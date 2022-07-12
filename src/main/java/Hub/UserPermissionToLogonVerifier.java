package Hub;/*
Class to verify user login credentials received in logRequest - checking if pass from user object (created from logRequestFrame) match with password from db for this user
 */


import Frames.LogResponseTypes;

public class UserPermissionToLogonVerifier {

    public LogResponseTypes verifyUserAccessPermission(User user, Database database) {
        String getPassQuery = "select password from users where name = ?";
        LogResponseTypes logPermission=null;

        String passFromDb = database.fetchOneParamQuery(getPassQuery, user.getName(), "password", new MySqlConnectionConfig());

        if (passFromDb !=null && passFromDb.equals(user.getPassword())){
            logPermission=LogResponseTypes.GRANTED;
        }
        if (logPermission == null) {
            logPermission = LogResponseTypes.DENIED;
        }
        return logPermission;
    }
}
