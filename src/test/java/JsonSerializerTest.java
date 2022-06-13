import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonSerializerTest {

    DataFrameCreator dataFrameCreator;

    @BeforeEach
    void beforeEach() {
        dataFrameCreator = new DataFrameCreator();
    }

    @Test
    void createJsonFrameTypeTest() {
        DataFrame dataFrame = dataFrameCreator.createDataFrame();
        assertEquals("1.0", dataFrame.appVersion, "Version should be 1.0");
    }

    @Test
    void createJsonFrameNotNull() {
        DataFrame dataFrame = dataFrameCreator.createDataFrame();
        assertNotNull(dataFrame);
    }
}