
package com.x.atrs.netty.netty101;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author xuewenke
 * @since 2020/10/22 5:22 下午
 */
public class NettyServer {


    public static void main(String[] args) {
        new NettyServer().bing(7397);
    }

    private void bing(int port) {
        //配置服务端NIO线程组
        /**
         *  一个 EventLoopGroup 包含一个或者多个 EventLoop；
         *  一个 EventLoop 在它的生命周期内只和一个 Thread 绑定；
         *  所有由 EventLoop 处理的 I/O 事件都将在它专有的 Thread 上被处理；
         *  一个 Channel 在它的生命周期内只注册于一个 EventLoop；
         *  一个 EventLoop 可能会被分配给一个或多个 Channel。
         */
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(parentGroup, childGroup)
                    //非阻塞模式
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new ServerChannelInitializer());
            ChannelFuture f = serverBootstrap.bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            childGroup.shutdownGracefully();
            parentGroup.shutdownGracefully();
        }

    }

}