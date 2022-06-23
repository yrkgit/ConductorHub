import org.junit.jupiter.api.*;


import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

class MySqlDatabaseTest {
    MySqlDatabase mySqlDatabase;

    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter) {
        mySqlDatabase = new MySqlDatabase();
        testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
    }

    @Test
    @DisplayName("Connecting to DB")
    void connectTest() {
        mySqlDatabase.connect(new MySqlConnectionConfig());
        try {
            assertFalse(mySqlDatabase.getConnection().isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Disconnecting from DB")
    void disconnectTest() {
        boolean isConnected = false;
        mySqlDatabase.connect(new MySqlConnectionConfig());
        try {
            if (!mySqlDatabase.getConnection().isClosed()) {
                isConnected = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assumeTrue(isConnected);
        mySqlDatabase.fetchOneParamQuery("select ? from users", "name", "name", new MySqlConnectionConfig());
        try {
            assertTrue(mySqlDatabase.getConnection().isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Fetch from DB with one param query")
    void fetchOneParamQueryTest() {
        String user = mySqlDatabase.fetchOneParamQuery("select ? from users", "name", "name", new MySqlConnectionConfig());
        mySqlDatabase.disconnect();
        assertNotNull(user);
    }

    @Test
    @DisplayName("Throws exception when null sql connection params")
    void connectExceptionTest() {
        assertThrows(RuntimeException.class, () -> mySqlDatabase.connect(null));
    }
}