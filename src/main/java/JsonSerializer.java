/** Class to create String of Json from passed object of Frame type */

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonSerializer {

    private static final Logger logger = LogManager.getLogger(DeviceSubscriptionServer.class);

    public String createJson(Frame frame){
        Gson gson = new Gson();
        String frameString = gson.toJson(frame);
        logger.info("Serialization process " +frameString);
       return frameString;
    }
}
