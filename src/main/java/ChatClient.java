
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by lawrencew on 11/10/2015.
 * from tutorial https://www.youtube.com/watch?v=tsz-assb1X8
 */
public class ChatClient {


    public static void main(String[] args) throws Exception{
        new ChatClient("localhost",8000).run();
    }

    private final String host;
    private final int port;
    private boolean run = true;

    public ChatClient(String host, int port)
    {
        this.host = host;
        this.port = port;
    }



    public void run() throws Exception
    {
        EventLoopGroup group = new NioEventLoopGroup();

        try{
            Bootstrap bootS = new Bootstrap().group(group).channel(NioSocketChannel.class).handler(new ChatClientInitializer());

            Channel channel = bootS.connect(host,port).sync().channel();

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            while(run)
            {
                    String message = in.readLine();
                    if(message.equals("quit"))
                    {
                        run=false;
                    }
                else {
                        System.out.println("wrote something");
                        channel.write(message + "\r\n");
                    }

            }

        }
        finally
        {
            group.shutdownGracefully();
        }
        System.exit(0);
    }

}
