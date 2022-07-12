import Frames.DataFrame;
import Frames.DataFrameController;
import Frames.FrameTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonSerializerTest {

    DataFrameController dataFrameController;
    DataFrame dataFrame;

    @BeforeEach
    void beforeEach() {
        dataFrameController = new DataFrameController();
        dataFrame = dataFrameController.createDataFrame();
    }

    @Test
    void createJsonFrameVersionTest() {
        assertEquals("1.0", dataFrame.getAppVersion(), "Version should be 1.0");
    }@Test
    void createJsonFrameTypeTest() {
        assertEquals(FrameTypes.DATA, dataFrame.getFrameType(), "Type should be DATA");
    }

    @Test
    void createJsonFrameNotNull() {
        assertNotNull(dataFrame);
    }
}