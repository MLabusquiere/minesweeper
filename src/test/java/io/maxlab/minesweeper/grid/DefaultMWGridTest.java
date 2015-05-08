package io.maxlab.minesweeper.grid;

import io.maxlab.minesweeper.core.MWGrid;
import io.maxlab.minesweeper.core.MWGridPrinter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultMWGridTest {

    private final Character[] map = new Character[]{
            '1', '1', '1', '0', '0',
            '1', 'X', '1', '0', '0',
            '1', '1', '1', '0', '0',
            '0', '0', '0', '1', '1',
            '0', '0', '0', '1', 'X'};
    @Mock
    private MWGridPrinter printer;
    private final int bombCount = 2;
    private final int height = 5;
    private final int width = 5;
    private MWGrid grid;

    @Before
    public void setUp() throws Exception {
        grid = MockGridFactory.getDefaultMWGrid(map, width, height, bombCount, printer);
    }

    @Test
    public void testisRevealedIsRecursifWhenHit0() throws Exception {
        assertTrue(grid.reveal(4, 0));
        assertTrue(grid.getCase(4, 0).isRevealed());
        assertTrue(grid.getCase(4, 2).isRevealed());
        assertTrue(grid.getCase(3, 0).isRevealed());
        assertTrue(grid.getCase(3, 1).isRevealed());
        assertTrue(grid.getCase(3, 2).isRevealed());
        assertTrue(grid.getCase(4, 1).isRevealed());

    }


    @Test
    public void testGetBox() throws Exception {
        assertTrue(grid.getCase(1, 1).isBomb());
        assertTrue(grid.getCase(4, 4).isBomb());
    }


    @Test
    public void testReinit() throws Exception {
        assertTrue(grid.reveal(0, 0));
        assertTrue(grid.getCase(0, 0).isRevealed());
        grid.reinit();
        assertFalse(grid.getCase(0, 0).isRevealed());
    }

    @Test
    public void testWidth() throws Exception {
        assertEquals(width, grid.width());
    }

    @Test
    public void testHeight() throws Exception {
        assertEquals(height, grid.height());

    }

    @Test
    public void testPrint() throws Exception {
        doNothing().when(printer).print(grid);
        grid.print();
        verify(printer, times(1)).print(grid);
        verifyNoMoreInteractions(printer);
    }

}