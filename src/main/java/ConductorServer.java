/** Runnable class that opening socket (by invoking SocketListener) and starting listening to communication from remote hosts. When get response from SocketListener pass it to JSON deserializer */

import java.io.IOException;
import java.net.ServerSocket;

public class ConductorServer extends SocketListener implements Runnable {
    private static ServerSocket serverSocket;
    private static String content;
    private boolean isServerRunning;
    private JsonDeserializer deserializer;


    public ConductorServer() {
        isServerRunning = true;
    }

    public void stopServer() {
        isServerRunning = false;
    }

    public void startServer() {
        isServerRunning = true;
    }


    @Override
    public void run() {
        deserializer=new JsonDeserializer();
        try {
            serverSocket = new ServerSocket(7800);
            while (isServerRunning) {
                System.out.println("Start receiving packets... ");
                content=startSocketListener(serverSocket);
                System.out.println("Captured content from socket "+content);
                deserializer.deserializeJsonToFrameObject(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
