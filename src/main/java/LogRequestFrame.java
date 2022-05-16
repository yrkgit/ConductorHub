public class LogRequestFrame extends Frame implements Serializable {
    private String user;
    private String pass;
    private String ipAddress;

    public LogRequestFrame(String appVersion, FrameTypes typeOfFrame, long utc, String user, String pass, String ipAddress) {
        super(appVersion, typeOfFrame, utc);
        this.user = user;
        this.pass = pass;
        this.ipAddress = ipAddress;
    }

    public LogRequestFrame(Builder builder) {
        super(builder);
        this.user=builder.user;
        this.pass=builder.pass;
        this.ipAddress=builder.ipAddress;
    }

    public static class Builder extends Frame.Builder<Builder> {
        private String user;
        private String pass;
        private String ipAddress;

        @Override
        public Builder getThis() {
            return this;
        }
        public Builder user(String user){
            this.user=user;
            return this;
        }
        public Builder pass(String pass){
            this.pass=pass;
            return this;
        }
        public Builder ipAddress(String ipAddress){
            this.ipAddress=ipAddress;
            return this;
        }
        public LogRequestFrame build(){
            return new LogRequestFrame(this);
        }
    }


    @Override
    public void serialize() {

    }
}
