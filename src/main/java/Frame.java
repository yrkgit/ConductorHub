/**
 * Class represents base (header) of frames used in communication between ConductorHub and (subscribed) Devices
 * Describe basic frame parameters with all frame must have: version of the application, type of frame (based on FrameTypes Enum) and time of frame creation
 * Contains inner Builder class for Object creations
 */

public class Frame {
    protected String appVersion;
    protected FrameTypes frameType;

    protected long utc;

    protected Frame(Builder<?> builder) {
        this.appVersion = builder.appVersion;
        this.frameType = builder.frameType;
        this.utc = builder.utc;
    }

    protected static Builder builder() {
        return new Builder() {
            @Override
            public Builder getThis() {
                return this;
            }
        };
    }


    public abstract static class Builder<T extends Builder<T>> {
        private String appVersion;
        private FrameTypes frameType;

        private long utc;

        public abstract T getThis();

        public T appVersion(String appVersion) {
            this.appVersion = appVersion;
            return this.getThis();
        }

        public T frameType(FrameTypes frameType) {
            this.frameType = frameType;
            return this.getThis();
        }

        public T utc(long utc) {
            this.utc = utc;
            return this.getThis();
        }

        public Frame build() {
            return new Frame(this);
        }
    }


    @Override
    public String toString() {
        return "Frame{" +
                "appVersion='" + appVersion + '\'' +
                ", frameType=" + frameType +
                ", utc=" + utc +
                '}';
    }
}
