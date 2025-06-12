package cn.itcast.nio.bytedemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestByteBuffer {

    private final static Logger log = LoggerFactory.getLogger(TestByteBuffer.class);

    public static void main(String[] args) {
        //FileChannel
        //1.输入输出流 2.RandomAccessFile
        try (FileChannel channel = new FileInputStream("data.txt").getChannel()) {
            //准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true) {
                int len = channel.read(buffer);//channel中读取数据,网buffer中写
                if (len == -1) {
                    break;
                }
                log.info("读取到的长度:{}", len);
                buffer.flip();//切换到读模式
                while (buffer.hasRemaining()) {//检查是否有剩余
                    byte b = buffer.get();
                    log.info("{}", (char) b);//打印内容
                }
                buffer.clear();//切换为写模式
            }
        } catch (IOException e) {
            log.info("异常", e);
        }
    }

}
