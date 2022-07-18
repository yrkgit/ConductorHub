package subscription;

import Frames.LogRequestFrame;
import Hub.FileLogger;
import Hub.JsonDeserializer;

public class CapturedFrameProcessor {

    private final JsonDeserializer deserializer;
    private LogRequestFrame logRequestFrame;

    public CapturedFrameProcessor() {
        this.deserializer = new JsonDeserializer();
    }

    public LogRequestFrame processCapturedFrame(String content) {
        logRequestFrame = deserializer.deserializeJsonToLogRequestFrameObject(content);
        if (logRequestFrame != null) {
            FileLogger.logger.info(logRequestFrame.getUser() + " is trying to connect");
        }
        return logRequestFrame;
    }
}
