import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class MySqlDatabaseTest {
    MySqlDatabase mySqlDatabase;

    @BeforeEach
    void init() {
         mySqlDatabase = new MySqlDatabase();
    }

    @Test
    void connectTest() {

        mySqlDatabase.connect(new MySqlConnectionConfig());
        try {
            assertFalse(mySqlDatabase.getConnection().isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void disconnectTest() {
        mySqlDatabase.fetchOneParamQuery("select ? from users", "name", "name", new MySqlConnectionConfig());
        try {
            assertTrue(mySqlDatabase.getConnection().isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void fetchOneParamQueryTest() {
        String user = mySqlDatabase.fetchOneParamQuery("select ? from users", "name", "name", new MySqlConnectionConfig());
        mySqlDatabase.disconnect();
        assertNotNull(user);
    }

    @Test
    void connectExceptionTest() {
        assertThrows(RuntimeException.class, () -> mySqlDatabase.connect(null));
    }
}