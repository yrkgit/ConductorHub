/**
 * Class used to build DataFrame filled with current vehicle data and parameters received from other systems (TCMS, SIP)
 * */

import java.time.Instant;

public class DataFrameController {

    private final String currentStopName;
    private final String nextStopName;
    private final String currentVehicleSpeed;

    private final int currentPassengerNumber;
    private final int boardedPassengersOnLastStation;
    private final int unBoardedPassengersOnLastStation;

    //TODO - do testów / usunąć
    DataFrameController() {
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
