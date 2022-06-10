import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonDeserializerTest {

    @Test
    void deserializeJsonToLogRequestFrameObjectNotNullTest() {
        String receivedStringToTest = "{\"ipAddress\":\"0.0.0.0\",\"pass\":\"zxc\",\"user\":\"xxx\",\"appVersion\":\"x\",\"frameType\":\"LOGREQUEST\",\"utc\":1}";
        JsonDeserializer jsonDeserializer = new JsonDeserializer();
        LogRequestFrame logRequestFrame = jsonDeserializer.deserializeJsonToLogRequestFrameObject(receivedStringToTest);
        assertNotNull(logRequestFrame);
    }

    @Test
    void deserializeJsonToLogRequestFrameObjectTest() {
        String receivedStringToTest = "{\"ipAddress\":\"0.0.0.0\",\"pass\":\"xxx\",\"user\":\"xxx\",\"appVersion\":\"x\",\"frameType\":\"LOGREQUEST\",\"utc\":1}";
        JsonDeserializer jsonDeserializer = new JsonDeserializer();
        LogRequestFrame logRequestFrame = jsonDeserializer.deserializeJsonToLogRequestFrameObject(receivedStringToTest);
        assert (logRequestFrame.frameType.equals(FrameTypes.LOGREQUEST));
    }
}