package cn.itcast.nio.io;

import cn.itcast.nio.bytedemo.ByteBufferUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectServer {
    public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 获得服务器通道
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(8080));
        // 创建选择器
        Selector selector = Selector.open();
        // 通道必须设置为非阻塞模式
        server.configureBlocking(false);
        // 将通道注册到选择器中，并设置感兴趣的实践
        server.register(selector, SelectionKey.OP_ACCEPT);
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
                // 处理完毕后移除
                iterator.remove();

                // 判断key的类型
                if (key.isAcceptable()) {
                    // 获得key对应的channel
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    System.out.println("before accepting...");
                    // 获取连接
                    SocketChannel socketChannel = channel.accept();
                    System.out.println("after accepting...");
                    // 设置为非阻塞模式，同时将连接的通道也注册到选择其中
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    try {
                        SocketChannel channel = (SocketChannel) key.channel();
                        final int read = channel.read(buffer);//如果是正常断开,read方法返回 -1
                        if (read == -1) {//正常断开
                            key.cancel();
                        } else {
                            buffer.flip();
                            ByteBufferUtil.debugRead(buffer);
                            buffer.clear();
                        }
                    } catch (IOException e) {
                        key.cancel();//因为客户端断开了,因此需要将key取消 (从selector 的 key 集合中真正删除 key)
                    }
                }
            }
        }
    }
}