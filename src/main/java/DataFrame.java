/**
 * Class represents frame send by ConductorHub to transfer data like Next stop, Current Speed of vehicle, Passenger Counting System data
 * Contains inner Builder class for Object creations
 */
public class DataFrame extends Frame{
    private final String currentStop;
    private final String nextStop;
    private final String currentSpeed;

    private final int passengerStats;
    private final int boardingStats;
    private final int unBoardingStats;


    public DataFrame(Builder builder) {
        super(builder);
        this.currentStop = builder.currentStop;
        this.nextStop = builder.nextStop;
        this.currentSpeed = builder.currentSpeed;
        this.passengerStats = builder.passengerStats;
        this.boardingStats = builder.boardingStats;
        this.unBoardingStats = builder.unBoardingStats;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Frame.Builder<Builder> {
        private String currentStop;
        private String nextStop;
        private String currentSpeed;

        private int passengerStats;
        private int boardingStats;
        private int unBoardingStats;

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
        public Builder passengerStats(int passengerStats) {
            this.passengerStats = passengerStats;
            return this;
        }
        public Builder boardingStats(int boardingStats) {
            this.boardingStats = boardingStats;
            return this;
        }
        public Builder unBoardingStats(int unBoardingStats) {
            this.unBoardingStats = unBoardingStats;
            return this;
        }

        @Override
        public DataFrame build() {
            return new DataFrame(this);
        }
    }

    @Override
    public String toString() {
        return "DataFrame{" +
                "currentStop='" + currentStop + '\'' +
                ", nextStop='" + nextStop + '\'' +
                ", currentSpeed='" + currentSpeed + '\'' +
                ", passengerStats=" + passengerStats +
                ", boardingStats=" + boardingStats +
                ", unBoardingStats=" + unBoardingStats +
                ", appVersion='" + appVersion + '\'' +
                ", frameType=" + frameType +
                ", utc=" + utc +
                '}';
    }
}
