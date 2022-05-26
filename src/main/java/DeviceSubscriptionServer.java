/** Runnable class that opening socket (by invoking SocketListener) and starting listening to communication from remote hosts. When get response from SocketListener pass it to JSON deserializer */

public class DeviceSubscriptionServer extends SocketListener implements Runnable {
    private static String content;
    private boolean isServerRunning;
    private JsonDeserializer deserializer;

    private LogResponseFrameCreator logResponseFrameCreator = new LogResponseFrameCreator();
    LogResponseFrame logResponseFrame;
    JsonSerializer serializedFrame = new JsonSerializer();
    Sender frameSender = new StringToDeviceSender();
    private static final int destinationLogPortNumber = 7801;
    private LogRequestFrame receivedFrame;

    public DeviceSubscriptionServer() {
        isServerRunning = true;
        deserializer=new JsonDeserializer();
    }

    public void stopServer() {
        isServerRunning = false;
    }

    public void startServer() {
        isServerRunning = true;
    }


    @Override
    public void run() {

        try {

            while (isServerRunning) {
                System.out.println("Start receiving packets... ");
                content=startSocketListener();
                System.out.println("Captured content from socket "+content);
                processCapturedFrame();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void processCapturedFrame(){

        receivedFrame=deserializer.deserializeJsonToFrameObject(content);

        if (receivedFrame!=null){
            System.out.println(receivedFrame.getUser() + " is trying to connect");

            //TODO add user authentication from sql base
            logResponseFrameCreator.setResponseType(LogResponseTypes.GRANTED);


            logResponseFrame = logResponseFrameCreator.createResponseFrame();
            frameSender.sendFrame(serializedFrame.createJson(logResponseFrame), receivedFrame.getIpAddress(), destinationLogPortNumber);

            if (logResponseFrame.getPermission().equals(LogResponseTypes.GRANTED)) {
                subscribeDevice();
            }
        }

    }
    private void subscribeDevice(){
        DeviceSubscriber.addDeviceIp(receivedFrame.getIpAddress());
        DeviceSubscriber.addDeviceUser(receivedFrame.getUser());
        System.out.println("Dodano urządzenie: "+receivedFrame.getUser() + " with IP address: " + receivedFrame.getIpAddress() + " Ilość subskrybentów IP: " + DeviceSubscriber.getNumberOfDevicesIps());
        DataFrameCreator.startSendingData();
    }
}
