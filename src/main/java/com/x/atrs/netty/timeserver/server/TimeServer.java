
/*
 * @Copyright 2014-2020 云安宝 (www.yunanbao.com.cn).
 *
 * 注意：本内容仅限于深圳云安宝科技有限公司内部传阅，禁止外泄以及用于其他商业目的
 *
 */

package com.x.atrs.netty.timeserver.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author xuewenke
 * @since 2020/12/23 下午3:27
 */
public class TimeServer {

    public static void main(String[] args) {
        new TimeServer().bind(8080);
    }

    public void bind(int port) {
        /*
            NioEventLoopGroup 是线程组
            boss 线程组用于处理网络事件，即reactor线程组，用于接收客户端的连接
            worker 用于网络的读写
         */
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {

            /*
                ServerBootstrap 程序启动类，用于屏蔽复杂的启动流程。
             */
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new TimeServerChannelInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

            // 等待服务端监听的端口被关闭
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 优雅退出
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}