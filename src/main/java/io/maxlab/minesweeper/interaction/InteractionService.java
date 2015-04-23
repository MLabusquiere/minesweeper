package io.maxlab.minesweeper.interaction;

import io.maxlab.minesweeper.core.PlayerInteractionService;
import io.maxlab.minesweeper.core.exception.AlreadyRevealedCaseException;
import io.maxlab.minesweeper.core.exception.MWException;
import io.maxlab.minesweeper.core.exception.NotificationException;
import io.maxlab.minesweeper.core.exception.WrongCoordinatesException;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

class InteractionService implements PlayerInteractionService {
    private final Scanner reader;
    private final PrintStream printer;
    private boolean isRunning = true;

    public InteractionService(Scanner reader, PrintStream printer) {
        this.reader = reader;
        this.printer = printer;
    }

    @Override
    public Configuration askConfiguration() {
        while (isRunning) {
            try {
                print("Please give us the configuration with the following format : ");
                print("map_width,map_height,bomb_number");
                return getConfigurationFromUser(reader.nextLine());
            } catch (IllegalArgumentException e) {
                print(e.getMessage());
            }
        }
        throw new IllegalStateException("The interaction with the player had been closed");
    }

    @Override
    public CaseCoordinate askCoordinates() {
        while (isRunning) {
            try {
                print("Please can you enter the coordinates of the cell you want to reveal (x,y)");
                return getCoordinatesFromInput(reader.nextLine());
            } catch (IllegalArgumentException e) {
                print(e.getMessage());
            }
        }
        throw new IllegalStateException("The interaction with the player had been closed");
    }

    /**
     * This method permits to convert the user input in coordinates
     *
     * @param pattern format : x,y
     *                x and y are the coordinated (int).
     * @return the representation of the user input
     */
    private CaseCoordinate getCoordinatesFromInput(String pattern) {
        final String[] split = pattern.split(",");
        if (!(split.length == 2)) {
            throw new IllegalArgumentException("Follow the pattern : x,y if you want to put a bomb");
        }
        final int x, y;
        try {
            x = Integer.parseInt(split[0]);
            y = Integer.parseInt(split[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please do enter integer number");
        }
        return new CaseCoordinate(x, y);
    }

    /**
     * This method permits to converts the user input in configuration
     *
     * @param pattern mapWidth,mapHeight,bombNumber
     * @return the configuration from the user
     */
    private Configuration getConfigurationFromUser(String pattern) {
        final String[] split = pattern.split(",");
        if (!(split.length == 3)) {
            throw new IllegalArgumentException("Follow the pattern : map_width,map_height,bomb_number to configure the game");
        }
        final int mapWidth, mapHeight, bombNumber;
        try {
            mapWidth = Integer.parseInt(split[0]);
            mapHeight = Integer.parseInt(split[1]);
            bombNumber = Integer.parseInt(split[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please do enter integer number");
        }
        return new Configuration(mapWidth, mapHeight, bombNumber);
    }

    @Override
    public void close() throws IOException {
        isRunning = false;
    }

    @Override
    public void print(String s) {
        printer.println(s);
    }

    @Override
    public void handleWin(String s) {
        printer.println(s);
    }

    @Override
    public void handleLoose(String s) {
        printer.println(s);
    }

    @Override
    public void handleCoordinatesError(WrongCoordinatesException e) {
        printer.println(e.getMessage());
    }

    @Override
    public void handleMWError(MWException e) {
        printer.println(e.getMessage());
    }

    @Override
    public void handleAlreadyRevealedCaseException(AlreadyRevealedCaseException e) {
        printer.println(e.getMessage());
    }

    @Override
    public void handleNotificationError(NotificationException e) {
        printer.println(e);
    }

    @Override
    public void handleNotificationError(String e) {
        printer.println(e);
    }
}
