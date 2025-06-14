package com.exchange.demo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

/**
 * @auther yangxiaolong
 * @create 2025/6/14
 */
@Builder
@Getter
public class AlbumsReq {

    @NotNull
    @Positive
    private Long userId;
    @NotBlank
    private String title;

}

