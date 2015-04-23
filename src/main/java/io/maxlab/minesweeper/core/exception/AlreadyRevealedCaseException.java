package io.maxlab.minesweeper.core.exception;

public class AlreadyRevealedCaseException extends MWException {
    public AlreadyRevealedCaseException() {
        super("This case had been already releaved");
    }
}
