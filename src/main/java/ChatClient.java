import java.io.BufferedReader;
import java.io.IOException;
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
        String clientName="Bob";
        boolean running = true;

        Socket socket = new Socket(host,port);
        final BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter toServer = new PrintWriter(socket.getOutputStream(),true);
        BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("please enter a name: ");
        clientName=clientInput.readLine();

        toServer.println(clientName);

        Thread reader = new Thread(){

            public void run()
            {
                System.out.println("started");
                String message;
                    while (true) {
                        try {
                            if ((message = fromServer.readLine()) != null) {
                                System.out.println(message);
                            }
                        }catch (IOException e)
                        {
                            e.printStackTrace();
                        }

                    }


            }
        };
        reader.start();




        while(running)
        {
            String clientsInput = clientInput.readLine();
            if(clientsInput.equals("exit"))
            {
                toServer.println(clientsInput);
                fromServer.close();
                toServer.close();
                clientInput.close();
                socket.close();

                running=false;
            }
            else {
                toServer.println(clientName + ": " + clientsInput);
              //  System.out.println(fromServer.readLine());
            }
        }



    }

    public static void main(String[] args) throws Exception
    {

        new ChatClient();
    }
}
