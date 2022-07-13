package Frames;

/**
 * Class Frames.LogRequestFrame represents frame send by Hub.ConductorHub as a response to Frames.LogRequestFrame with permission status
 * */

public class LogResponseFrame extends FrameHeader {
    private LogResponseTypes permission;


    public LogResponseFrame(Builder builder) {
        super(builder);
        this.permission = builder.permission;

    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends FrameHeader.Builder<Builder> {
        private LogResponseTypes permission;


        @Override
        public Builder getThis() {
            return this;
        }

        public Builder permission(LogResponseTypes permission) {
            this.permission = permission;
            return this;
        }


        public LogResponseFrame build() {
            return new LogResponseFrame(this);
        }
    }

    @Override
    public String toString() {
        return "Frames.LogResponseFrame{" +
                "permission=" + permission +
                ", appVersion='" + appVersion + '\'' +
                ", frameType=" + frameType +
                ", utc=" + utc +
                '}';
    }

    public LogResponseTypes getPermission() {
        return permission;
    }
}
