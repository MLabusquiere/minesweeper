package io.maxlab.minesweeper.map.cell;

import io.maxlab.minesweeper.core.MWCell;
import io.maxlab.minesweeper.core.exception.AlreadyRevealedCaseException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultCellTest {

    private MWCell mwCell;

    @Before
    public void setUp() throws Exception {
        mwCell = new DefaultCell();

    }

    @Test
    public void testIsBomb() throws Exception {
        assertFalse("The default mwCase do not hold bomb it is the bomb mwCase", mwCell.isBomb());
    }

    @Test
    public void testGetSymbol() throws Exception {

        assertEquals("Not yet revealed", MWCell.NOT_REVEALED_SYMBOL, mwCell.getSymbol());
        mwCell.setBombNeighbor(2);
        assertEquals("Not yet revealed", MWCell.NOT_REVEALED_SYMBOL, mwCell.getSymbol());
        mwCell.revealAsNotMine();
        assertEquals("Revealed", '2', mwCell.getSymbol());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetBombNeighbor() throws Exception {
        mwCell.setBombNeighbor(2);
        mwCell.revealAsNotMine();
        assertEquals("Not yet revealed", '2', mwCell.getSymbol());
        mwCell.setBombNeighbor(11);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeBound() throws Exception {
        mwCell.setBombNeighbor(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPositiveBound() throws Exception {
        mwCell.setBombNeighbor(9);
    }
    @Test
    public void testReveal() throws Exception {
        //Test bomb
        mwCell.setBombNeighbor(2);
        assertEquals("Not yet revealed", MWCell.NOT_REVEALED_SYMBOL, mwCell.getSymbol());
        mwCell.revealAsNotMine();
        assertEquals("Not yet revealed", '2', mwCell.getSymbol());
    }

    @Test(expected = AlreadyRevealedCaseException.class)
    public void testRevealTwice() throws Exception {
        mwCell.setBombNeighbor(2);
        mwCell.revealAsNotMine();
        mwCell.revealAsNotMine();

    }

    @Test
    public void testIsEmpty() throws Exception {
        mwCell.setBombNeighbor(0);
        assertTrue("An empty mwCase is a mwCase without bomb neighbor", mwCell.isEmpty());
    }


    @Test
    public void testIsNotEmpty() throws Exception {
        mwCell.setBombNeighbor(0);
        assertTrue("An empty mwCase is a mwCase without bomb neighbor", mwCell.isEmpty());
    }

    @Test
    public void testIsRevealed() throws Exception {
        mwCell.setBombNeighbor(0);
        assertFalse("Not yet revealed", mwCell.isRevealed());
        assertEquals(MWCell.NOT_REVEALED_SYMBOL, mwCell.getSymbol());
        mwCell.revealAsNotMine();
        assertTrue("Revealed", mwCell.isRevealed());
        assertEquals('0', mwCell.getSymbol());
    }
}