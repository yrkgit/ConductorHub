import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class DataSender {
    //TODO - move port number to config
    private static final int destinationPortNumber =7802;
    private static String currentStop;
    private static String nextStop;
    private static String currentSpeed;

    private static int passengerStats;
    private static int boardingStats;
    private static int unBoardingStats;

    static {
        currentStop="Bydgoszcz Główna";
        nextStop="Bydgoszcz Wschód";
        currentSpeed="100";

        passengerStats=157;
        boardingStats=22;
        unBoardingStats=14;
    }

    public static void setCurrentStop(String currentStop) {
        DataSender.currentStop = currentStop;
    }

    public static void setNextStop(String nextStop) {
        DataSender.nextStop = nextStop;
    }

    public static void setCurrentSpeed(String currentSpeed) {
        DataSender.currentSpeed = currentSpeed;
    }

    public static void sendData(String destinationIpAddress){
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
        frameSender.sendFrame(serializedFrame.createJson(dataFrame),destinationIpAddress, destinationPortNumber);
    }
    public static void startSendingData(){
        while (!DeviceSubscriber.getListOfDevicesIps().isEmpty()) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            sendData(DeviceSubscriber.getListOfDevicesIps().get(0));
            for(String ip : DeviceSubscriber.getListOfDevicesIps()){
                DataSender.sendData(ip);
            }
        }
    }
}
