package Multiplayer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ServerDecoder extends ReplayingDecoder<ClientData> {

        private final Charset charset = StandardCharsets.UTF_8;

        @Override
        protected void decode(ChannelHandlerContext ctx,
                              ByteBuf in, List<Object> out) throws Exception {

            ClientData data = new ClientData();
            data.setIntValue(in.readInt());
            int strLen = in.readInt();
            data.setStrValue(
                    in.readCharSequence(strLen, charset).toString());
            out.add(data);
        }
    }