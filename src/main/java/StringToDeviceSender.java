import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class StringToDeviceSender implements Sender {

    private Socket socket;
    private PrintWriter printWriter;


    @Override
    public void sendFrame(String content) {
        System.out.println("Sending to device: "+ content);
        try {
            socket = new Socket("10.0.2.16", 7801);
            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.write(content);
            printWriter.flush();
            printWriter.close();
            socket.close();
            System.out.println("Send " + content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
