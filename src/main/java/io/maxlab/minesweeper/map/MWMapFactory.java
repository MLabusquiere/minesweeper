package io.maxlab.minesweeper.map;

import io.maxlab.minesweeper.core.MWGrid;
import io.maxlab.minesweeper.core.MWGridPrinter;

public class MWMapFactory implements MWGrid.Factory {

    @Override
    public MWGrid getNewMap(int width, int height, int bombNumber, MWGridPrinter printer) {
        return new DefaultMWGrid(width, height, bombNumber, printer);
    }

}
