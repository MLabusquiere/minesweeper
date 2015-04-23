package io.maxlab.minesweeper.core;

/**
 *
 */
public interface MWGrid {
    /**
     * Reveal a cell of the map given the coordinates.
     *
     * @param x Coordinates
     * @param y Coordinates
     */
    boolean reveal(int x, int y);

    /**
     * Get the cell at the given coordinates
     *
     * @param x Coordinates
     * @param y Coordinates
     * @return the cell at given coordonates
     */
    MWCell getCase(int x, int y);

    /**
     * The width of the map
     *
     * @return the width of the map
     */
    int width();

    /**
     * The height of the map
     *
     * @return The height of the map
     */
    int height();

    /**
     * Permits to know if all case which are not mines had been revealed
     * @return true if all not mine cells had been reveeled else otherwise
     */
    boolean isAllCellHadBeenRevealed();

    /**
     * Create a new map with the same configuration
     */
    void reinit();

    /**
     * Print the map
     */
    void print();

    public interface MapCaseFactory {
        MWCell getDefault();

        MWCell getBomb();
    }

    public interface Factory {
        MWGrid getNewMap(int width, int height, int bombNumber, MWGridPrinter printer);

    }
}
