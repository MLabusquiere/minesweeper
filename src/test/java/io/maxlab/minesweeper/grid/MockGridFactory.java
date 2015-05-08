package io.maxlab.minesweeper.grid;

import io.maxlab.minesweeper.core.MWCell;
import io.maxlab.minesweeper.core.MWGrid;
import io.maxlab.minesweeper.core.MWGridPrinter;
import io.maxlab.minesweeper.grid.cell.CellFactory;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class MockGridFactory {
    private static final MWGrid.MapCaseFactory caseFactory = new CellFactory();

    public static GridStructure getGridStructure(Character[] map, int width, int height, int bomb) {
        List<MWCell> list = Arrays.stream(map).
                map(MockGridFactory::getNewBox).
                collect(toList());
        return new GridStructure(width, height, bomb, list);
    }

    public static MWGrid getDefaultMWGrid(Character[] map, int width, int height, int bomb, MWGridPrinter printer) {
        GridStructure gridStructure = getGridStructure(map, width, height, bomb);
        return new DefaultMWGrid(gridStructure, printer);
    }

    private static MWCell getNewBox(Character n) {
        if (n == 'X') {
            return caseFactory.getBomb();
        }
        MWCell mwCell = caseFactory.getDefault();
        mwCell.setBombNeighbor(Integer.parseInt(n.toString()));
        return mwCell;
    }
}
