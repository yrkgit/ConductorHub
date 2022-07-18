package subscription;

import Frames.DataFrameController;
import Frames.LogRequestFrame;
import Hub.DataFrameSender;
import Hub.FileLogger;
import Hub.JsonSerializer;
import Hub.StringToDeviceSender;

public class DeviceSubscriber {
    private boolean isServerAlreadyStarted;

    public void subscribeDevice(LogRequestFrame logRequestFrame) {
        if (SubscribedDevices.getListOfSubscribedDevicesIpAddresses().contains(logRequestFrame.getIpAddress())) {
            FileLogger.logger.info(logRequestFrame.getIpAddress() + " was already subscribed");
        } else {
            SubscribedDevices.addDeviceIpToList(logRequestFrame.getIpAddress());
            FileLogger.logger.info("Dodano urządzenie: " + logRequestFrame.getUser() + " with IP address: " + logRequestFrame.getIpAddress() + " Ilość subskrybentów IP: " + SubscribedDevices.getNumberOfSubscribedDevices());

            if (!isServerAlreadyStarted) {
                startSendingData();
            }
        }
    }
    private void startSendingData() {
        isServerAlreadyStarted = true;
        Thread dataFrameCreatorThread = new Thread(new DataFrameSender(new DataFrameController(), new JsonSerializer(), new StringToDeviceSender()));
        dataFrameCreatorThread.start();
    }
}
