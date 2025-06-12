package cn.itcast.nio.filechannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TestFileChannel {

    private final static Logger log = LoggerFactory.getLogger(TestFileChannel.class);

    public static void main(String[] args) {
        try (FileChannel from = new FileInputStream("data.txt").getChannel();
             FileChannel to = new FileOutputStream("to.txt").getChannel()) {
            from.transferTo(0, from.size(), to);
        } catch (IOException e) {
            log.error("", e);
        }
    }
}
