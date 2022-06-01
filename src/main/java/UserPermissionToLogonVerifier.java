import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserPermissionToLogonVerifier {
    private LogResponseTypes logPermission;
    private MySqlDatabase mySqlDatabase = new MySqlDatabase();
    private String passFromSql;

    public LogResponseTypes verifyUserAccessPermission(User user) {

        passFromSql = mySqlDatabase.fetchQuery("select password from users where name='" + user.getName() + "'");

        if (passFromSql!=null && passFromSql.equals(user.getPassword())){
            logPermission=LogResponseTypes.GRANTED;
        }
        if (logPermission == null) {
            logPermission = LogResponseTypes.DENIED;
        }
        return logPermission;
    }
}
