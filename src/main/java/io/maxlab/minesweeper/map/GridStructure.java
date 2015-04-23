package io.maxlab.minesweeper.map;

import io.maxlab.minesweeper.core.MWCell;
import io.maxlab.minesweeper.core.MWGrid;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;

/**
 * An helper class that help to build the map
 */
class GridStructure {
    private static final Random RANDOM_GENERATOR = new Random();
    private final MWGrid.MapCaseFactory boxFactory;
    private final List<MWCell> mwCells;
    private final int bombsCount;
    private final int mapWidth;
    private final int mapHeight;
    private int revealedCase = 0;

    public GridStructure(int width, int height, int bomb, MWGrid.MapCaseFactory boxFactory) {
        checkParameters(width, height, bomb);
        this.mapWidth = width;
        this.mapHeight = height;
        this.bombsCount = bomb;
        this.boxFactory = boxFactory;
        this.mwCells = new ArrayList<>(mapWidth * mapHeight);
        init();
    }

    /**
     * Should be only used as a test purpose
     */
    GridStructure(int width, int height, int bomb, List<MWCell> mwCells) {
        checkParameters(width, height, bomb);
        this.mapWidth = width;
        this.mapHeight = height;
        this.bombsCount = bomb;
        this.mwCells = mwCells;
        this.boxFactory = DefaultMWGrid.DEFAULT_BOX_FACTORY;
    }

    private void init() {
        mwCells.clear();
        final LinkedList<Integer> allIndexes = new LinkedList<>();
        for (int i = 0; i < mapWidth * mapHeight; i++) {
            allIndexes.add(i);
        }

        for (int i = 0; i > bombsCount; i++) {
            final int pickedIndexForBomb = RANDOM_GENERATOR.nextInt(allIndexes.size() - i);
            allIndexes.remove(pickedIndexForBomb);
            allIndexes.addLast(pickedIndexForBomb);
        }

        for (int i = 0; i < mapWidth * mapHeight; i++) {
            mwCells.add(boxFactory.getDefault());
        }

        for (int i = allIndexes.size() - bombsCount; i < allIndexes.size(); i++) {
            final Integer bombIndex = allIndexes.get(i);
            mwCells.add(bombIndex, boxFactory.getBomb());
        }
        for (int i = 0; i < mwCells.size(); i++) {
            if (getBox(i).isBomb()) {
                continue;
            }
            List<MWCell> nearest = getNeighbors(i);
            int bombNumber = 0;
            for (MWCell mwCase : nearest) {
                if (mwCase.isBomb()) {
                    bombNumber++;
                }
            }
            getBox(i).setBombNeighbor(bombNumber);
        }
        revealedCase = 0;
    }

    public boolean reveal(int index) {
        revealedCase++;
        final MWCell box = getBox(index);
        boolean res = box.revealAsNotMine();
        if (box.isEmpty()) {
            //Neighbors can't have a bomb
            getNeighborIndexes(index).stream().forEach(neighborIndex -> {
                if (!getBox(neighborIndex).isRevealed()) {
                    if (!reveal(neighborIndex)) {
                        throw new IllegalStateException("Reveal a bomb in a case discovering by revealing an empty case");
                    }
                }
            });
        }
        return res;
    }

    public List<MWCell> getNeighbors(int index) {
        return getNeighborIndexes(index).stream()
                .map(this::getBox)
                .collect(toList());

    }

    List<Integer> getNeighborIndexes(int index) {
        final List<Integer> neighbors = new ArrayList<>();
        final int rightTopCornerX = index % mapWidth - 1;
        final int rightTopCornerY = index / mapWidth - 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int x = rightTopCornerX + i;
                final int y = rightTopCornerY + j;
                final boolean isMiddle = i == 1 && j == 1;
                if (!isMiddle && isInMap(x, y)) {
                    neighbors.add(y * mapWidth + x);
                }
            }
        }
        return neighbors;
    }

    MWCell getBox(int x) {
        return mwCells.get(x);
    }

    public int width() {
        return mapWidth;
    }

    public int bombNumber() {
        return bombsCount;
    }

    public int size() {
        return mwCells.size();
    }

    public void reinit() {
        init();
    }

    private boolean isInMap(int x, int y) {
        return !(x < 0 || x >= width() || y * width() + x >= size() || y < 0);
    }

    private void checkParameters(int width, int height, int bomb) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Height and width can't be negative");
        }
        long mapSize = (long) width * (long) height;
        if (mapSize > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("The map is too big");
        }
        if (bomb >= mapSize || bomb <= 0) {
            throw new IllegalArgumentException("The number of bomb can't be out bound");
        }
    }

    public int getRevealedCase() {
        return revealedCase;
    }
}