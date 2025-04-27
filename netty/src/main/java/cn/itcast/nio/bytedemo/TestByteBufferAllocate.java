package cn.itcast.nio.bytedemo;

import java.nio.ByteBuffer;

public class TestByteBufferAllocate {
    public static void main(String[] args) {
        System.out.println(ByteBuffer.allocate(16).getClass());
        System.out.println(ByteBuffer.allocateDirect(16).getClass());
        /*
        class java.nio.HeapByteBuffer  - java 堆内存，读写效率较低，受到 GC的彩响
        class java.nio.DirectByteBuffer - 直接内存，读写效率高(少一次拷贝)，不会受 GC影响, 分配效率较低
         */
    }
}
