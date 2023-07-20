package Multiplayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ClientEncoder
        extends MessageToByteEncoder<ClientData> {

    private final Charset charset = StandardCharsets.UTF_8;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void encode(ChannelHandlerContext ctx,
                          ClientData msg, ByteBuf out) throws Exception {
        String msgString= objectMapper.writeValueAsString(msg);
        out.writeCharSequence(msgString, charset);
    }
}