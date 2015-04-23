package io.maxlab.minesweeper.map.cell;

import io.maxlab.minesweeper.core.MWCell;
import io.maxlab.minesweeper.core.MWGrid;

/**
 *
 */
public class CaseFactory implements MWGrid.MapCaseFactory {

    @Override
    public MWCell getDefault() {
        return new DefaultCell();
    }

    @Override
    public MWCell getBomb() {
        return new MineCell();
    }
}
