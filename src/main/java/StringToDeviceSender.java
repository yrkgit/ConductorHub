/** Class for sending String (content) to remote socket */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StringToDeviceSender implements Sender {

    private static final Logger logger = LogManager.getLogger(DeviceSubscriptionServer.class);
    private Socket socket;
    private PrintWriter printWriter;

    @Override
    public void sendFrame(String content, String destinationIpAddress, int destinationPortNumber) {
        logger.info("Try to send to: "+destinationIpAddress +" "+ content);
        try {
            socket = new Socket(destinationIpAddress, destinationPortNumber);
            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.write(content);
            printWriter.flush();
            printWriter.close();
            socket.close();
            logger.info("Send " + content +" to device");
        }catch(ConnectException connectException){
            logger.warn("Can't send frame to device: "+ connectException.getMessage());
        }
        catch (IOException e) {
            logger.error("Can't send frame to device: "+ e.getMessage());
            e.printStackTrace();
        }

    }

}
