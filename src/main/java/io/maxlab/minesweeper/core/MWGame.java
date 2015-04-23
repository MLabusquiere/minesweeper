package io.maxlab.minesweeper.core;

import io.maxlab.minesweeper.core.exception.AlreadyRevealedCaseException;
import io.maxlab.minesweeper.core.exception.MWException;
import io.maxlab.minesweeper.core.exception.NotificationException;
import io.maxlab.minesweeper.core.exception.WrongCoordinatesException;

import java.io.IOException;

import static io.maxlab.minesweeper.core.PlayerInteractionService.CaseCoordinate;

public class MWGame {

    private final MWGrid mwGrid;
    private final PlayerInteractionService playerInteractionService;
    private boolean isRunning = false;

    public MWGame(MWGrid mwGrid, PlayerInteractionService playerInteractionService) {
        this.mwGrid = mwGrid;
        this.playerInteractionService = playerInteractionService;
    }

    public void start() {
        isRunning = true;
        run();
    }

    private void run() {
        while (isRunning) {
            try {
                runSequence();
            } catch (NotificationException e) {
                playerInteractionService.handleNotificationError(e);
            } catch (WrongCoordinatesException e) {
                playerInteractionService.handleCoordinatesError(e);
            } catch (AlreadyRevealedCaseException e) {
                playerInteractionService.handleAlreadyRevealedCaseException(e);
            } catch (MWException e) {
                playerInteractionService.handleMWError(e);
            } catch (Exception e) {
                playerInteractionService.handleNotificationError("Error during the game. Sorry. Stopping the game");
                isRunning = false;
            }
        }
    }

    void runSequence() {
        mwGrid.print();
        CaseCoordinate caseCoordinate = playerInteractionService.askCoordinates();
        if (!mwGrid.reveal(caseCoordinate.x, caseCoordinate.y)) {
            loose();
        }
        if (mwGrid.isAllCellHadBeenRevealed()) {
            win();
        }
    }

    void win() {
        playerInteractionService.handleWin("Congratulation you win :). Please play again by relaunching the software");
        mwGrid.print();
        close();
    }

    void loose() {
        playerInteractionService.handleLoose("Sorry you loose :(. Please play again by relaunching the software");
        mwGrid.print();
        close();
    }

    public void close() {
        isRunning = false;
        try {
            playerInteractionService.close();
        } catch (IOException e) {
            //Should have a logger here but we are closing the game..
        }
    }

    /**
     * Not used but do work
     */
    private void replay() {
        playerInteractionService.print("Let's play again !!");
        mwGrid.reinit();
    }
}
