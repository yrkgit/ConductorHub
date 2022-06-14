import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonSerializerTest {

    DataFrameCreator dataFrameCreator;
    DataFrame dataFrame;

    @BeforeEach
    void beforeEach() {
        dataFrameCreator = new DataFrameCreator();
        dataFrame = dataFrameCreator.createDataFrame();
    }

    @Test
    void createJsonFrameVersionTest() {
        assertEquals("1.0", dataFrame.appVersion, "Version should be 1.0");
    }@Test
    void createJsonFrameTypeTest() {
        assertEquals(FrameTypes.DATA, dataFrame.frameType, "Type should be DATA");
    }

    @Test
    void createJsonFrameNotNull() {
        assertNotNull(dataFrame);
    }
}