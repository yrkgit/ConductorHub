/*
Class to verify user login credentials received in logRequest - checking if pass from user object (created from logRequestFrame) match with password from db for this user
 */


public class UserPermissionToLogonVerifier {

    public LogResponseTypes verifyUserAccessPermission(User user, Database database) {
        String getPassQuery = "select password from users where name = ?";
        LogResponseTypes logPermission=null;

        String passFromDb = database.fetchOneParamQuery(getPassQuery, user.getName());

        if (passFromDb !=null && passFromDb.equals(user.getPassword())){
            logPermission=LogResponseTypes.GRANTED;
        }
        if (logPermission == null) {
            logPermission = LogResponseTypes.DENIED;
        }
        return logPermission;
    }
}
