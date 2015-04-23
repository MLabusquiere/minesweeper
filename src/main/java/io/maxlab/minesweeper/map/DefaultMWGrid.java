package io.maxlab.minesweeper.map;

import io.maxlab.minesweeper.core.MWCell;
import io.maxlab.minesweeper.core.MWGrid;
import io.maxlab.minesweeper.core.MWGridPrinter;
import io.maxlab.minesweeper.core.exception.WrongCoordinatesException;
import io.maxlab.minesweeper.map.cell.CaseFactory;

/**
 */
class DefaultMWGrid implements MWGrid {

    public static final CaseFactory DEFAULT_BOX_FACTORY = new CaseFactory();

    private final GridStructure gridStructure;
    private final MWGridPrinter printer;

    public DefaultMWGrid(int width, int height, int bomb, MWGridPrinter printer) {
        this(width, height, bomb, printer, DEFAULT_BOX_FACTORY);
    }

    private DefaultMWGrid(int width, int height, int bomb, MWGridPrinter printer, CaseFactory caseFactory) {
        this(new GridStructure(width, height, bomb, caseFactory), printer);
    }

    DefaultMWGrid(GridStructure gridStructure, MWGridPrinter printer) {
        this.gridStructure = gridStructure;
        this.printer = printer;
    }

    @Override
    public boolean reveal(int x, int y) {
        check(x, y);
        return gridStructure.reveal(y * gridStructure.width() + x);
    }

    @Override
    public MWCell getCase(int x, int y) {
        check(x, y);
        return gridStructure.getBox(y * width() + x);
    }

    @Override
    public void reinit() {
        gridStructure.reinit();
    }

    @Override
    public int width() {
        return gridStructure.width();
    }

    @Override
    public int height() {
        return gridStructure.size() / gridStructure.width();
    }

    @Override
    public boolean isAllCellHadBeenRevealed() {
        return gridStructure.getRevealedCase() == gridStructure.size() - gridStructure.bombNumber();
    }

    @Override
    public void print() {
        printer.print(this);
    }

    private void check(int x, int y) {
        if (x < 0 || x >= gridStructure.width() || y * gridStructure.width() + x >= gridStructure.size() || y < 0) {
            throw new WrongCoordinatesException("The coordinates (" + x + "," + y + ") can't fit in the map");
        }
    }

}