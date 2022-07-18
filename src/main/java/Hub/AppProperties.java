package Hub;

import org.apache.logging.log4j.util.PropertiesUtil;

import java.io.*;
import java.util.Properties;

public final class AppProperties {
    private Properties properties;
    private static final String FILE_NAME="src\\main\\resources\\config.properties";
    public final String appVersion;
    public final String dbUser;
    public final String dbPass;
    public final String dbUrl;

    public AppProperties(Properties properties) {
        this.properties = properties;
        try (FileReader reader = new FileReader(FILE_NAME)) {
            properties.load(reader);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 1");
        } catch (IOException ex) {
            System.out.println("Error 2");
        }
        appVersion=properties.getProperty("app.version");
        dbUser=properties.getProperty("db.user");
        dbPass=properties.getProperty("db.pass");
        dbUrl=properties.getProperty("db.url");

    }

}