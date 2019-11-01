package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for {@link Coordinate}.
 */
public class CoordinateTest {
    @Test
    public void testDistance() {
        Coordinate coordinate1 = new Coordinate(0,0,3);
        Coordinate coordinate2 = new Coordinate(2,2,2);
        Coordinate coordinate3 = new Coordinate(0,0,-1);
        assertEquals(coordinate1.getDistance(coordinate2), 3, 1e-15);
        assertEquals(coordinate2.getDistance(coordinate1), 3, 1e-15);
        assertEquals(coordinate1.getDistance(coordinate3), 4, 1e-15);
        assertEquals(coordinate3.getDistance(coordinate1), 4, 1e-15);
        Coordinate coordinate4 = new Coordinate(0,0,0);
        assertEquals(coordinate4.getDistance(coordinate4), 0, 1e-15);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDistanceWithNull() {
        Coordinate coordinate = new Coordinate(1,2,3);
        assertEquals(coordinate.getDistance(null), 4, 1e-15);
    }

    @Test
    public void testEquality() {
        Coordinate coordinate = new Coordinate(1,2,3);
        Coordinate coordinate1 = new Coordinate(1,2,1);
        assertEquals(coordinate, coordinate);
        assertNotEquals(coordinate, coordinate1);
        assertNotEquals(coordinate, null);
    }
}
