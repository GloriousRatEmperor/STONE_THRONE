package Multiplayer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import jade.GameObject;

import java.util.ArrayList;

public class Processor extends ChannelInboundHandlerAdapter {
    private ArrayList<ChannelHandlerContext> ctxlist=new ArrayList<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server connected to sumone");
        ctxlist.add(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {



        ClientData ClientData = (ClientData) msg;


        ServerData ServerData = new ServerData();

        ServerData.setIntValue(ClientData.getIntValue());
        ServerData.setGameObjects(ClientData.getGameObjects());
        ServerData.setName(ClientData.getName());
        ServerData.setPos(ClientData.getPos());

        toClients(ServerData);

    }
    public void toClients(ServerData msg) throws JsonProcessingException {
        for (ChannelHandlerContext ctx : ctxlist) {
            ctx.writeAndFlush(msg);
        }
    }
    public void toclient(ChannelHandlerContext ctx,ServerData msg) throws JsonProcessingException {
        ctx.writeAndFlush(msg);
    }
}