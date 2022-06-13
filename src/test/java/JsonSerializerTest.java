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
    void createJsonFrameTypeTest() {
        assertEquals("1.0", dataFrame.appVersion, "Version should be 1.0");
    }

    @Test
    void createJsonFrameNotNull() {
        assertNotNull(dataFrame);
    }
}