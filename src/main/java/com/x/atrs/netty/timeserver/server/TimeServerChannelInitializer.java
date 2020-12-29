package com.x.atrs.netty.timeserver.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author xuewenke
 */
public class TimeServerChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel channel) {

        //解决粘包的问题
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        channel.pipeline().addLast(new StringDecoder());
        //在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new TimeServerHandler());
    }

}
