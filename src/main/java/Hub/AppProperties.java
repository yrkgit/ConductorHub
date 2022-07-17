package Hub;

import java.util.Properties;

public class AppProperties {
    private Properties properties;
    private static final String FILE_NAME="app.config";

    public AppProperties(Properties properties) {
        this.properties = properties;
    }
}
