public class LogRequestFrame extends Frame implements Serializable{
    private String user;
    private String pass;
    private String ipAddress;

    public LogRequestFrame(String appVersion, FrameTypes typeOfFrame, long utc, String user, String pass, String ipAddress) {
        super(appVersion, typeOfFrame, utc);
        setUser(user);
        setPass(pass);
        setIpAddress(ipAddress);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public void serialize() {

    }
}
