package io.maxlab.minesweeper.core;

import java.io.PrintStream;

public interface MWGridPrinter {
    /**
     * Print the MinesWheeper grid
     */
    void print(MWGrid grid);

    public interface Factory {
        MWGridPrinter getNewMapPrinter(PrintStream outStream);
    }
}
