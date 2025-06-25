package com.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * @auther yangxiaolong
 * @create 2025/6/24
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(Thread.currentThread().getName() + " " + buf.toString(StandardCharsets.UTF_8));
        // 调用下一个handler
        ctx.fireChannelRead(msg);
    }

}
