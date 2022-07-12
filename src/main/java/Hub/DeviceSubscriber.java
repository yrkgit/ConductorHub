package Hub; /**
 * Class for device subscription to Data Frames.Frame receivers list. This list is used to when sending vehicle data to end devices.
 */

import java.util.ArrayList;

public class DeviceSubscriber {
    private static ArrayList<String> listOfSubscribedDevicesIpAddresses = new ArrayList<>();

    public static void addDeviceIpToList(String deviceIp){
        listOfSubscribedDevicesIpAddresses.add(deviceIp);
    }

    public static ArrayList<String> getListOfSubscribedDevicesIpAddresses() {
        return listOfSubscribedDevicesIpAddresses;
    }
    public static int getNumberOfSubscribedDevices() {
        return listOfSubscribedDevicesIpAddresses.size();
    }
    //TODO add logoff option with remove ip from subscription
    public void removeSubscription(){
    }
}
