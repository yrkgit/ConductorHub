import java.time.Instant;

public class AccessResponseSender {

    public static void sendAccessResponse(String destinationIpAddress){
        JsonSerializer serializedFrame = new JsonSerializer();
        Sender frameSender = new StringToDeviceSender();
        LogResponseFrame logResponseFrame = LogResponseFrame.builder()
                .appVersion("1.0")
                .frameType(FrameTypes.LOGRESPONSE)
                .utc(Instant.now().getEpochSecond())
                .permission(LogResponseTypes.GRANTED)
                .build();
        System.out.println("Created frame " + logResponseFrame.toString());
        frameSender.sendFrame(serializedFrame.createJson(logResponseFrame),destinationIpAddress);
    }
}
