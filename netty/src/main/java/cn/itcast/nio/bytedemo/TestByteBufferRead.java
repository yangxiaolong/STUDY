package cn.itcast.nio.bytedemo;

import java.nio.ByteBuffer;

import static cn.itcast.nio.bytedemo.ByteBufferUtil.debugAll;

public class TestByteBufferRead {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});
        buffer.flip();

        /*buffer.get(new byte[4]);
        debugAll(buffer);
        buffer.rewind();// 从头开始读取
        System.out.println((char) buffer.get());*/

        //mark & reset
        debugAll(buffer);
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        buffer.mark();//mark 做一个标记，记录 position 位置， reset 是将 position 重置到 mark 的位置
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        buffer.reset();//跳回mark的位置
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());

        //get(i) //不会改变读索引的位置
        System.out.println((char) buffer.get(3));
    }
}
