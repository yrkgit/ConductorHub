/**
 * Runnable class that opening socket (by invoking SocketListener) and starting listening to communication from remote hosts. When get response from SocketListener pass it to JSON deserializer
 */

public class DeviceSubscriptionServer implements Runnable {

    private static final int destinationLogPortNumber = 7803;

    private String content;
    private boolean isServerRunning;
    private final JsonDeserializer deserializer;
    private final LogResponseFrameCreator logResponseFrameCreator;
    private final JsonSerializer serializedFrame;
    private final Sender frameSender;
    private LogRequestFrame receivedFrame;
    private final DataFrameCreator dataFrameCreator;
    private final SocketListener socketListener;
    private Thread dataFrameCreatorThread;
    private boolean isServerAlreadyStarted;

    public DeviceSubscriptionServer() {
        logResponseFrameCreator = new LogResponseFrameCreator();
        deserializer = new JsonDeserializer();
        dataFrameCreator = new DataFrameCreator();
        serializedFrame = new JsonSerializer();
        frameSender = new StringToDeviceSender();
        socketListener = new SocketListener();
        isServerRunning = true;
    }

    public void stopServer() {
        isServerRunning = false;
    }

    public void startServer() {
        isServerRunning = true;
    }


    @Override
    public void run() {
        try {
            while (isServerRunning) {
                System.out.println("Start receiving packets... ");
                content = socketListener.startSocketListener();
                System.out.println("Captured content from socket " + content);
                processCapturedFrame();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void processCapturedFrame() {
        receivedFrame = deserializer.deserializeJsonToFrameObject(content);

        if (receivedFrame != null) {
            System.out.println(receivedFrame.getUser() + " is trying to connect");

            //TODO add user authentication from sql base
            logResponseFrameCreator.setResponseType(LogResponseTypes.GRANTED);


            LogResponseFrame logResponseFrame = logResponseFrameCreator.createResponseFrame();
            frameSender.sendFrame(serializedFrame.createJson(logResponseFrame), receivedFrame.getIpAddress(), destinationLogPortNumber);

            if (logResponseFrame.getPermission().equals(LogResponseTypes.GRANTED)) {
                subscribeDevice();
            }
        }
    }

    private void subscribeDevice() {
        if (DeviceSubscriber.getListOfDevicesIps().contains(receivedFrame.getIpAddress())) {
            System.out.println(receivedFrame.getIpAddress() + " was already subscribed");
        } else {
            DeviceSubscriber.addDeviceIp(receivedFrame.getIpAddress());
            DeviceSubscriber.addDeviceUser(receivedFrame.getUser());
            System.out.println("Dodano urządzenie: " + receivedFrame.getUser() + " with IP address: " + receivedFrame.getIpAddress() + " Ilość subskrybentów IP: " + DeviceSubscriber.getNumberOfDevicesIps());

            if (!isServerAlreadyStarted) {
                startSendingData();
            }
        }
    }

    private void startSendingData() {
        isServerAlreadyStarted = true;
        dataFrameCreatorThread = new Thread(new DataFrameSender());
        dataFrameCreatorThread.start();
    }
}
