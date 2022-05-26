

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class DataFrameCreator {
    //TODO - move port number to config
    private final int destinationDataPortNumber = 7802;
    private String currentStop;
    private String nextStop;
    private String currentSpeed;

    private int passengerStats;
    private int boardingStats;
    private int unBoardingStats;

    //TODO - do testów usunąć
    DataFrameCreator() {
        currentStop = "Bydgoszcz Główna";
        nextStop = "Bydgoszcz Wschód";
        currentSpeed = "100";

        passengerStats = 157;
        boardingStats = 22;
        unBoardingStats = 14;
    }


    public void startSendingData() {
        while (!DeviceSubscriber.getListOfDevicesIps().isEmpty()) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (String ip : DeviceSubscriber.getListOfDevicesIps()) {
                sendData(ip);
            }
        }
    }

    public void sendData(String destinationIpAddress) {
        JsonSerializer serializedFrame = new JsonSerializer();
        Sender frameSender = new StringToDeviceSender();
        DataFrame dataFrame = DataFrame.builder()
                .appVersion("1.0")
                .frameType(FrameTypes.DATA)
                .utc(Instant.now().getEpochSecond())
                .currentStop(currentStop)
                .nextStop(nextStop)
                .currentSpeed(currentSpeed)
                .passengerStats(passengerStats)
                .boardingStats(boardingStats)
                .unBoardingStats(unBoardingStats)
                .build();
        System.out.println("Created data frame " + dataFrame.toString());
        frameSender.sendFrame(serializedFrame.createJson(dataFrame), destinationIpAddress, destinationDataPortNumber);
    }
}
