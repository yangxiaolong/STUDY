package cn.itcast.nio.filechannel;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

@Slf4j
public class TestFileChannel {
    public static void main(String[] args) {
        try (FileChannel from = new FileInputStream("data.txt").getChannel();
             FileChannel to = new FileOutputStream("to.txt").getChannel()) {
            from.transferTo(0, from.size(), to);
        } catch (IOException e) {
            log.error("", e);
        }
    }
}
