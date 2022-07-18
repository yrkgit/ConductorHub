package subscription;

import Frames.LogRequestFrame;
import Frames.LogResponseFrame;
import Frames.LogResponseFrameController;
import Frames.LogResponseTypes;
import Hub.*;

public class SubscriptionResponse {

    private static final int destinationLogPortNumber = 7803;

    private final UserPermissionToLogonVerifier userPermissionToLogonVerifier;
    private final MySqlDatabase mySqlDatabase;
    private final LogResponseFrameController logResponseFrameController;
    private final JsonSerializer serializedFrame;
    private final Sender frameSender;

    public SubscriptionResponse() {
        this.userPermissionToLogonVerifier = new UserPermissionToLogonVerifier();
        this.mySqlDatabase = new MySqlDatabase();
        logResponseFrameController = new LogResponseFrameController();
        serializedFrame = new JsonSerializer();
        frameSender = new StringToDeviceSender();
    }

    public void setPermission(LogRequestFrame receivedFrame) {
        LogResponseTypes userAccessPermission = userPermissionToLogonVerifier.verifyUserAccessPermission(
                new User.UserBuilder()
                        .name(receivedFrame.getUser())
                        .password(receivedFrame.getPass())
                        .build()
                , mySqlDatabase);

        logResponseFrameController.setResponseType(userAccessPermission);
    }

    public LogResponseFrame prepareLogResponseFrame(LogRequestFrame logRequestFrame) {
        LogResponseFrame logResponseFrame = logResponseFrameController.createResponseFrame();
        frameSender.sendFrame(serializedFrame.createJsonFromFrame(logResponseFrame), logRequestFrame.getIpAddress(), destinationLogPortNumber);
        return logResponseFrame;
    }
}
