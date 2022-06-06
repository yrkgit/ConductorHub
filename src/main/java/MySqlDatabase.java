import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;


public class MySqlDatabase implements Database {

    private static final Logger logger = LogManager.getLogger(DeviceSubscriptionServer.class);
    private static final String dbUserName;
    private static final String dbPassword;
    private static final String dbUrl;
    private String queryResult;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private static final String jdbcDriver;

    static {
        dbUserName = "root";
        dbPassword = "root";
        dbUrl = "jdbc:mysql://localhost:3306/conductordb";
        jdbcDriver= "com.mysql.cj.jdbc.Driver";
    }

    @Override
    public void connect() {
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void disconnect() {
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void modifyQuery() {

    }

    @Override
    public String fetchQuery(String query) {
        queryResult = null;
        connect();
        try {
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                queryResult = resultSet.getString("password");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        disconnect();
        return queryResult;
    }
}
