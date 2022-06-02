public class UserPermissionToLogonVerifier {
    private LogResponseTypes logPermission;

    private String passFromSql;

    public LogResponseTypes verifyUserAccessPermission(User user, Database database) {
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
