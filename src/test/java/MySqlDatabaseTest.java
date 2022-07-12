import Hub.MySqlConnectionConfig;
import Hub.MySqlDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

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
        boolean isConnected = false;
        mySqlDatabase.connect(new MySqlConnectionConfig());
        try {
            if(!mySqlDatabase.getConnection().isClosed()){
                isConnected=true;
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