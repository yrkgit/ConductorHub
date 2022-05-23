/** Class design to open and listen socket connection */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketListener {

    private Socket socket;
    private ServerSocket serverSocket;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private String message;


    public String startSocketListener(ServerSocket serverSocket) {
        try {
            this.serverSocket=serverSocket;

            socket = serverSocket.accept();
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            message = bufferedReader.readLine();
            System.out.println(message);
            return message;


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
