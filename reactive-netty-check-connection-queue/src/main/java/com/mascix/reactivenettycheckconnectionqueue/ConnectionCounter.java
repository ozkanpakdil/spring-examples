package com.mascix.reactivenettycheckconnectionqueue;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.atomic.AtomicLong;

@ChannelHandler.Sharable
public class ConnectionCounter extends ChannelDuplexHandler {

    public AtomicLong totalConnectionCount = new AtomicLong();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        totalConnectionCount.incrementAndGet();
        if (ctx.channel().isWritable() == false) { // means we hit the max limit of netty
            System.out.println("I suggest we should restart or put a new server to our pool :)");
        }
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        totalConnectionCount.decrementAndGet();
        super.channelInactive(ctx);
    }

}