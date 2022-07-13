package Hub; /**
 * Class design to open and listen socket connection
 */

import Hub.FileLogger;

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

    //TODO move port number to config
    private final int PORT_NUMBER = 7800;


    public String startSocketListener() {
        try {
            serverSocket = new ServerSocket(PORT_NUMBER);
            socket = serverSocket.accept();
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            message = bufferedReader.readLine();

            FileLogger.logger.info(message);

            bufferedReader.close();
            inputStreamReader.close();
            socket.close();
            serverSocket.close();

            return message;

        } catch (IOException e) {
            FileLogger.logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
