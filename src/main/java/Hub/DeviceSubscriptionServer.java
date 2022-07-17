package Hub;

import Frames.*;

/**
 * Runnable class that opening socket (by invoking Hub.SocketListener) and starting listening to communication from remote hosts. When get response from Hub.SocketListener pass it to JSON deserializer
 */


public class DeviceSubscriptionServer implements Runnable {



    private static final int destinationLogPortNumber = 7803;
//TODO Create anonymous inner classes for object used once (from classes with one method)
    private final JsonDeserializer deserializer;
    private final LogResponseFrameController logResponseFrameController;
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
        logResponseFrameController = new LogResponseFrameController();
        deserializer = new JsonDeserializer();
        serializedFrame = new JsonSerializer();
        frameSender = new StringToDeviceSender();
        socketListener = new SocketListener();
        userVerifier=new UserPermissionToLogonVerifier();
        mySqlDatabase = new MySqlDatabase();
        isServerRunning = true;
    }


    @Override
    public void run() {
        try {
            while (isServerRunning) {
                FileLogger.logger.info("Start receiving packets... ");
                content = socketListener.startSocketListener();
                FileLogger.logger.info("Captured content from socket " + content);
                processCapturedFrame();
            }
        } catch (Exception e) {
            FileLogger.logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void processCapturedFrame() {
        receivedFrame = deserializer.deserializeJsonToLogRequestFrameObject(content);

        if (receivedFrame != null) {
            FileLogger.logger.info(receivedFrame.getUser() + " is trying to connect");

            //user authentication - setting response permission
            setPermission(receivedFrame);

            LogResponseFrame logResponseFrame = prepareLogResponseFrame();

            if (logResponseFrame.getPermission().equals(LogResponseTypes.GRANTED)) {
                subscribeDevice();
            }
        }
    }

    private LogResponseFrame prepareLogResponseFrame() {
        LogResponseFrame logResponseFrame = logResponseFrameController.createResponseFrame();
        frameSender.sendFrame(serializedFrame.createJsonFromFrame(logResponseFrame), receivedFrame.getIpAddress(), destinationLogPortNumber);
        return logResponseFrame;
    }

    private void setPermission(LogRequestFrame receivedFrame) {
        LogResponseTypes userAccessPermission = userVerifier.verifyUserAccessPermission(
                new User.UserBuilder()
                        .name(receivedFrame.getUser())
                        .password(receivedFrame.getPass())
                        .build()
                ,mySqlDatabase);

        logResponseFrameController.setResponseType(userAccessPermission);
    }

    private void subscribeDevice() {
        if (DeviceSubscriber.getListOfSubscribedDevicesIpAddresses().contains(receivedFrame.getIpAddress())) {
            FileLogger.logger.info(receivedFrame.getIpAddress() + " was already subscribed");
        } else {
            DeviceSubscriber.addDeviceIpToList(receivedFrame.getIpAddress());
            FileLogger.logger.info("Dodano urządzenie: " + receivedFrame.getUser() + " with IP address: " + receivedFrame.getIpAddress() + " Ilość subskrybentów IP: " + DeviceSubscriber.getNumberOfSubscribedDevices());

            if (!isServerAlreadyStarted) {
                startSendingData();
            }
        }
    }

    private void startSendingData() {
        isServerAlreadyStarted = true;
        Thread dataFrameCreatorThread = new Thread(new DataFrameSender(new DataFrameController(),new JsonSerializer(),new StringToDeviceSender()));
        dataFrameCreatorThread.start();
    }
}
