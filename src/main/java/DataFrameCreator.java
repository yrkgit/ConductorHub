import java.time.Instant;

public class DataFrameCreator {

    private final String currentStopName;
    private final String nextStopName;
    private final String currentVehicleSpeed;

    private final int currentPassengerNumber;
    private final int boardedPassengersOnLastStation;
    private final int unBoardedPassengersOnLastStation;

    //TODO - do testów / usunąć
    DataFrameCreator() {
        currentStopName = "Bydgoszcz Główna";
        nextStopName = "Bydgoszcz Wschód";
        currentVehicleSpeed = "100";

        currentPassengerNumber = 157;
        boardedPassengersOnLastStation = 22;
        unBoardedPassengersOnLastStation = 14;
    }

    public DataFrame createDataFrame() {
        DataFrame dataFrame = DataFrame.builder()
                .appVersion("1.0")
                .frameType(FrameTypes.DATA)
                .utc(Instant.now().getEpochSecond())
                .currentStopName(currentStopName)
                .nextStopName(nextStopName)
                .currentVehicleSpeed(currentVehicleSpeed)
                .currentPassengerNumber(currentPassengerNumber)
                .boardedPassengersOnLastStation(boardedPassengersOnLastStation)
                .unBoardedPassengersOnLastStation(unBoardedPassengersOnLastStation)
                .build();
        FileLogger.logger.info("Created data frame " + dataFrame.toString());
        return dataFrame;
    }
}
