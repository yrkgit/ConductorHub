import java.util.concurrent.TimeUnit;

public class DataFrameSender implements Runnable {

    //TODO - move port number to config
    private static final int DESTINATION_DATA_PORT_NUMBER = 7802;
    private static final int FRAME_SENDING_INTERVAL_IN_SECONDS = 10;

    private final DataFrameCreator dataFrameCreator;
    private final JsonSerializer serializedFrame;
    private final Sender frameSender;


    public DataFrameSender(DataFrameCreator dataFrameCreator, JsonSerializer serializedFrame, Sender frameSender) {
        this.dataFrameCreator = dataFrameCreator;
        this.serializedFrame = serializedFrame;
        this.frameSender = frameSender;
    }

    @Override
    public void run() {
        while (!DeviceSubscriber.getListOfDevicesIps().isEmpty()) {
            try {
                TimeUnit.SECONDS.sleep(FRAME_SENDING_INTERVAL_IN_SECONDS);
            } catch (InterruptedException e) {
                FileLogger.logger.error(e.getMessage());
                e.printStackTrace();

            }
            for (String destinationIpAddress : DeviceSubscriber.getListOfDevicesIps()) {
                DataFrame dataFrame = dataFrameCreator.createDataFrame();
                frameSender.sendFrame(serializedFrame.createJson(dataFrame), destinationIpAddress, DESTINATION_DATA_PORT_NUMBER);
            }
        }
    }
}
