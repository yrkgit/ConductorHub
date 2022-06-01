import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserPermissionToLogonVerifier {
    private LogResponseTypes logPermission;


    public LogResponseTypes verifyUserAccessPermission(User user){



        if (logPermission==null){
            logPermission=LogResponseTypes.DENIED;
        }
        return logPermission;
    }
}
