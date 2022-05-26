import java.time.Instant;

public class LogResponseFrameCreator {
    //TODO - move port number to config

    private final String appVersion;
    private final FrameTypes frameType;
    private  LogResponseTypes responseType;

    public LogResponseFrameCreator() {
        appVersion = "1.0";
        frameType = FrameTypes.LOGRESPONSE;
        responseType = LogResponseTypes.DENIED;
    }

    public void setResponseType(LogResponseTypes responseType) {
        this.responseType = responseType;
    }

    public LogResponseFrame createResponseFrame() {

        LogResponseFrame logResponseFrame = LogResponseFrame.builder()
                .appVersion(appVersion)
                .frameType(frameType)
                .utc(Instant.now().getEpochSecond())
                .permission(responseType)
                .build();
        System.out.println("Created frame " + logResponseFrame.toString());

        return logResponseFrame;
    }
}
