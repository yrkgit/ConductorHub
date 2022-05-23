/** Class for sending String (content) to remote socket */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

public class StringToDeviceSender implements Sender {

    private Socket socket;
    private PrintWriter printWriter;

    @Override
    public void sendFrame(String content, String destinationIpAddress) {
        System.out.println("Try to send to device: "+ content);
        try {
            //TODO capture and store destination ip from LOGREQUEST
            socket = new Socket(destinationIpAddress, 7801);
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
