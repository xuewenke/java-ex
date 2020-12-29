
/*
 * @Copyright 2014-2020 云安宝 (www.yunanbao.com.cn).
 *
 * 注意：本内容仅限于深圳云安宝科技有限公司内部传阅，禁止外泄以及用于其他商业目的
 *
 */

package com.x.atrs.netty.timeserver.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author xuewenke
 * @since 2020/12/23 下午3:27
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//        String body = new String(req, StandardCharsets.UTF_8);
        String body = (String) msg;
        System.out.println("time Server receive odder " + body);
        String response = "query time".equals(body) ? System.currentTimeMillis() + "" : "bad order";
        response = response + System.getProperty("line.separator");
        ByteBuf responseBuf = Unpooled.copiedBuffer(response.getBytes());
        ctx.writeAndFlush(responseBuf);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}