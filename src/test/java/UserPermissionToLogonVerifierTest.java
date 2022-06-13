import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserPermissionToLogonVerifierTest {
    UserPermissionToLogonVerifier userPermissionToLogonVerifier;

    @BeforeEach
    void beforeEach(){
        userPermissionToLogonVerifier = new UserPermissionToLogonVerifier();
    }

    @Test
    void verifyUserAccessPermissionGrantedTest() {
        LogResponseTypes logResponseTypes = userPermissionToLogonVerifier.verifyUserAccessPermission(new User.UserBuilder()
                        .name("qwerty")
                        .password("zxc")
                        .build()
                , new MySqlDatabase());
        assertEquals(logResponseTypes, LogResponseTypes.GRANTED);
    }

    @Test
    void verifyUserAccessWrongPassTest() {
        LogResponseTypes logResponseTypes = userPermissionToLogonVerifier.verifyUserAccessPermission(new User.UserBuilder()
                        .name("qwerty")
                        .password("asd")
                        .build()
                , new MySqlDatabase());
        assertEquals(logResponseTypes, LogResponseTypes.DENIED);
    }
    @Test
    void verifyUserAccessNullPassTest() {
        LogResponseTypes logResponseTypes = userPermissionToLogonVerifier.verifyUserAccessPermission(new User.UserBuilder()
                        .name("qwerty")
                        .password(null)
                        .build()
                , new MySqlDatabase());
        assertEquals(logResponseTypes, LogResponseTypes.DENIED);
    }
    @Test
    void verifyUserAccessNullNameTest() {
        assertThrows(IllegalStateException.class,()->userPermissionToLogonVerifier.verifyUserAccessPermission(new User.UserBuilder()
                        .name(null)
                        .password("qwerty")
                        .build()
                , new MySqlDatabase()));
    }

    @Test
    void verifyUserAccessPermissionEmptyUserTest() {
        LogResponseTypes logResponseTypes = userPermissionToLogonVerifier.verifyUserAccessPermission(new User.UserBuilder()
                        .name("")
                        .password("zxc")
                        .build()
                , new MySqlDatabase());
        assertEquals(logResponseTypes, LogResponseTypes.DENIED);
    }

    @Test
    void verifyUserAccessPermissionEmptyPasswordTest() {
        LogResponseTypes logResponseTypes = userPermissionToLogonVerifier.verifyUserAccessPermission(new User.UserBuilder()
                        .name("qwerty")
                        .password("")
                        .build()
                , new MySqlDatabase());
        assertEquals(logResponseTypes, LogResponseTypes.DENIED);
    }
}