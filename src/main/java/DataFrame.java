
public class DataFrame extends Frame{
    private String currentStop;
    private String nextStop;
    private String currentSpeed;

    public DataFrame(Builder builder) {
        super(builder);
        this.currentStop = builder.currentStop;
        this.nextStop = builder.nextStop;
        this.currentSpeed = builder.currentSpeed;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Frame.Builder<Builder> {
        private String currentStop;
        private String nextStop;
        private String currentSpeed;

        @Override
        public Builder getThis() {
            return this;
        }

        public Builder currentStop(String currentStop) {
            this.currentStop = currentStop;
            return this;
        }

        public Builder nextStop(String nextStop) {
            this.nextStop = nextStop;
            return this;
        }

        public Builder currentSpeed(String currentSpeed) {
            this.currentSpeed = currentSpeed;
            return this;
        }

        public DataFrame build() {
            return new DataFrame(this);
        }
    }

    public String getCurrentStop() {
        return currentStop;
    }

    public String getNextStop() {
        return nextStop;
    }

    public String getCurrentSpeed() {
        return currentSpeed;
    }

}
