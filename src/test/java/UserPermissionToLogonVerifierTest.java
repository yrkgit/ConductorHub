import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserPermissionToLogonVerifierTest {

    @Test
    void verifyUserAccessPermissionGrantedTest() {
        UserPermissionToLogonVerifier userPermissionToLogonVerifier = new UserPermissionToLogonVerifier();
        LogResponseTypes logResponseTypes = userPermissionToLogonVerifier.verifyUserAccessPermission(new User.UserBuilder()
                        .name("qwerty")
                        .password("zxc")
                        .build()
                , new MySqlDatabase());
        assertEquals(logResponseTypes, LogResponseTypes.GRANTED);
    }

    @Test
    void verifyUserAccessPermissionEmptyUserTest() {
        UserPermissionToLogonVerifier userPermissionToLogonVerifier = new UserPermissionToLogonVerifier();
        LogResponseTypes logResponseTypes = userPermissionToLogonVerifier.verifyUserAccessPermission(new User.UserBuilder()
                        .name("")
                        .password("zxc")
                        .build()
                , new MySqlDatabase());
        assertEquals(logResponseTypes, LogResponseTypes.DENIED);
    }

    @Test
    void verifyUserAccessPermissionEmptyPasswordTest() {
        UserPermissionToLogonVerifier userPermissionToLogonVerifier = new UserPermissionToLogonVerifier();
        LogResponseTypes logResponseTypes = userPermissionToLogonVerifier.verifyUserAccessPermission(new User.UserBuilder()
                        .name("qwerty")
                        .password("")
                        .build()
                , new MySqlDatabase());
        assertEquals(logResponseTypes, LogResponseTypes.DENIED);
    }
}