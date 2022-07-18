package Hub;

import java.util.Properties;

public class MySqlConnectionConfig {

    private final String dbUserName;
    private final String dbPassword;
    private final String dbUrl;
    private final String jdbcDriver;

    private final AppProperties appProperties;

    //TODO - DI
    public MySqlConnectionConfig() {
        this.appProperties = new AppProperties(new Properties());

        dbUserName = appProperties.dbUser;
        dbPassword = appProperties.dbPass;
        dbUrl = "jdbc:mysql:"+appProperties.dbUrl;
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
