/**
 * Runnable class that opening socket (by invoking SocketListener) and starting listening to communication from remote hosts. When get response from SocketListener pass it to JSON deserializer
 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeviceSubscriptionServer implements Runnable {

    private static final Logger logger = LogManager.getLogger(DeviceSubscriptionServer.class);
    private static final int destinationLogPortNumber = 7803;
//TODO Create anonymous inner classes for object used once (from classes with one method)
    private final JsonDeserializer deserializer;
    private final LogResponseFrameCreator logResponseFrameCreator;
    private final JsonSerializer serializedFrame;
    private final Sender frameSender;
    private final SocketListener socketListener;
    private final UserPermissionToLogonVerifier userVerifier;
    private final MySqlDatabase mySqlDatabase;

   private String content;
    private boolean isServerRunning;
    private LogRequestFrame receivedFrame;
    private boolean isServerAlreadyStarted;

    public DeviceSubscriptionServer() {
        logResponseFrameCreator = new LogResponseFrameCreator();
        deserializer = new JsonDeserializer();
        serializedFrame = new JsonSerializer();
        frameSender = new StringToDeviceSender();
        socketListener = new SocketListener();
        isServerRunning = true;
        userVerifier=new UserPermissionToLogonVerifier();
        mySqlDatabase = new MySqlDatabase();
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
                logger.debug("Start receiving packets... ");
                content = socketListener.startSocketListener();
                logger.info("Captured content from socket " + content);
                processCapturedFrame();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void processCapturedFrame() {
        receivedFrame = deserializer.deserializeJsonToFrameObject(content);

        if (receivedFrame != null) {
            logger.info(receivedFrame.getUser() + " is trying to connect");

            //user authentication - setting response permission
            setPermission(receivedFrame);

            LogResponseFrame logResponseFrame = prepareLogResponseFrame();

            if (logResponseFrame.getPermission().equals(LogResponseTypes.GRANTED)) {
                subscribeDevice();
            }
        }
    }

    private LogResponseFrame prepareLogResponseFrame() {
        LogResponseFrame logResponseFrame = logResponseFrameCreator.createResponseFrame();
        frameSender.sendFrame(serializedFrame.createJson(logResponseFrame), receivedFrame.getIpAddress(), destinationLogPortNumber);
        return logResponseFrame;
    }

    private void setPermission(LogRequestFrame receivedFrame) {
        LogResponseTypes userAccessPermission = userVerifier.verifyUserAccessPermission(
                new User.UserBuilder()
                        .name(receivedFrame.getUser())
                        .password(receivedFrame.getPass())
                        .build()
                ,mySqlDatabase);

        logResponseFrameCreator.setResponseType(userAccessPermission);
    }

    private void subscribeDevice() {
        if (DeviceSubscriber.getListOfDevicesIps().contains(receivedFrame.getIpAddress())) {
            logger.info(receivedFrame.getIpAddress() + " was already subscribed");
        } else {
            DeviceSubscriber.addDeviceIp(receivedFrame.getIpAddress());
            DeviceSubscriber.addDeviceUser(receivedFrame.getUser());
            logger.info("Dodano urządzenie: " + receivedFrame.getUser() + " with IP address: " + receivedFrame.getIpAddress() + " Ilość subskrybentów IP: " + DeviceSubscriber.getNumberOfDevicesIps());

            if (!isServerAlreadyStarted) {
                startSendingData();
            }
        }
    }

    private void startSendingData() {
        isServerAlreadyStarted = true;
        Thread dataFrameCreatorThread = new Thread(new DataFrameSender());
        dataFrameCreatorThread.start();
    }
}
