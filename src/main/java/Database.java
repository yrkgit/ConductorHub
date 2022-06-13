public interface Database {

    void connect(MySqlConnectionConfig mySqlConnectionConfig);

    void disconnect();
    void modifyQuery();
    String fetchOneParamQuery(String query, String param, String columnLabel, MySqlConnectionConfig mySqlConnectionConfig);
}
