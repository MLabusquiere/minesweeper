package io.maxlab.minesweeper.core.exception;

public interface ErrorHandler {
    void handleCoordinatesError(WrongCoordinatesException e);

    void handleMWError(MWException e);

    void handleAlreadyRevealedCaseException(AlreadyRevealedCaseException e);

    void handleNotificationError(NotificationException e);

    void handleNotificationError(String e);
}
