import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class JsonSerializerTest {

    DataFrameCreator dataFrameCreator;
    DataFrame dataFrame;

    @BeforeEach
    void beforeEach(TestInfo testInfo, TestReporter testReporter) {
        dataFrameCreator = new DataFrameCreator();
        dataFrame = dataFrameCreator.createDataFrame();
        testReporter.publishEntry("Running "+ testInfo.getDisplayName());

    }

    @Test
    @DisplayName("Checking frame version after serialization to JSON")
    void createJsonFrameVersionTest() {
        assertEquals("1.0", dataFrame.appVersion, "Version should be 1.0");
    }

    @Test
    @DisplayName("Checking frame type after serialization to JSON")
    void createJsonFrameTypeTest() {
        assertEquals(FrameTypes.DATA, dataFrame.frameType, "Type should be DATA");
    }


    @Test
    @DisplayName("Checking if not null")
    void createJsonFrameNotNull() {
        assertNotNull(dataFrame);
    }
}