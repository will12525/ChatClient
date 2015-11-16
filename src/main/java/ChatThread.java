import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lawrencew on 11/10/2015.
 */
public class ChatThread extends Thread {

    List<String> messages = new ArrayList<String>();
    String name;
    BufferedReader fromServer;
    Socket socket;
    ChatThread(String name, Socket socket,final BufferedReader fromServer)
    {
        this.socket=socket;
        this.name=name;
       this.fromServer=fromServer;
    }
    public void run(){
        System.out.println("started");
        String message;
        while (true) {
            try {
                if ((message = fromServer.readLine()) != null) {
                    System.out.println(message);
                }
                socket.close();
            }catch (IOException e)
            {
                e.printStackTrace();
            }

        }
    }

}
