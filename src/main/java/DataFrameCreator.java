import java.time.Instant;

public class DataFrameCreator {

    private final String currentStop;
    private final String nextStop;
    private final String currentSpeed;

    private final int passengerStats;
    private final int boardingStats;
    private final int unBoardingStats;

    //TODO - do testów / usunąć
    DataFrameCreator() {
        currentStop = "Bydgoszcz Główna";
        nextStop = "Bydgoszcz Wschód";
        currentSpeed = "100";

        passengerStats = 157;
        boardingStats = 22;
        unBoardingStats = 14;
    }

    public DataFrame createDataFrame() {
        DataFrame dataFrame = DataFrame.builder()
                .appVersion("1.0")
                .frameType(FrameTypes.DATA)
                .utc(Instant.now().getEpochSecond())
                .currentStop(currentStop)
                .nextStop(nextStop)
                .currentSpeed(currentSpeed)
                .passengerStats(passengerStats)
                .boardingStats(boardingStats)
                .unBoardingStats(unBoardingStats)
                .build();
        FileLogger.logger.info("Created data frame " + dataFrame.toString());
        return dataFrame;
    }
}
