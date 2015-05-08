package io.maxlab.minesweeper.grid;

import io.maxlab.minesweeper.core.MWGrid;
import io.maxlab.minesweeper.core.MWGridPrinter;

public class MWGridFactory implements MWGrid.Factory {

    @Override
    public MWGrid getNewGrid(int width, int height, int bombNumber, MWGridPrinter printer) {
        return new DefaultMWGrid(width, height, bombNumber, printer);
    }

}
