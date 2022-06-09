/** Class to create String of Json from passed object of Frame type */

import com.google.gson.Gson;


public class JsonSerializer {


    public String createJson(Frame frame){
        Gson gson = new Gson();
        String frameString = gson.toJson(frame);
        FileLogger.logger.info("Serialization process " +frameString);
       return frameString;
    }
}
