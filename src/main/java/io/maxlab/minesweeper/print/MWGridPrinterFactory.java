package io.maxlab.minesweeper.print;

import io.maxlab.minesweeper.core.MWGridPrinter;

import java.io.PrintStream;

public class MWGridPrinterFactory implements MWGridPrinter.Factory {

    @Override
    public MWGridPrinter getNewMapPrinter(PrintStream outStream) {
        return new AsciiMWGridPrinter(outStream);
    }
}
