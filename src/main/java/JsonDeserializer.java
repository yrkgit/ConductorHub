import com.google.gson.Gson;

public class JsonDeserializer {

    private Gson gson;
    private Frame frame;

    public JsonDeserializer() {
        gson = new Gson();
    }

    public LogRequestFrame deserializeJsonToFrameObject(String content) {
        //TODO add try and not null

        try{
            frame = gson.fromJson(content, Frame.class);
            System.out.println("Deserialization");
        }catch (Exception e){

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
