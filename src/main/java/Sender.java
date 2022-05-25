@FunctionalInterface
public interface Sender {
    void sendFrame(String content, String destinationIpAddress, int destinationPortNumber);
}
