public interface Database {
    void connect();
    void disconnect();
    void modifyQuery();
    String fetchQuery(String query);
}
