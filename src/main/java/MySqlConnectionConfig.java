public class MySqlConnectionConfig {

    private final String dbUserName;
    private final String dbPassword;
    private final String dbUrl;
    private final String jdbcDriver;

    public MySqlConnectionConfig() {
        dbUserName = "root";
        dbPassword = "root";
        dbUrl = "jdbc:mysql://localhost:3306/conductordb";
        jdbcDriver = "com.mysql.cj.jdbc.Driver";
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

}
