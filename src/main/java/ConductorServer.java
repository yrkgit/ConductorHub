import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ConductorServer {
   private static Socket socket;
    private static ServerSocket serverSocket;
    private static InputStreamReader inputStreamReader;
    private static BufferedReader bufferedReader;
    private static String message;


    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(7800);
            while (true) {
                socket = serverSocket.accept();
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                message = bufferedReader.readLine();
                System.out.println(message);
                sendToDevice();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void sendToDevice() {
        System.out.println("Sending to device");
        FrameToDeviceSender frame = new FrameToDeviceSender();
        frame.sendToDevice();
    }

}
