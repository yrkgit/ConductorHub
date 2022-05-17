import com.google.gson.Gson;

public class JsonDeserializer {


    public Frame deserializeJsonToFrameObject(String content){
        //TODO add try and not null
        Gson gson = new Gson();
        Frame frame=gson.fromJson(content,Frame.class);
        System.out.println(frame);
        ConductorHub.sendResponse();
//        if (frame.getFrameType().equals(FrameTypes.LOGREQUEST)){
//            LogRequestFrame receivedFrame = gson.fromJson(content, LogRequestFrame.class);
//            System.out.println(receivedFrame.getUser()+ " is trying to connect");
//            ConductorHub.sendResponse();
//            return receivedFrame;
//        }

        return frame;
    }
}
