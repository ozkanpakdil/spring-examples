package com.mascix.reactivenettycheckconnectionqueue;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.SingleThreadEventExecutor;
import org.springframework.boot.web.embedded.netty.NettyServerCustomizer;
import reactor.netty.http.server.HttpServer;


class NettyConfigure implements NettyServerCustomizer {
    NioEventLoopGroup boss = new NioEventLoopGroup(2, new DefaultThreadFactory("boss"));
    NioEventLoopGroup worker = new NioEventLoopGroup(2, new DefaultThreadFactory("worker"));

    ConnectionCounter childHandler = new ConnectionCounter();

    @Override
    public HttpServer apply(HttpServer httpServer) {

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("worker executor Count:" + worker.executorCount());
                    System.out.println("Total connection count:" + childHandler.totalConnectionCount.get());

                    int tot = 0;
                    for (final EventExecutor eventExecutor : worker) {
                        if (eventExecutor instanceof SingleThreadEventExecutor) {
                            final SingleThreadEventExecutor singleExecutor = (SingleThreadEventExecutor) eventExecutor;
                            tot += singleExecutor.pendingTasks();
                        }
                    }
                    int totB = 0;
                    for (final EventExecutor eventExecutor : boss) {
                        if (eventExecutor instanceof SingleThreadEventExecutor) {
                            final SingleThreadEventExecutor singleExecutor = (SingleThreadEventExecutor) eventExecutor;
                            totB += singleExecutor.pendingTasks();
                        }
                    }
                    System.out.println("worker:" + tot + " boss:" + totB + " .pendingTasks()");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return httpServer.tcpConfiguration(tcpServer -> tcpServer.bootstrap(serverBootstrap -> serverBootstrap
                .group(boss, worker)
                .childHandler(childHandler)
//                .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
//                    @Override
//                    public void initChannel(SocketChannel ch) throws Exception {
//                        ch.pipeline().addLast(new DiscardServerHandler());
//                    }
//                })
//                .option(ChannelOption.SO_BACKLOG, 2)
                .channel(NioServerSocketChannel.class)));
    }
}