package com.exchange;

import com.exchange.demo.AlbumsClient;
import com.exchange.demo.AlbumsReq;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @auther yangxiaolong
 * @create 2025/6/14
 */
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private AlbumsClient albumsClient;

    @Test
    void contextLoads() {
        System.out.println("getAll size:" + albumsClient.getAll().size());

        System.out.println("getById 1:" + albumsClient.getById(1L));

        // 创建一个
        Object addedResp = albumsClient.add(AlbumsReq.builder().userId(1L).title("diy add...").build());
        System.out.println("创建的allAlbums对象为：" + addedResp + "，现在总数为：" + albumsClient.getAll().size());
    }

}
