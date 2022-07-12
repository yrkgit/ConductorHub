package Hub; /** Class to create String of Json from passed object of Frames.Frame type */

import Frames.FrameHeader;
import com.google.gson.Gson;


public class JsonSerializer {


    public String createJson(FrameHeader frameHeader){
        Gson gson = new Gson();
        String frameString = gson.toJson(frameHeader);
        FileLogger.logger.info("Serialization process " +frameString);
       return frameString;
    }
}
