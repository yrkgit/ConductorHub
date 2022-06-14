import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class SocketListenerTest implements Runnable{
    private SocketListener socketListener;
    private StringToDeviceSender stringToDeviceSender;
    private static String value;


    @Test
    void startSocketListenerTest() {
        Thread thread = new Thread(new SocketListenerTest());
        thread.start();
        stringToDeviceSender();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals("TEST",value);
    }

    void stringToDeviceSender(){
        stringToDeviceSender = new StringToDeviceSender();
        stringToDeviceSender.sendFrame("TEST","127.0.0.1",7800);
    }

    @Override
    public void run() {
        socketListener = new SocketListener();
        value=socketListener.startSocketListener();
    }
}