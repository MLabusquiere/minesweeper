package io.maxlab.minesweeper.core;

/**
 *
 */
public interface MWGrid {
    /**
     * Reveal a cell of the grid given the coordinates.
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
     * The width of the grid
     *
     * @return the width of the grid
     */
    int width();

    /**
     * The height of the grid
     *
     * @return The height of the grid
     */
    int height();

    /**
     * Permits to know if all case which are not mines had been revealed
     * @return true if all not mine cells had been reveeled else otherwise
     */
    boolean isAllCellHadBeenRevealed();

    /**
     * Create a new grid with the same configuration
     */
    void reinit();

    /**
     * Print the grid
     */
    void print();

    public interface MapCaseFactory {
        MWCell getDefault();

        MWCell getBomb();
    }

    public interface Factory {
        MWGrid getNewGrid(int width, int height, int bombNumber, MWGridPrinter printer);

    }
}
