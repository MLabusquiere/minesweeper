package io.maxlab.minesweeper.print;


import io.maxlab.minesweeper.core.MWGrid;
import io.maxlab.minesweeper.core.MWGridPrinter;
import io.maxlab.minesweeper.grid.MockGridFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class AsciiMWGridPrinterTest {

    private final String expectedOutputBeforeReveal =
            "  0 1 2 3\n" +
                    "0        \n" +
                    "1        \n" +
                    "2        \n" +
                    "3        \n" +
                    "4        \n\n";
    private final String expectedOutputAfterReveal =
            "  0 1 2 3\n" +
                    "0     1 0\n" +
                    "1     1 0\n" +
                    "2     1 0\n" +
                    "3     1 1\n" +
                    "4        \n\n";
    private MWGrid mwGrid;
    private ByteArrayOutputStream baos;
    private final Character[] map = new Character[]{
            '1', '1', '1', '0',
            '1', 'X', '1', '0',
            '1', '1', '1', '0',
            '0', '0', '1', '1',
            '0', '0', '1', 'X'};

    @Before
    public void setUp() throws Exception {
        baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        MWGridPrinter printer = new AsciiMWGridPrinter(printStream);
        mwGrid = MockGridFactory.getDefaultMWGrid(map, 4, 5, 2, printer);

    }

    @Test
    public void testPrint() throws Exception {
        mwGrid.print();
        assertEquals(expectedOutputBeforeReveal, baos.toString());
    }

    @Test
    public void testPrintAfterReveal() throws Exception {
        mwGrid.reveal(3, 0);
        mwGrid.print();
        assertEquals(expectedOutputAfterReveal, baos.toString());
    }
}