
public class Frame {
    private String appVersion;
    private FrameTypes frameType;

    private long utc;

    public <T extends Builder<T>> Frame(Builder<T> tBuilder) {
    }

    public static Builder builder() {
        return new Builder() {
            public Builder getThis() {
                return this;
            }
        };
    }

    public FrameTypes getFrameType() {
        return frameType;
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

    public Frame(String appVersion, FrameTypes frameType, long utc) {
        this.appVersion = appVersion;
        this.frameType = frameType;
        this.utc = utc;
    }

}
