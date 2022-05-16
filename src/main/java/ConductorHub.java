import java.time.Instant;

public class ConductorHub {

    //TODO - TESTS of functionality - to remove

    public static void main(String[] args) {
        //Run socketListener to receive packets from remote Conductor
        Thread serverThread = new Thread(new ConductorServer());
        serverThread.run();

    }

    public static void sendResponse(){

        JsonSerializer serializedFrame = new JsonSerializer();
        Sender frameSender = new StringToDeviceSender();
        frameSender.sendFrame(serializedFrame.createJson(new LogRequestFrame(
                "1.0",
                FrameTypes.LOGRESPONSE,
                Instant.now().getEpochSecond(),
                "Jan",
                "haslo",
                "10.1.1.1")));
    }
}
