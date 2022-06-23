import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class JsonDeserializerTest {

    static String receivedStringToTest;
    JsonDeserializer jsonDeserializer;

    @BeforeAll
    static void beforeAll() {
        receivedStringToTest = "{\"ipAddress\":\"0.0.0.0\",\"pass\":\"zxc\",\"user\":\"xxx\",\"appVersion\":\"x\",\"frameType\":\"LOGREQUEST\",\"utc\":1}";
    }

    @BeforeEach
    void beforeEach(TestInfo testInfo, TestReporter testReporter) {
        jsonDeserializer = new JsonDeserializer();
        testReporter.publishEntry("Running "+ testInfo.getDisplayName());
    }

    @Test
    @DisplayName("Checking frame not null after deserializing from JSON")
    void deserializeJsonToLogRequestFrameObjectNotNullTest() {
        LogRequestFrame logRequestFrame = jsonDeserializer.deserializeJsonToLogRequestFrameObject(receivedStringToTest);
        assertNotNull(logRequestFrame);
    }

    @Test
    @DisplayName("Checking frame type after deserializing from JSON")
    void deserializeJsonToLogRequestFrameObjectTest() {
        LogRequestFrame logRequestFrame = jsonDeserializer.deserializeJsonToLogRequestFrameObject(receivedStringToTest);
        assertEquals(FrameTypes.LOGREQUEST, logRequestFrame.frameType, "Type should be LOGREQUEST");
    }
}