package io.maxlab.minesweeper.map;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GridStructureTest {

    private final Character[] map = new Character[]{
            '1', '1', '1', '0', '0',
            '1', 'X', '1', '0', '0',
            '1', '1', '1', '0', '0',
            '0', '0', '0', '1', '1',
            '0', '0', '0', '1', 'X'};
    private GridStructure gridStructure;
    private final int width = 5;
    private int bombNumber = 2;

    @Before
    public void setUp() throws Exception {
        gridStructure = MockGridFactory.getGridStructure(map, width, 5, bombNumber);
    }

    @Test
    public void testGetNeighborsInCorner() throws Exception {
        List<Integer> neighborIndexes = gridStructure.getNeighborIndexes(getCoordinates(0, 0));
        assertEquals("We have 3 neighborg in a corner", neighborIndexes.size(), 3);
        assertTrue(neighborIndexes.contains(1));
        assertTrue(neighborIndexes.contains(5));
        assertTrue(neighborIndexes.contains(6));

        neighborIndexes = gridStructure.getNeighborIndexes(getCoordinates(4, 4));
        assertEquals("We have 3 neighbourg in a corner", neighborIndexes.size(), 3);
        assertTrue(neighborIndexes.contains(getCoordinates(3, 4)));
        assertTrue(neighborIndexes.contains(getCoordinates(4, 3)));
        assertTrue(neighborIndexes.contains(getCoordinates(3, 3)));
    }

    @Test
    public void testGetNeighborsInMiddle() throws Exception {
        List<Integer> neighborIndexes = gridStructure.getNeighborIndexes(getCoordinates(2, 2));
        assertEquals("We have 3 neighbor in a corner", neighborIndexes.size(), 8);
    }

    @Test
    public void testGetNeighborIndexes() throws Exception {
        for (int i = 0; i < gridStructure.size(); i++) {
            assertTrue("The get neighbor and the one with index do not send back the same result",
                    gridStructure.getNeighborIndexes(i).stream().
                            map(gridStructure::getBox).
                            collect(toList()).
                            equals(gridStructure.getNeighbors(i))
            );
        }
    }

    @Test
    public void testWidth() throws Exception {
        assertEquals(5, gridStructure.width());
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(25, gridStructure.size());

    }

    private int getCoordinates(int x, int y) {
        return y * width + x;
    }

}