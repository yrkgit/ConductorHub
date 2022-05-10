public class ConductorHub {
    public static void main(String[] args) {
        //Run socketListener to receive packets from remote Conductor
        Thread serverThread = new Thread(new ConductorServer());
        serverThread.run();
    }
    public static void setResponse(){
        System.out.println("Set Response to device");
        FrameToDeviceSender frameSender = new FrameToDeviceSender(new JsonToDeviceSender());
        frameSender.sendToDevice();
    }
}
