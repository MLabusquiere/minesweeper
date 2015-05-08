package io.maxlab.minesweeper.map;

import io.maxlab.minesweeper.core.MWCell;
import io.maxlab.minesweeper.core.MWGrid;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * An helper class that help to build the map
 */
class GridStructure {
    private static final Random RANDOM_GENERATOR = new Random();
    private final MWGrid.MapCaseFactory cellFactory;
    private final List<MWCell> mwCells;
    private final int bombsCount;
    private final int mapWidth;
    private final int mapHeight;
    private int revealedCase = 0;

    public GridStructure(int width, int height, int bomb, MWGrid.MapCaseFactory cellFactory) {
        checkParameters(width, height, bomb);
        this.mapWidth = width;
        this.mapHeight = height;
        this.bombsCount = bomb;
        this.cellFactory = cellFactory;
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
        this.cellFactory = DefaultMWGrid.DEFAULT_BOX_FACTORY;
    }

    private void init() {
        mwCells.clear();
        int i = 0;
        for (; i < bombsCount; i++) {
            mwCells.add(cellFactory.getBomb());
        }
        for (; i < mapWidth * mapHeight; i++) {
            mwCells.add(cellFactory.getDefault());
        }
        Collections.shuffle(mwCells);
        computeNeigbourg();
        revealedCase = 0;
    }

    private void computeNeigbourg() {
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