import java.sql.*;

public class MySqlDatabase implements Database {
    private static final String dbUserName;
    private static final String dbPassword;
    private static final String dbUrl;
    private String queryResult;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    static {
        dbUserName = "root";
        dbPassword = "root";
        dbUrl = "jdbc:mysql://localhost:3306/conductordb";
    }

    @Override
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
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
                System.out.println(queryResult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return queryResult;
    }
}
