package io.maxlab.minesweeper.interaction;

import io.maxlab.minesweeper.core.PlayerInteractionService;

import java.io.PrintStream;
import java.util.Scanner;

public class MWInteractionServiceFactory implements PlayerInteractionService.Factory {

    private final PrintStream printer;

    public MWInteractionServiceFactory(PrintStream printer) {
        this.printer = printer;
    }

    @Override
    public InteractionService getNewInteractionService(Scanner scanner) {
        return new InteractionService(scanner, this.printer);
    }
}
