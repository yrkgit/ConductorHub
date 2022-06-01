public interface Database {
    void connect();
    void disconnect();
    void modifyQuery();
    void fetchQuery();
}
