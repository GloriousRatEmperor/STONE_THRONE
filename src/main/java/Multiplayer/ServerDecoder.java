package Multiplayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

public class ServerDecoder extends ReplayingDecoder<ClientData> {

        private final Charset charset = StandardCharsets.UTF_8;
        private ObjectMapper objectMapper = new ObjectMapper();

        @Override
        protected void decode(ChannelHandlerContext ctx,
                              ByteBuf in, List<Object> out) throws Exception {
            int whatisdis=in.readInt();

            String msgString=in.toString(4,whatisdis,charset);
            ClientData data =  objectMapper.readValue(msgString, ClientData.class);
            out.add(data);
        }
    }