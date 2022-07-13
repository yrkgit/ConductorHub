package Hub;

import Frames.FrameHeader;
import Frames.FrameTypes;
import Frames.LogRequestFrameHeader;
import com.google.gson.Gson;

public class JsonDeserializer {


    private Gson gson;
    private FrameHeader frameHeader;

    public JsonDeserializer() {
        gson = new Gson();
    }

    public LogRequestFrameHeader deserializeJsonToLogRequestFrameObject(String content) {
        try {
            frameHeader = gson.fromJson(content, FrameHeader.class);
            FileLogger.logger.info("Deserialization: " + frameHeader.toString());
        } catch (Exception e) {
            FileLogger.logger.error(e.getMessage());
            e.printStackTrace();
        }
        /*Checking type of frame */
        if (frameHeader.getFrameType().equals(FrameTypes.LOGREQUEST)) {
            return deserializeToLogRequestFrame(content);
        }
        return null;
    }

    private LogRequestFrameHeader deserializeToLogRequestFrame(String content) {
        return gson.fromJson(content, LogRequestFrameHeader.class);
    }
}
