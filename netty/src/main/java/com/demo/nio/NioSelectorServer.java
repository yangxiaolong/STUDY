package com.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @auther yangxiaolong
 * @create 2025/6/23
 */
public class NioSelectorServer {

    public static void main(String[] args) throws IOException {
        // 获得服务器通道
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(9000));
        // 创建选择器, 即创建epoll
        Selector selector = Selector.open();
        // 通道必须设置为非阻塞模式
        serverSocket.configureBlocking(false);
        // 将通道注册到选择器中，并设置感兴趣的事件
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server start...");
        // 为serverKey设置感兴趣的事件
        while (true) {
            // 若没有事件就绪，线程会被阻塞，反之不会被阻塞。从而避免了CPU空转
            // 返回值为就绪的事件个数
            int ready = selector.select();
            System.out.println("selector ready counts : " + ready);
            // 获取所有事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            // 使用迭代器遍历事件
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 判断key的类型
                if (key.isAcceptable()) {
                    // 获得key对应的channel
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    System.out.println("before accepting...");
                    // 获取连接
                    SocketChannel socketChannel = server.accept();
                    System.out.println("after accepting...");
                    // 设置为非阻塞模式，同时将连接的通道也注册到选择其中，同时设置附件
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("Client connect success...");
                } else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    System.out.println("before reading...");
                    ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                    int len = socketChannel.read(byteBuffer);
                    if (len > 0) {
                        System.out.println("接收到消息: " + new String(byteBuffer.array()));
                    } else if (len == -1) {
                        System.out.println("Client 断开...");
                        socketChannel.close();
                    }
                }
                iterator.remove();
            }
        }
    }

}
