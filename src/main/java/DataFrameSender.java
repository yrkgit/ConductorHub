import java.util.concurrent.TimeUnit;

public class DataFrameSender implements Runnable {

    //TODO - move port number to config
    private static final int DESTINATION_PORT_NUMBER = 7802;
    private static final int TIME_BETWEEN_DATA_FRAME_IS_SENT = 10;

    private DataFrameCreator dataFrameCreator;
    private JsonSerializer serializedFrame;
    private Sender frameSender;


    public DataFrameSender(DataFrameCreator dataFrameCreator, JsonSerializer serializedFrame, Sender frameSender) {
        this.dataFrameCreator = dataFrameCreator;
        this.serializedFrame = serializedFrame;
        this.frameSender = frameSender;
    }

    @Override
    public void run() {
        while (!DataFrameReceiverDeviceSubscriber.getListOfSubscribedDevicesIpAddresses().isEmpty()) {
            try {
                TimeUnit.SECONDS.sleep(TIME_BETWEEN_DATA_FRAME_IS_SENT);
            } catch (InterruptedException e) {
                FileLogger.logger.error(e.getMessage());
                e.printStackTrace();

            }
            for (String destinationIpAddress : DataFrameReceiverDeviceSubscriber.getListOfSubscribedDevicesIpAddresses()) {
                DataFrame dataFrame = dataFrameCreator.createDataFrame();
                frameSender.sendFrame(serializedFrame.createJson(dataFrame), destinationIpAddress, DESTINATION_PORT_NUMBER);
            }
        }
    }
}
