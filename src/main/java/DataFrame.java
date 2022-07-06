/**
 * Class represents frame send by ConductorHub to transfer data like Next stop, Current Speed of vehicle, Passenger Counting System data
 * Contains inner Builder class for Object creations
 */
public class DataFrame extends Frame{
    private final String currentStopName;
    private final String nextStopName;
    private final String currentVehicleSpeed;

    private final int currentPassengerNumber;
    private final int boardedPassengersOnLastStation;
    private final int unBoardedPassengersOnLastStation;


    public DataFrame(Builder builder) {
        super(builder);
        this.currentStopName = builder.currentStopName;
        this.nextStopName = builder.nextStopName;
        this.currentVehicleSpeed = builder.currentVehicleSpeed;
        this.currentPassengerNumber = builder.currentPassengerNumber;
        this.boardedPassengersOnLastStation = builder.boardedPassengersOnLastStation;
        this.unBoardedPassengersOnLastStation = builder.unBoardedPassengersOnLastStation;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Frame.Builder<Builder> {
        private String currentStopName;
        private String nextStopName;
        private String currentVehicleSpeed;

        private int currentPassengerNumber;
        private int boardedPassengersOnLastStation;
        private int unBoardedPassengersOnLastStation;

        @Override
        public Builder getThis() {
            return this;
        }

        public Builder currentStopName(String currentStopName) {
            this.currentStopName = currentStopName;
            return this;
        }

        public Builder nextStopName(String nextStopName) {
            this.nextStopName = nextStopName;
            return this;
        }

        public Builder currentVehicleSpeed(String currentVehicleSpeed) {
            this.currentVehicleSpeed = currentVehicleSpeed;
            return this;
        }
        public Builder currentPassengerNumber(int currentPassengerNumber) {
            this.currentPassengerNumber = currentPassengerNumber;
            return this;
        }
        public Builder boardedPassengersOnLastStation(int boardedPassengersOnLastStation) {
            this.boardedPassengersOnLastStation = boardedPassengersOnLastStation;
            return this;
        }
        public Builder unBoardedPassengersOnLastStation(int unBoardedPassengersOnLastStation) {
            this.unBoardedPassengersOnLastStation = unBoardedPassengersOnLastStation;
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
                "currentStopName='" + currentStopName + '\'' +
                ", nextStopName='" + nextStopName + '\'' +
                ", currentVehicleSpeed='" + currentVehicleSpeed + '\'' +
                ", currentPassengerNumber=" + currentPassengerNumber +
                ", boardedPassengersOnLastStation=" + boardedPassengersOnLastStation +
                ", unBoardedPassengersOnLastStation=" + unBoardedPassengersOnLastStation +
                ", appVersion='" + appVersion + '\'' +
                ", frameType=" + frameType +
                ", utc=" + utc +
                '}';
    }
}
