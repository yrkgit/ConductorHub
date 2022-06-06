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
        FileLogger.logger.info("Try to send to: "+destinationIpAddress +" "+ content);
        try {
            socket = new Socket(destinationIpAddress, destinationPortNumber);
            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.write(content);
            printWriter.flush();
            printWriter.close();
            socket.close();
            FileLogger.logger.info("Send " + content +" to device");
        }catch(ConnectException connectException){
            FileLogger.logger.warn("Can't send frame to device: "+ connectException.getMessage());
        }
        catch (IOException e) {
            FileLogger.logger.error("Can't send frame to device: "+ e.getMessage());
            e.printStackTrace();
        }

    }

}
