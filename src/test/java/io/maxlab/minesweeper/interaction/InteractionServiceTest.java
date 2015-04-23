package io.maxlab.minesweeper.interaction;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static io.maxlab.minesweeper.core.PlayerInteractionService.CaseCoordinate;
import static io.maxlab.minesweeper.core.PlayerInteractionService.Configuration;
import static org.junit.Assert.*;

public class InteractionServiceTest {

    @Test
    public void testGetConfiguration() throws Exception {
        final InteractionService interactionService = new InteractionService(new Scanner(new ByteArrayInputStream("3,2,1".getBytes())), System.out);
        final Configuration configuration = interactionService.askConfiguration();
        assertEquals(3, configuration.width);
        assertEquals(2, configuration.height);
        assertEquals(1, configuration.bombCount);
    }

    @Test
    public void testGetConfigurationRetryWithLetters() throws Exception {
        final InteractionService interactionService = new InteractionService(new Scanner(new ByteArrayInputStream("aaaa,2,3\n3,2,1".getBytes())), System.out);
        final Configuration configuration = interactionService.askConfiguration();
        assertEquals(3, configuration.width);
        assertEquals(2, configuration.height);
        assertEquals(1, configuration.bombCount);
    }

    @Test
    public void testGetConfigurationRetryWithTooMuchArgs() throws Exception {
        final InteractionService interactionService = new InteractionService(new Scanner(new ByteArrayInputStream("1,3,4,2,3\n3,2,1".getBytes())), System.out);
        final Configuration configuration = interactionService.askConfiguration();
        assertEquals(3, configuration.width);
        assertEquals(2, configuration.height);
        assertEquals(1, configuration.bombCount);
    }

    @Test
    public void testGetCoordinatesWithoutBombFromInput() throws Exception {
        final InteractionService interactionService = new InteractionService(new Scanner(new ByteArrayInputStream("3,1".getBytes())), System.out);
        final CaseCoordinate coordinate = interactionService.askCoordinates();
        assertEquals(3, coordinate.x);
        assertEquals(1, coordinate.y);
    }

    @Test
    public void testGetCoordinatesRetryWithLetters() throws Exception {
        final InteractionService interactionService = new InteractionService(new Scanner(new ByteArrayInputStream("aaaa,1\n3,1".getBytes())), System.out);
        final CaseCoordinate coordinate = interactionService.askCoordinates();
        assertEquals(3, coordinate.x);
        assertEquals(1, coordinate.y);
    }

    @Test
    public void testGetCoordinatesRetryWithTooMuchArgs() throws Exception {
        final InteractionService interactionService = new InteractionService(new Scanner(new ByteArrayInputStream("1,2,3,4,1\n3,1".getBytes())), System.out);
        final CaseCoordinate coordinate = interactionService.askCoordinates();
        assertEquals(3, coordinate.x);
        assertEquals(1, coordinate.y);
    }

}