package io.maxlab.minesweeper.grid.cell;

import io.maxlab.minesweeper.core.MWCell;
import io.maxlab.minesweeper.core.exception.AlreadyRevealedCaseException;

/**
 *
 */
class DefaultCell implements MWCell {
    private char symbol = NOT_REVEALED_SYMBOL;
    private boolean isRevealed = false;

    @Override
    public boolean isBomb() {
        return false;
    }

    @Override
    public char getSymbol() {
        return isRevealed ? symbol : NOT_REVEALED_SYMBOL;
    }

    @Override
    public void setBombNeighbor(int bombNumber) {
        if (bombNumber < 0 || bombNumber > 8) {
            throw new IllegalArgumentException("The case can have only between 0 and 8 neighbor");
        }
        symbol = Character.forDigit(bombNumber, 10);
    }

    @Override
    public boolean revealAsNotMine() {
        if (isRevealed()) {
            throw new AlreadyRevealedCaseException();
        }
        isRevealed = true;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return symbol == '0';
    }

    @Override
    public boolean isRevealed() {
        return isRevealed;
    }
}
