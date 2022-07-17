package Hub; /** Class to create String of Json from passed object of Frames.Frame type */

import Frames.Frame;
import com.google.gson.Gson;


public class JsonSerializer {


    public String createJsonFromFrame(Frame frame){
        Gson gson = new Gson();
        String frameString = gson.toJson(frame);
        FileLogger.logger.info("Serialization process " +frameString);
       return frameString;
    }
}
