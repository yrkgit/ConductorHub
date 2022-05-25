import com.google.gson.Gson;

public class JsonDeserializer {


    private Gson gson;

    public Frame deserializeJsonToFrameObject(String content){
        //TODO add try and not null
        gson = new Gson();
        Frame frame= gson.fromJson(content,Frame.class);
        System.out.println("Deserialization");

        if (frame.frameType.equals(FrameTypes.LOGREQUEST)){
            return deserializeToLogRequestFrame(content);
        }
        return frame;
    }

    private LogRequestFrame deserializeToLogRequestFrame(String content) {
        LogRequestFrame receivedFrame = gson.fromJson(content, LogRequestFrame.class);
        System.out.println(receivedFrame.getUser()+ " is trying to connect");
        AccessResponseSender.sendAccessResponse(receivedFrame.getIpAddress());
        return receivedFrame;
    }
}
