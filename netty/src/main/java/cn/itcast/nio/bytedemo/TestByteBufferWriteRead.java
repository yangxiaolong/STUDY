package cn.itcast.nio.bytedemo;

import java.nio.ByteBuffer;

import static cn.itcast.nio.bytedemo.ByteBufferUtil.debugAll;

public class TestByteBufferWriteRead {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x61);//a
        debugAll(buffer);
        buffer.put(new byte[]{0x62, 0x63, 0x64});//b c d
        debugAll(buffer);
        buffer.flip();
        System.out.println(buffer.get());
        debugAll(buffer);
        buffer.compact();
        debugAll(buffer);
        buffer.put(new byte[]{0x65, 0x66, 0x67});//e f g
        debugAll(buffer);
    }

}
