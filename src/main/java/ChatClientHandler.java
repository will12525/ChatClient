import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * Created by lawrencew on 11/10/2015.
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println(s);
    }
}
