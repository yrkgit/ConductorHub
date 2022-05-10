public class FrameToDeviceSender {

    private FrameSender frame;

    public FrameToDeviceSender(FrameSender frame) {
        this.frame = frame;
    }

    public void sendToDevice() {
        System.out.println("Sending to device");
        frame.sendFrame();

    }
}
