import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDatabase implements Database {
    private static final String dbUserName;
    private static final String dbPassword;
    private static final String dbUrl;

    static {
        dbUserName = "TestUser";
        dbPassword = "TestPassword";
        dbUrl="";
    }

    @Override
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void disconnect() {

    }

    @Override
    public void modifyQuery() {

    }

    @Override
    public void fetchQuery() {

    }
}
