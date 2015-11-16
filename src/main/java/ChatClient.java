import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

/**
 * Created by lawrencew on 11/10/2015.
 */
public class ChatClient extends Thread{

    private Socket socket;
    private BufferedReader fromServer;
    private PrintWriter toServer;
    private BufferedReader clientInput;
    private boolean running = true;
    public ChatClient() throws IOException {
        final int port = 5000;
        final String host = "localhost";
        String clientName;

       try{
           socket = new Socket(host,port);
       }catch (ConnectException e)
       {

       }
        fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toServer = new PrintWriter(socket.getOutputStream(),true);
        clientInput = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("please enter a name: ");
        clientName=clientInput.readLine();

        toServer.println(clientName);

        this.start();
        while(running)
        {
            String clientsInput= clientInput.readLine();
            if(clientsInput.equals("exit"))
            {
                close();
            }
            else {
                toServer.println(clientsInput);
            }
        }
       System.exit(0);
    }

    public void run()
    {
        String message;
        while (true) {
            try {
                if ((message = fromServer.readLine()) != null) {
                    if(message.equals("stop"))
                    {
                        System.out.println("Server: Server closing");
                        close();
                    }
                    else
                    {
                        System.out.println(message);
                    }
                }
            }catch (IOException e)
            {
                interrupt();
                close();
            }
        }
    }
    public void close()
    {
        System.out.println("I was clled");
        try {
            toServer.println("exit");
            fromServer.close();
            toServer.close();
            clientInput.close();
            socket.close();
            this.interrupt();
            running = false;
        }catch (IOException e)
        {

        }
    }
    public static void main(String[] args) throws Exception
    {
        new ChatClient();
    }
}
