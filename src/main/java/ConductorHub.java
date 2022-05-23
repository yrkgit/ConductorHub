import java.time.Instant;

public class ConductorHub {


    public static void main(String[] args) {
        //Run socketListener to receive packets from remote Conductor
        Thread serverThread = new Thread(new ConductorServer());
        serverThread.run();
    }
}
