package io.maxlab.minesweeper.grid.cell;

import io.maxlab.minesweeper.core.MWCell;
import io.maxlab.minesweeper.core.exception.AlreadyRevealedCaseException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MineCellTest {

    private MWCell mwCell;

    @Before
    public void setUp() throws Exception {
        mwCell = new MineCell();

    }

    @Test
    public void testIsBomb() throws Exception {
        assertTrue("The bomb mwCase hold a bomb", mwCell.isBomb());
    }

    @Test
    public void testGetSymbol() throws Exception {

        assertEquals("Not yet revealed", MWCell.NOT_REVEALED_SYMBOL, mwCell.getSymbol());
        assertFalse(mwCell.revealAsNotMine());
        assertTrue("Revealed", mwCell.isRevealed());
        assertEquals("Revealed", MWCell.BOMB_SYMBOL, mwCell.getSymbol());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCantPutNeighborToBombCells() throws Exception {
        mwCell.setBombNeighbor(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetBombNeighbor() throws Exception {
        mwCell.setBombNeighbor(2);
        mwCell.revealAsNotMine();
        assertEquals("Not yet revealed", '2', mwCell.getSymbol());
        mwCell.setBombNeighbor(11);
    }

    @Test(expected = AlreadyRevealedCaseException.class)
    public void testRevealTwice() throws Exception {
        mwCell.revealAsNotMine();
        mwCell.revealAsNotMine();

    }

    @Test
    public void testIsEmpty() throws Exception {
       assertFalse(mwCell.isEmpty());
    }

}