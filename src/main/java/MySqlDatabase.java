

import java.sql.*;


public class MySqlDatabase implements Database {

    private static final String dbUserName;
    private static final String dbPassword;
    private static final String dbUrl;
    private String queryResult;
    private Connection connection;
    PreparedStatement statement;
    private ResultSet resultSet;
    private static final String jdbcDriver;

    public Connection getConnection() {
        return connection;
    }

    static {
        dbUserName = "root";
        dbPassword = "root";
        dbUrl = "jdbc:mysql://localhost:3306/conductordb";
        jdbcDriver = "com.mysql.cj.jdbc.Driver";
    }

    @Override
    public void connect() {
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

        } catch (ClassNotFoundException | SQLException e) {
            FileLogger.logger.error(e.getMessage());
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
            FileLogger.logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void modifyQuery() {

    }

    @Override
    public String fetchOneParamQuery(String query, String param,String columnLabel) {
        queryResult = null;
        connect();
        try {
            statement= connection.prepareStatement(query);
            statement.setString(1,param);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                queryResult = resultSet.getString(columnLabel);
            }
        } catch (SQLException e) {
            FileLogger.logger.error(e.getMessage());
            e.printStackTrace();
        }finally {
            disconnect();
        }
        return queryResult;
    }
}
