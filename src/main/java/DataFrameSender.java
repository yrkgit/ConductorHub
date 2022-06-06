

import java.util.concurrent.TimeUnit;

public class DataFrameSender implements Runnable {

    //TODO - move port number to config
    private final int destinationDataPortNumber = 7802;
    private DataFrameCreator dataFrameCreator;
    JsonSerializer serializedFrame;
    Sender frameSender;
    DataFrame dataFrame;

    public DataFrameSender() {
        dataFrameCreator = new DataFrameCreator();
        serializedFrame = new JsonSerializer();
        frameSender = new StringToDeviceSender();
    }

    @Override
    public void run() {
        while (!DeviceSubscriber.getListOfDevicesIps().isEmpty()) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                FileLogger.logger.error(e.getMessage());
                e.printStackTrace();

            }
            for (String destinationIpAddress : DeviceSubscriber.getListOfDevicesIps()) {
                dataFrame = dataFrameCreator.createDataFrame();
                frameSender.sendFrame(serializedFrame.createJson(dataFrame), destinationIpAddress, destinationDataPortNumber);
            }
        }
    }
}
