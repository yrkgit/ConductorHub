import Hub.SocketListener;
import Hub.StringToDeviceSender;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class SocketListenerTest implements Runnable {
    private SocketListener socketListener;
    private StringToDeviceSender stringToDeviceSender;
    private static String actualValue;

    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter) {
        testReporter.publishEntry("Running " + testInfo.getDisplayName());
    }

    @DisplayName("Multiply test socket listener ")
    @RepeatedTest(3)
    void startSocketListenerTest() {
        Thread thread = new Thread(new SocketListenerTest());
        thread.start();
        stringToDeviceSender();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals("TEST", actualValue);
    }

    void stringToDeviceSender() {
        stringToDeviceSender = new StringToDeviceSender();
        stringToDeviceSender.sendFrame("TEST", "127.0.0.1", 7800);
    }

    @Override
    public void run() {
        socketListener = new SocketListener();
        actualValue = socketListener.startSocketListener();
    }
}