import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class JsonToDeviceSender implements FrameSender {
    private Socket socket;
    private PrintWriter printWriter;
    private String reply= "Reply from PC";



    @Override
    public void sendFrame() {
        try {
            socket = new Socket("10.0.2.16", 7801);
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
