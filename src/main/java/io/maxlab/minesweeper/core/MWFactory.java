package io.maxlab.minesweeper.core;

import io.maxlab.minesweeper.interaction.MWInteractionServiceFactory;
import io.maxlab.minesweeper.grid.MWGridFactory;
import io.maxlab.minesweeper.print.MWGridPrinterFactory;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static io.maxlab.minesweeper.core.PlayerInteractionService.Configuration;
import static io.maxlab.minesweeper.core.PlayerInteractionService.Factory;

public class MWFactory {

    private static final PrintStream OUT_STREAM = System.out;
    private static final InputStream IN_STREAM = System.in;
    private final PrintStream outStream;
    private final InputStream inStream;

    private MWGrid.Factory mapFactory = new MWGridFactory();
    private Factory interactionFactory;
    private MWGridPrinterFactory printerFactory = new MWGridPrinterFactory();

    public MWFactory() {
        this(OUT_STREAM, IN_STREAM);
    }

    public MWFactory(PrintStream outStream, InputStream inStream) {
        this.outStream = outStream;
        this.inStream = inStream;
        this.interactionFactory = new MWInteractionServiceFactory(outStream);
    }

    public MWGame getGame() {
        final PlayerInteractionService interactionService = this.interactionFactory.getNewInteractionService(new Scanner(inStream));
        final MWGridPrinter newMapPrinter = printerFactory.getNewMapPrinter(outStream);

        try {
            final Configuration configuration = interactionService.askConfiguration();
            final MWGrid newMap = mapFactory.getNewGrid(configuration.width, configuration.height, configuration.bombCount, newMapPrinter);
            return new MWGame(newMap, interactionService);
        } catch (IllegalArgumentException e) {
            interactionService.handleNotificationError(e.getMessage());

        }
        return null;
    }
}
