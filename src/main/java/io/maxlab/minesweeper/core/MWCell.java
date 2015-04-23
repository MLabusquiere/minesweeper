package io.maxlab.minesweeper.core;

import io.maxlab.minesweeper.core.exception.AlreadyRevealedCaseException;

/**
 *
 */
public interface MWCell {
    public static final char BOMB_SYMBOL = 'X';
    public static final char NOT_REVEALED_SYMBOL = ' ';

    /**
     * Permit to know if the cell holds a bomb
     * @return if the cell holds a bomb
     */
    boolean isBomb();
    /**
     * Get the symbol representing the current state of the bomb.
     * The 4 states ares a mix between isRevealed and isBomb
     * @return the symbol to print
     */
    char getSymbol();

    /**
     * Permit to set the bomb number around the Cell
     * @throws IllegalArgumentException when isBomb is true
     * @param bombNumber the bomb number around the Cell
     */
    void setBombNeighbor(int bombNumber) throws IllegalArgumentException;

    /**
     * Reveal the case changing the state of isRevealed and isEmpty
     * It will reveal the mine in any case
     * @throws io.maxlab.minesweeper.core.exception.AlreadyRevealedCaseException if the method had been already called once
     * @return true if it was not a mine else it is false.
     */
    boolean revealAsNotMine() throws AlreadyRevealedCaseException;
    /**
     * Permit to know if it is not a bomb and if their is no neighbour
     * mine.
     * @return true if it was not a mine else it is false.
     */
    boolean isEmpty();
    /**
     * Permit to know if it is not a bomb and if their is no neighbour
     * mine.
     * @return true if it was not a mine else it is false.
     */
    boolean isRevealed();


}
