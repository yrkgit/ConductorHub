public class FrameToDeviceSender {

    private SendableFrame frame;

    public FrameToDeviceSender(SendableFrame frame) {
        this.frame = frame;
    }

    public void sendToDevice() {
        System.out.println("Sending to device");
        frame.sendFrame();

    }
}
