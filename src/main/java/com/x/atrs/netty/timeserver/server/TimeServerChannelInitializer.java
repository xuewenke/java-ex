package com.x.atrs.netty.timeserver.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author xuewenke
 */
public class TimeServerChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel channel) {

        //在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new TimeServerHandler());
    }

}
