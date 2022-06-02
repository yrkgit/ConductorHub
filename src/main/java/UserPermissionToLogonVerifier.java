/*
Class to verify user login credentials received in logRequest - checking if pass from user object (created from logRequestFrame) match with password from db for this user
 */

public class UserPermissionToLogonVerifier {


    private String passFromSql;

    public LogResponseTypes verifyUserAccessPermission(User user, Database database) {
        LogResponseTypes logPermission=null;
        passFromSql = database.fetchQuery("select password from users where name='" + user.getName() + "'");

        if (passFromSql!=null && passFromSql.equals(user.getPassword())){
            logPermission=LogResponseTypes.GRANTED;
        }
        if (logPermission == null) {
            logPermission = LogResponseTypes.DENIED;
        }
        return logPermission;
    }
}
