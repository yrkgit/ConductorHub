import java.time.Instant;

public class AccessResponseSender {
    //TODO - move port number to config
    private static final int destinationPortNumber =7801;

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
        frameSender.sendFrame(serializedFrame.createJson(logResponseFrame),destinationIpAddress, destinationPortNumber);
        if (logResponseFrame.getPermission().equals(LogResponseTypes.GRANTED)){
            SubscribeDevice.addDeviceIp(destinationIpAddress);
            System.out.println("Ilość subskrybentów IP: " + SubscribeDevice.getNumberOfDevicesIps());
            for(String ip : SubscribeDevice.getListOfDevicesIps()){
                System.out.println(ip);
                DataSender.sendData(ip);
            }

        }

    }
}
