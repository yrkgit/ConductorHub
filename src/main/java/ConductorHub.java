public class ConductorHub {


    public static void main(String[] args) {
        ConductorHub conductorHub = new ConductorHub();
        conductorHub.startConductorHub();
    }

    public void startConductorHub() {
        //Run socketListener to receive packets from remote Conductor
        Thread serverThread = new Thread(new DeviceSubscriptionServer());
        serverThread.start();

    }


}
