import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by lawrencew on 11/10/2015.
 */
public class ChatClient {



    public ChatClient() throws Exception
    {
        final int port = 5000;
        final String host = "localhost";
        String clientName="";
        boolean running = true;

        Socket socket = new Socket(host,port);
        BufferedReader bfClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pW = new PrintWriter(socket.getOutputStream(),true);
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        while(running)
        {
            String clientInput = bf.readLine();
            if(clientInput.equals("exit"))
            {
                running=false;
            }
            else {
                pW.println(clientName + ": " + clientInput);
            }
        }

    }

    public static void main(String[] args) throws Exception
    {

        new ChatClient();
    }
}
