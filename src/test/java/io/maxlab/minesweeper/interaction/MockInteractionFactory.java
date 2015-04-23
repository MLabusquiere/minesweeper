package io.maxlab.minesweeper.interaction;

import io.maxlab.minesweeper.core.PlayerInteractionService;

import java.io.PrintStream;
import java.util.Scanner;

public class MockInteractionFactory {
    public static PlayerInteractionService getService(PrintStream printer, Scanner reader) {
        return new InteractionService(reader, printer);
    }
}
