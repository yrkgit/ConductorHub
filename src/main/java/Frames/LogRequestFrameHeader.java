package Frames;

/**
 * Class represents frame send by remote device to authenticate user and subscribe to Hub.ConductorHub if permission granted
 */

public class LogRequestFrameHeader extends FrameHeader {
    private String user;
    private String pass;
    private String ipAddress;

    public LogRequestFrameHeader(Builder builder) {
        super(builder);
        this.user = builder.user;
        this.pass = builder.pass;
        this.ipAddress = builder.ipAddress;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends FrameHeader.Builder<Builder> {
        private String user;
        private String pass;
        private String ipAddress;

        @Override
        public Builder getThis() {
            return this;
        }

        public Builder user(String user) {
            this.user = user;
            return this;
        }

        public Builder pass(String pass) {
            this.pass = pass;
            return this;
        }

        public Builder ipAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        public LogRequestFrameHeader build() {
            return new LogRequestFrameHeader(this);
        }
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getIpAddress() {
        return ipAddress;
    }
}
