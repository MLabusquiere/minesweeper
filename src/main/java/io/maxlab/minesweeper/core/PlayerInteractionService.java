package io.maxlab.minesweeper.core;

import io.maxlab.minesweeper.core.exception.ErrorHandler;

import java.io.Closeable;
import java.util.Scanner;

/**
 */
public interface PlayerInteractionService extends ErrorHandler, Closeable {
    /**
     * get configuration from the user
     *
     * @return the configuration from the user
     */
    CaseCoordinate askCoordinates();

    /**
     * get configuration from the user
     *
     * @return the configuration from the user
     */
    Configuration askConfiguration();

    void print(String s);

    void handleWin(String s);

    void handleLoose(String s);


    public interface Factory {
        PlayerInteractionService getNewInteractionService(Scanner scanner);
    }

    public static class CaseCoordinate {
        public final int x;
        public final int y;

        public CaseCoordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Configuration {
        public final int width;
        public final int height;
        public final int bombCount;

        public Configuration(int width, int height, int bombCount) {
            this.width = width;
            this.height = height;
            this.bombCount = bombCount;
        }

    }
}
