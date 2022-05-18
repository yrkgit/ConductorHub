import com.google.gson.Gson;

public class JsonSerializer {

    //TODO po dodaniu builderow błędnie serializuje tylko bez 2 z 3 zmiennych z frame

    public String createJson(Frame frame){
        Gson gson = new Gson();
        String frameString = gson.toJson(frame);
        System.out.println("Serialization process " +frameString);
       return frameString;
    }
}
