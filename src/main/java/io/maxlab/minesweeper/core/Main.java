package io.maxlab.minesweeper.core;

import java.io.IOException;

/**
 *
 */
public class Main {
    private static final MWFactory factory = new MWFactory();

    public static void main(String[] args) throws IOException {
        factory.getGame().start();
    }
}
