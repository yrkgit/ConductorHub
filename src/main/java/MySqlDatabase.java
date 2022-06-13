

import java.sql.*;


public class MySqlDatabase implements Database {



    private String queryResult;
    private Connection connection;
    PreparedStatement statement;
    private ResultSet resultSet;


    public Connection getConnection() {
        return connection;
    }



    @Override
    public void connect(MySqlConnectionConfig mySqlConnectionConfig) {
        try {
            Class.forName(mySqlConnectionConfig.getJdbcDriver());
            connection = DriverManager.getConnection(mySqlConnectionConfig.getDbUrl(), mySqlConnectionConfig.getDbUserName(), mySqlConnectionConfig.getDbPassword());

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
    public String fetchOneParamQuery(String query, String param,String columnLabel, MySqlConnectionConfig  mySqlConnectionConfig) {
        queryResult = null;
        connect(mySqlConnectionConfig);
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
