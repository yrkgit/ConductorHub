import java.time.Instant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogResponseFrameCreator {

    private static final Logger logger = LogManager.getLogger(DeviceSubscriptionServer.class);
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
        logger.info("Created frame " + logResponseFrame.toString());

        return logResponseFrame;
    }
}
