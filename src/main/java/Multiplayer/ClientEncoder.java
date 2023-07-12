package Multiplayer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ClientEncoder
        extends MessageToByteEncoder<ClientData> {

    private final Charset charset = StandardCharsets.UTF_8;

    @Override
    protected void encode(ChannelHandlerContext ctx,
                          ClientData msg, ByteBuf out) throws Exception {

        out.writeInt(msg.getIntValue());
        out.writeInt(msg.getStrValue().length());
        out.writeCharSequence(msg.getStrValue(), charset);
    }
}