/** Class for sending String (content) to remote socket */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

public class StringToDeviceSender implements Sender {

    private Socket socket;
    private PrintWriter printWriter;

    @Override
    public void sendFrame(String content, String destinationIpAddress, int destinationPortNumber) {
        System.out.println("Try to send to: "+destinationIpAddress +" "+ content);
        try {
            socket = new Socket(destinationIpAddress, destinationPortNumber);
            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.write(content);
            printWriter.flush();
            printWriter.close();
            socket.close();
            System.out.println("Send " + content +" to device");
        }catch(ConnectException connectException){
            System.out.println("Can't send frame to device");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}
