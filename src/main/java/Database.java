public interface Database {
    void connect();
    void disconnect();
    void modifyQuery();
    String fetchOneParamQuery(String query, String param, String columnLabel);
}
