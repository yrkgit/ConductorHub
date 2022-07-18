package subscription;

import Frames.*;
import Hub.*;

/**
 * Runnable class that opening socket (by invoking Hub.SocketListener) and starting listening to communication from remote hosts. When get response from Hub.SocketListener pass it to JSON deserializer
 */


public class DeviceSubscriptionServer implements Runnable {



//TODO Create anonymous inner classes for object used once (from classes with one method)



    private final SocketListener socketListener;
    private final CapturedFrameProcessor capturedFrameProcessor;
    private final DeviceSubscriber deviceSubscriber;
    private final SubscriptionResponse subscriptionResponse;

    private LogRequestFrame logRequestFrame;
    private String content;
    private boolean isServerRunning;


    public DeviceSubscriptionServer() {

        this.socketListener = new SocketListener();
        this.isServerRunning = true;
        this.capturedFrameProcessor = new CapturedFrameProcessor();
        this.deviceSubscriber = new DeviceSubscriber();
        subscriptionResponse = new SubscriptionResponse();
    }

    @Override
    public void run() {
        try {
            while (isServerRunning) {
                FileLogger.logger.info("Start receiving packets... ");
                content = socketListener.startSocketListener();
                FileLogger.logger.info("Captured content from socket " + content);
                logRequestFrame = capturedFrameProcessor.processCapturedFrame(content);


                subscriptionResponse.setPermission(logRequestFrame);

                LogResponseFrame logResponseFrame = subscriptionResponse.prepareLogResponseFrame(logRequestFrame);

                if (logResponseFrame.getPermission().equals(LogResponseTypes.GRANTED)) {
                    deviceSubscriber.subscribeDevice(logRequestFrame);
                }
            }
        } catch (Exception e) {
            FileLogger.logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
