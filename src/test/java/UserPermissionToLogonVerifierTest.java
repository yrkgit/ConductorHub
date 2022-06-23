import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testing user logon mechanism")
class UserPermissionToLogonVerifierTest {
    UserPermissionToLogonVerifier userPermissionToLogonVerifier;


    @BeforeEach
    void beforeEach(TestInfo testInfo, TestReporter testReporter){
        userPermissionToLogonVerifier = new UserPermissionToLogonVerifier();
        testReporter.publishEntry("Running "+ testInfo.getDisplayName());
    }

    @Test
    @DisplayName("When proper name and pass are passed")
    void verifyUserAccessPermissionGrantedTest() {
        LogResponseTypes logResponseTypes = userPermissionToLogonVerifier.verifyUserAccessPermission(new User.UserBuilder()
                        .name("qwerty")
                        .password("zxc")
                        .build()
                , new MySqlDatabase());
        assertEquals(logResponseTypes, LogResponseTypes.GRANTED);
    }

    @Test
    @DisplayName("When proper name, invalid pass are passed")
    void verifyUserAccessWrongPassTest() {
        LogResponseTypes logResponseTypes = userPermissionToLogonVerifier.verifyUserAccessPermission(new User.UserBuilder()
                        .name("qwerty")
                        .password("asd")
                        .build()
                , new MySqlDatabase());
        assertEquals(logResponseTypes, LogResponseTypes.DENIED);
    }
    @Test
    @DisplayName("When proper name, null pass are passed")
    void verifyUserAccessNullPassTest() {
        LogResponseTypes logResponseTypes = userPermissionToLogonVerifier.verifyUserAccessPermission(new User.UserBuilder()
                        .name("qwerty")
                        .password(null)
                        .build()
                , new MySqlDatabase());
        assertEquals(logResponseTypes, LogResponseTypes.DENIED);
    }
    @Test
    @DisplayName("When null name and normal pass are passed")
    void verifyUserAccessNullNameTest() {
        assertThrows(IllegalStateException.class,()->userPermissionToLogonVerifier.verifyUserAccessPermission(new User.UserBuilder()
                        .name(null)
                        .password("qwerty")
                        .build()
                , new MySqlDatabase()));
    }

    @Test
    @DisplayName("When empty name and proper pass are passed")
    void verifyUserAccessPermissionEmptyUserTest() {
        LogResponseTypes logResponseTypes = userPermissionToLogonVerifier.verifyUserAccessPermission(new User.UserBuilder()
                        .name("")
                        .password("zxc")
                        .build()
                , new MySqlDatabase());
        assertEquals(logResponseTypes, LogResponseTypes.DENIED);
    }

    @Test
    @DisplayName("When proper name and empty pass are passed")
    void verifyUserAccessPermissionEmptyPasswordTest() {
        LogResponseTypes logResponseTypes = userPermissionToLogonVerifier.verifyUserAccessPermission(new User.UserBuilder()
                        .name("qwerty")
                        .password("")
                        .build()
                , new MySqlDatabase());
        assertEquals(logResponseTypes, LogResponseTypes.DENIED);
    }
}