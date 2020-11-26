
package com.x.atrs.netty.netty101;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author xuewenke
 * @since 2020/10/23 11:52 上午
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        System.out.println("客户端建立连接");
        System.out.println("本客户端链接到服务端。channelId：" + ch.id());
    }
}