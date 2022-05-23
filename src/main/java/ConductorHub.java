import java.time.Instant;

public class ConductorHub {

    //TODO - TESTS of functionality - to remove

    public static void main(String[] args) {
        //Run socketListener to receive packets from remote Conductor
        Thread serverThread = new Thread(new ConductorServer());
        serverThread.run();
    }

    public static void sendResponse(String destinationIpAddress) {

        JsonSerializer serializedFrame = new JsonSerializer();
        Sender frameSender = new StringToDeviceSender();
        LogResponseFrame logResponseFrame = LogResponseFrame.builder()
                .appVersion("1.0")
                .frameType(FrameTypes.LOGRESPONSE)
                .utc(Instant.now().getEpochSecond())
                .permission(LogResponseTypes.GRANTED)
                .build();
        System.out.println("świeżo stworzona rameczka " + logResponseFrame.toString());
        frameSender.sendFrame(serializedFrame.createJson(logResponseFrame),destinationIpAddress);

    }
}
