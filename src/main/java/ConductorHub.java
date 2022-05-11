import java.time.Instant;

public class ConductorHub {
    public static void main(String[] args) {
        //Run socketListener to receive packets from remote Conductor
//        Thread serverThread = new Thread(new ConductorServer());
//        serverThread.run();

        JsonCreator serializedFrame = new JsonCreator();
        serializedFrame.createJson(new FrameLogRequest("1.0",FrameTypes.LOGREQUEST, Instant.now().getEpochSecond(),"Jan","haslo","10.1.1.1"));
    }
    public static void setResponse(){
        System.out.println("Set Response to device");
        FrameToDeviceSender frameSender = new FrameToDeviceSender(new JsonToDeviceSender());
        frameSender.sendToDevice();


    }
}
