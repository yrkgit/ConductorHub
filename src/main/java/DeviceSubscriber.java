import java.util.ArrayList;

public class DeviceSubscriber {
    private static ArrayList<String> listOfDevicesIps = new ArrayList<>();
    private static ArrayList<String> listOfDevicesUsers = new ArrayList<>();

    public static void addDeviceIp(String deviceIp){
        listOfDevicesIps.add(deviceIp);
    }
    public static void addDeviceUser(String deviceUser){
        listOfDevicesUsers.add(deviceUser);
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
