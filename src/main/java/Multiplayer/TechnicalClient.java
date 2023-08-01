package Multiplayer;

<<<<<<< HEAD
import com.fasterxml.jackson.core.JsonProcessingException;
=======
>>>>>>> 244325ca06514a2b144c81d9ea80a7fe7b3a59ac
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
<<<<<<< HEAD
import org.junit.runner.Request;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class TechnicalClient implements Runnable{
    String host;
    BlockingQueue<ClientData> requests;
    BlockingQueue<ServerData> responses;
    public TechnicalClient(String host, BlockingQueue<ClientData> requests, BlockingQueue<ServerData> responses){
        this.host=host;
        this.requests=requests;
        this.responses=responses;

    }

    @Override
    public void run() {
        int port = 8080;
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Client client= new Client(responses);
=======

public class TechnicalClient {
    public TechnicalClient(String host) throws Exception {
        int port = 8080;
        EventLoopGroup workerGroup = new NioEventLoopGroup();

>>>>>>> 244325ca06514a2b144c81d9ea80a7fe7b3a59ac
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {

                @Override
                public void initChannel(SocketChannel ch)
                        throws Exception {
                    ch.pipeline().addLast(new ClientEncoder(),
<<<<<<< HEAD
                            new ClientDecoder(), client);
=======
                            new ClientDecoder(), new Client());
>>>>>>> 244325ca06514a2b144c81d9ea80a7fe7b3a59ac
                }
            });

            ChannelFuture f = b.connect(host, port).sync();
<<<<<<< HEAD
            while (true) {
                ClientData request;
                    while (!Objects.equals((request = requests.take()).getName(), "exit")) {
                        client.toServer(request);
                    }

            }

            //f.channel().closeFuture().sync();
        } catch (InterruptedException | JsonProcessingException e) {
            throw new RuntimeException(e);
        } finally {
            workerGroup.shutdownGracefully();
        }

=======

            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
>>>>>>> 244325ca06514a2b144c81d9ea80a7fe7b3a59ac
    }
}