/**
 * Class for device subscription to list od Data Frame receivers
 */

import java.util.ArrayList;

public class DataFrameReceiverDeviceSubscriber {
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
