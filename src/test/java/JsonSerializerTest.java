import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonSerializerTest {

    @Test
    void createJsonFrameTypeTest() {
        DataFrameCreator dataFrameCreator = new DataFrameCreator();
        DataFrame dataFrame = dataFrameCreator.createDataFrame();
        assertEquals("1.0",dataFrame.appVersion);
    }
    @Test
    void createJsonFrameNotNull() {
        DataFrameCreator dataFrameCreator = new DataFrameCreator();
        DataFrame dataFrame = dataFrameCreator.createDataFrame();
        assertNotNull(dataFrame);
    }
}