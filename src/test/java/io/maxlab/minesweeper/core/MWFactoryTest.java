package io.maxlab.minesweeper.core;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertNotNull;

public class MWFactoryTest {

    @Test
    public void testFactoryExpectingNoException() throws Exception {
        MWFactory mwFactory = new MWFactory(System.out, new ByteArrayInputStream("3,2,1".getBytes()));
        final MWGame game = mwFactory.getGame();
        assertNotNull(game);
    }

}