import com.google.gson.Gson;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonDeserializer {

    private static final Logger logger = LogManager.getLogger(DeviceSubscriptionServer.class);

    private Gson gson;
    private Frame frame;

    public JsonDeserializer() {
        gson = new Gson();
    }

    public LogRequestFrame deserializeJsonToFrameObject(String content) {
        //TODO add try and not null

        try{
            frame = gson.fromJson(content, Frame.class);
            logger.info("Deserialization: "+ frame.toString());
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }

/*Checking type of frame */
        if (frame.frameType.equals(FrameTypes.LOGREQUEST)) {
            return deserializeToLogRequestFrame(content);
        }
        return null;
    }

    private LogRequestFrame deserializeToLogRequestFrame(String content) {
        return gson.fromJson(content, LogRequestFrame.class);
    }
}
