package Multiplayer;

<<<<<<< HEAD
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.BlockingQueue;

public class Client extends ChannelInboundHandlerAdapter {

    ChannelHandlerContext ctx;
    BlockingQueue<ServerData> responses;
    private final Charset charset = StandardCharsets.UTF_8;
    ObjectMapper mapper = new ObjectMapper();

    public Client(BlockingQueue<ServerData> responses) {
        this.responses=responses;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx)
            throws Exception {
        this.ctx=ctx;

    }
    public void toServer(ClientData msg) throws JsonProcessingException {
        ctx.writeAndFlush(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        responses.add((ServerData) msg);
=======
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class Client extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx)
            throws Exception {

        ClientData msg = new ClientData();
        msg.setIntValue(123);
        msg.setStrValue(
                "all work and no play makes jack a dull boy");
        ChannelFuture future = ctx.writeAndFlush(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println((ServerData)msg);
        ctx.close();
>>>>>>> 244325ca06514a2b144c81d9ea80a7fe7b3a59ac
    }
}