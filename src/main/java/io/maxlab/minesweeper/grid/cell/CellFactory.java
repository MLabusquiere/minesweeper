package io.maxlab.minesweeper.grid.cell;

import io.maxlab.minesweeper.core.MWCell;
import io.maxlab.minesweeper.core.MWGrid;

/**
 *
 */
public class CellFactory implements MWGrid.MapCaseFactory {

    @Override
    public MWCell getDefault() {
        return new DefaultCell();
    }

    @Override
    public MWCell getBomb() {
        return new MineCell();
    }
}
