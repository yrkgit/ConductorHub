import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class FrameToDeviceSender {
    private Socket socket;
    private PrintWriter printWriter;
    private String reply= "Reply from PC";

    public void sendToDevice() {
        try {
            socket = new Socket("192.168.0.13", 7801);
            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.write(reply);
            printWriter.flush();
            printWriter.close();
            socket.close();
            System.out.println("Send " + reply);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
