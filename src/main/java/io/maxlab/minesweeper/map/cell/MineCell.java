package io.maxlab.minesweeper.map.cell;

import io.maxlab.minesweeper.core.MWCell;
import io.maxlab.minesweeper.core.exception.AlreadyRevealedCaseException;

/**
 *
 */
class MineCell implements MWCell {

    private boolean isRevealed = false;

    @Override
    public boolean isBomb() {
        return true;
    }

    @Override
    public char getSymbol() {
        return isRevealed ? BOMB_SYMBOL : NOT_REVEALED_SYMBOL;
    }

    @Override
    public void setBombNeighbor(int bombNumber) {
         throw new IllegalArgumentException("This case is a bomb");
    }

    @Override
    public boolean revealAsNotMine() {
        if (isRevealed()) {
            throw new AlreadyRevealedCaseException();
        }
        isRevealed = true;
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isRevealed() {
        return isRevealed;
    }

}
