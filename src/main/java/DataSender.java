import java.time.Instant;

public class DataSender {
    //TODO - move port number to config
    private static final int destinationPortNumber =7801;
    private static String currentStop;
    private static String nextStop;
    private static String currentSpeed;

    static {
        currentStop="Bydgoszcz Główna";
        nextStop="Bydgoszcz Wschód";
        currentSpeed="100";
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
                .build();
        System.out.println("Created frame " + dataFrame.toString());
        frameSender.sendFrame(serializedFrame.createJson(dataFrame),destinationIpAddress, destinationPortNumber);
    }
}
