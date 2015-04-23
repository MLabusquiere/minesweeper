package io.maxlab.minesweeper.print;

import io.maxlab.minesweeper.core.MWGrid;
import io.maxlab.minesweeper.core.MWGridPrinter;

import java.io.PrintStream;

/**
 * Print the MWMap in Ascii
 */
class AsciiMWGridPrinter implements MWGridPrinter {

    private final PrintStream printer;

    public AsciiMWGridPrinter(PrintStream printer) {
        this.printer = printer;
    }

    @Override
    public void print(MWGrid mwGrid) {
        printHeader(mwGrid);
        printMap(mwGrid);
        printFooter();
    }

    private void printHeader(MWGrid mwGrid) {
        printer.print(" ");
        for (int i = 0; i < mwGrid.width(); i++) {
            printer.print(" " + i);
        }
        printer.print("\n");
    }

    private void printMap(MWGrid mwGrid) {
        for (int nLine = 0; nLine < mwGrid.height(); nLine++) {
            printLine(mwGrid, nLine);
            printer.print("\n");
        }
    }

    private void printFooter() {
        printer.println();
    }

    private void printLine(MWGrid mwGrid, int nLine) {
        printer.print(nLine);
        for (int nColumn = 0; nColumn < mwGrid.width(); nColumn++) {
            printer.print(" " + mwGrid.getCase(nColumn, nLine).getSymbol());
        }
    }
}