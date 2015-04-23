package io.maxlab.minesweeper.core;

import io.maxlab.minesweeper.map.MockGridFactory;
import io.maxlab.minesweeper.print.MWGridPrinterFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static io.maxlab.minesweeper.interaction.MockInteractionFactory.getService;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * TODO
 */
public class MWGameScenarioTest {

    private Character[] map = new Character[]{
            '1', '1', '1', '0', '0',
            '1', 'X', '1', '0', '0',
            '1', '1', '1', '0', '0',
            '0', '0', '0', '0', '0',
            '0', '0', '0', '0', '0'};
    private MWGrid defaultMWMap;

    @Before
    public void setUp() throws Exception {
        MWGridPrinterFactory printFactory = new MWGridPrinterFactory();
        final MWGridPrinter newMapPrinter = printFactory.getNewMapPrinter(System.out);
        defaultMWMap = MockGridFactory.getDefaultMWGrid(this.map, 5, 5, 1, newMapPrinter);
    }

    @Test
    public void testLooseScenario() throws Exception {
        String looseScenario = "1,1\n";
        final Scanner reader = new Scanner(new ByteArrayInputStream(looseScenario.getBytes()));
        final PlayerInteractionService service = getService(System.out, reader);
        MWGame mwGame = new MWGame(defaultMWMap, service);
        mwGame = spy(mwGame);
        mwGame.start();
        verify(mwGame, times(1)).loose();

    }

    @Test
    public void testWinScenario() throws Exception {
        String winScenario = "0,0\n" +// 1 on first line
                             "1,0\n" +
                             "2,0\n" +
                             "0,1\n" +// 1 in second line
                             "3,1\n" +
                             "0,2\n" +// 1 on first line
                             "1,2\n" +
                             "2,2\n" +
                             "3,3";  // reveal all 0
        final Scanner reader = new Scanner(new ByteArrayInputStream(winScenario.getBytes()));
        final PlayerInteractionService service = getService(System.out, reader);
        MWGame mwGame = new MWGame(defaultMWMap, service);
        mwGame = spy(mwGame);
        mwGame.start();
        verify(mwGame, times(1)).win();

    }

}
