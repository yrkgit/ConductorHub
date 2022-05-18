import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ConductorServer implements Runnable {
    private static Socket socket;
    private static ServerSocket serverSocket;
    private static InputStreamReader inputStreamReader;
    private static BufferedReader bufferedReader;
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
                socket = serverSocket.accept();
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                content = bufferedReader.readLine();


                System.out.println("Captured content from socket "+content);
                deserializer.deserializeJsonToFrameObject(content);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
