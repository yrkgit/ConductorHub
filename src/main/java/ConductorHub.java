/**
 * Main class, starts na new Thread with Device Subscription Server (awaiting new clients)
 */
public class ConductorHub {

    private static ConductorHub conductorHub;
    private Thread serverThread;

    static {
        conductorHub = new ConductorHub();
    }

    public static void main(String[] args) {
        conductorHub.startConductorHub();
    }

    //Run socketListener to receive packets from remote Conductor
    public void startConductorHub() {
        serverThread =new Thread(new DeviceSubscriptionServer());
        serverThread.start();

    }


}
