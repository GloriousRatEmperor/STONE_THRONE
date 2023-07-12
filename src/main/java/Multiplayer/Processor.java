package Multiplayer;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class Processor extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {

        ClientData ClientData = (ClientData) msg;
        ServerData ServerData = new ServerData();
        ServerData.setIntValue(ClientData.getIntValue() * 2);
        ChannelFuture future = ctx.writeAndFlush(ServerData);
        future.addListener(ChannelFutureListener.CLOSE);
        System.out.println(ClientData);
    }
}