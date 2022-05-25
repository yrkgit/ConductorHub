import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class ConductorHub {


    public static void main(String[] args) {
        ConductorHub conductorHub = new ConductorHub();
        conductorHub.startConductorHub();
    }

    public void startConductorHub() {
        //Run socketListener to receive packets from remote Conductor
        Thread serverThread = new Thread(new ConductorServer());
        serverThread.start();

        //TODO do poprawy
        while (SubscribeDevice.getNumberOfDevicesIps() > 0) {
            System.out.println("Próba wysłania ramki danych");
            DataSender.sendData(SubscribeDevice.getListOfDevicesIps().get(0));
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
