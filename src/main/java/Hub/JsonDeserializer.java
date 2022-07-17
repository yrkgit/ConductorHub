package Hub;

import Frames.Frame;
import Frames.FrameTypes;
import Frames.LogRequestFrame;
import com.google.gson.Gson;

public class JsonDeserializer {


    private Gson gson;
    private Frame frame;

    public JsonDeserializer() {
        gson = new Gson();
    }

    public LogRequestFrame deserializeJsonToLogRequestFrameObject(String content) {
        try {
            frame = gson.fromJson(content, Frame.class);
            FileLogger.logger.info("Deserialization: " + frame.toString());
        } catch (Exception e) {
            FileLogger.logger.error(e.getMessage());
            e.printStackTrace();
        }
        if (checkIfFrameTypeIsLogRequest(content)) {
            return deserializeToLogRequestFrame(content);
        }
        return null;
    }

    private boolean checkIfFrameTypeIsLogRequest(String content) {
        if (frame.getFrameType().equals(FrameTypes.LOGREQUEST)) {
            return true;
        }
        return false;
    }

    private LogRequestFrame deserializeToLogRequestFrame(String content) {
        return gson.fromJson(content, LogRequestFrame.class);
    }
}
