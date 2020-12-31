

package com.x.atrs.netty.decoder.client;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author xuewenke
 * @since 2020/12/29 下午4:03
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    private int counter;


    static final String ECHO = "hi welcome to netty $_";

    public EchoClientHandler() {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO.getBytes()));
        }


    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(" this is " + counter + msg);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }
}