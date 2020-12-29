

package com.x.atrs.netty.timeserver.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author xuewenke
 * @since 2020/12/23 下午7:03
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private final ByteBuf firstMessage;


    public TimeClientHandler() {
        byte[] req = ("query time" + System.getProperty("line.separator")).getBytes();
        firstMessage = Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//        String body = new String(req, StandardCharsets.UTF_8);
        String body = (String) msg;
        System.out.println("time " + body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("释放资源");
        ctx.close();
    }
}