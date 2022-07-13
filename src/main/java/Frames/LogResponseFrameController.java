import java.time.Instant;

public class LogResponseFrameController {

    private final String appVersion;
    private final FrameTypes frameType;
    private LogResponseTypes responseType;

    public LogResponseFrameController() {
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
        FileLogger.logger.info("Created frame " + logResponseFrame.toString());

        return logResponseFrame;
    }
}
