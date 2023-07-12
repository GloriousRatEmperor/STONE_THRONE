package Multiplayer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class Responder
        extends MessageToByteEncoder<ServerData> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ServerData msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getIntValue());
    }
}