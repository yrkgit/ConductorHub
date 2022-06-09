import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class MySqlDatabaseTest {

    @Test
    void connectTest() {
        MySqlDatabase mySqlDatabase = new MySqlDatabase();
        mySqlDatabase.connect();
        try {
            assertFalse(mySqlDatabase.getConnection().isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void disconnectTest() {
        MySqlDatabase mySqlDatabase = new MySqlDatabase();
        mySqlDatabase.fetchOneParamQuery("select ? from users","name","name");
        try {
            assertTrue(mySqlDatabase.getConnection().isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void fetchOneParamQueryTest() {
        MySqlDatabase mySqlDatabase = new MySqlDatabase();
        String user=mySqlDatabase.fetchOneParamQuery("select ? from users","name","name");
        mySqlDatabase.disconnect();
        assertNotNull(user);
    }
}