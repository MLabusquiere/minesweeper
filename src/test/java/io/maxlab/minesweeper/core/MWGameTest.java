package io.maxlab.minesweeper.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;

import static io.maxlab.minesweeper.core.PlayerInteractionService.CaseCoordinate;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MWGameTest {
    @Mock
    private PlayerInteractionService playerInteractionService;
    @Mock
    private MWGrid mwGrid;

    @Test
    public void testCheckCallForOneSequenceRun() throws Exception {
        when(playerInteractionService.askCoordinates()).thenReturn(new CaseCoordinate(1, 2));
        doNothing().when(playerInteractionService).close();
        when(mwGrid.reveal(1, 2)).thenReturn(true);
        doNothing().when(mwGrid).print();
        final MWGame game = new MWGame(mwGrid, playerInteractionService);
        game.runSequence();
        final VerificationMode timeForOneSequence = times(1);
        verify(mwGrid, timeForOneSequence).print();
        verify(playerInteractionService, timeForOneSequence).askCoordinates();
        verify(mwGrid, timeForOneSequence).reveal(1, 2);
        verify(mwGrid, timeForOneSequence).isAllCellHadBeenRevealed();
        verifyNoMoreInteractions(playerInteractionService, mwGrid);
    }

    @Test
    public void testClose() throws Exception {
        final VerificationMode timeForOneSequence = times(1);
        doNothing().when(playerInteractionService).close();
        final MWGame game = new MWGame(mwGrid, playerInteractionService);
        game.close();
        verify(playerInteractionService, timeForOneSequence).close();
        verifyZeroInteractions(mwGrid);
        verifyNoMoreInteractions(playerInteractionService);
    }

}