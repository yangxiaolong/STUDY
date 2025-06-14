package com.exchange.demo;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

/**
 * @auther yangxiaolong
 * @create 2025/6/14
 */
@HttpExchange(value = "/albums")
public interface AlbumsClient {

    @GetExchange
    List<AlbumsResp> getAll();

    @GetExchange("/{id}")
    AlbumsResp getById(@PathVariable Long id);

    @PostExchange
    AlbumsResp add(@RequestBody @Valid AlbumsReq req);

}
