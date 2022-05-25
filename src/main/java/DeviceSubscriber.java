import java.util.ArrayList;

public class DeviceSubscriber {
    private static ArrayList<String> listOfDevicesIps = new ArrayList<String>();

    public static void addDeviceIp(String deviceIp){
        listOfDevicesIps.add(deviceIp);
    }

    public static ArrayList<String> getListOfDevicesIps() {
        return listOfDevicesIps;
    }
    public static int getNumberOfDevicesIps() {
        return listOfDevicesIps.size();
    }
    //TODO add logoff option with remove ip from subscription
    public void removeSubscription(){
    }
}
