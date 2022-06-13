import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonDeserializerTest {

    static String receivedStringToTest;
    JsonDeserializer jsonDeserializer;

    @BeforeAll
    static void beforeAll() {
        receivedStringToTest = "{\"ipAddress\":\"0.0.0.0\",\"pass\":\"zxc\",\"user\":\"xxx\",\"appVersion\":\"x\",\"frameType\":\"LOGREQUEST\",\"utc\":1}";
    }

    @BeforeEach
    void beforeEach() {
        jsonDeserializer = new JsonDeserializer();
    }

    @Test
    void deserializeJsonToLogRequestFrameObjectNotNullTest() {
        LogRequestFrame logRequestFrame = jsonDeserializer.deserializeJsonToLogRequestFrameObject(receivedStringToTest);
        assertNotNull(logRequestFrame);
    }

    @Test
    void deserializeJsonToLogRequestFrameObjectTest() {
        LogRequestFrame logRequestFrame = jsonDeserializer.deserializeJsonToLogRequestFrameObject(receivedStringToTest);
        assert (logRequestFrame.frameType.equals(FrameTypes.LOGREQUEST));
    }
}