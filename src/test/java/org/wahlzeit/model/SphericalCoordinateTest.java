package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for {@link SphericalCoordinate}.
 */
public class SphericalCoordinateTest {
    @Test
    public void testCentralAngle() {
        SphericalCoordinate sphericalCoordinate1 = new SphericalCoordinate(1, Math.PI/ 2, Math.PI/2); // [0,1,0]
        SphericalCoordinate sphericalCoordinate2 = new SphericalCoordinate(1, 0, Math.PI / 2); // [1,0,0]
        SphericalCoordinate sphericalCoordinate3 = new SphericalCoordinate(1, 0,0); // [0,0,1]
        assertEquals(0, sphericalCoordinate1.getCentralAngle(sphericalCoordinate1), 1e-15);
        assertEquals(Math.PI / 2, sphericalCoordinate1.getCentralAngle(sphericalCoordinate2),1e-15);
        assertEquals(Math.PI / 2, sphericalCoordinate1.getCentralAngle(sphericalCoordinate3),1e-15);
        assertEquals(Math.PI / 2, sphericalCoordinate3.getCentralAngle(sphericalCoordinate2), 1e-15);
        assertEquals(Math.PI / 2, sphericalCoordinate2.getCentralAngle(sphericalCoordinate3), 1e-15);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCentralAngleWithNull() {
        SphericalCoordinate sphericalCoordinate = new SphericalCoordinate(1, 0,0);
        assertEquals(0, sphericalCoordinate.getCentralAngle(null), 1e-15);
    }

    @Test
    public void testAsCartesianCoordinate() {
        SphericalCoordinate sphericalCoordinate1 = new SphericalCoordinate(1, Math.PI/ 2, Math.PI/2); // [0,1,0]
        SphericalCoordinate sphericalCoordinate2 = new SphericalCoordinate(1, 0, Math.PI / 2); // [1,0,0]
        SphericalCoordinate sphericalCoordinate3 = new SphericalCoordinate(1, 0,0); // [0,0,1]
        CartesianCoordinate cartesianCoordinate1 = new CartesianCoordinate(0,1,0);
        CartesianCoordinate cartesianCoordinate2 = new CartesianCoordinate(1,0,0);
        CartesianCoordinate cartesianCoordinate3 = new CartesianCoordinate(0,0,1);
        assertEquals(sphericalCoordinate1.asCartesianCoordinate(), cartesianCoordinate1);
        assertEquals(sphericalCoordinate2.asCartesianCoordinate(), cartesianCoordinate2);
        assertEquals(sphericalCoordinate3.asCartesianCoordinate(), cartesianCoordinate3);
    }

    @Test
    public void testEquality() {
        SphericalCoordinate sphericalCoordinate1 = new SphericalCoordinate(1, Math.PI/ 2, Math.PI/2); // [0,1,0]
        SphericalCoordinate sphericalCoordinate2 = new SphericalCoordinate(1, 0, Math.PI / 2); // [1,0,0]
        SphericalCoordinate sphericalCoordinate3 = new SphericalCoordinate(1, 0,0); // [0,0,1]
        CartesianCoordinate cartesianCoordinate1 = new CartesianCoordinate(0,1,0);
        CartesianCoordinate cartesianCoordinate2 = new CartesianCoordinate(1,0,0);
        CartesianCoordinate cartesianCoordinate3 = new CartesianCoordinate(0,0,1);
        assertEquals(sphericalCoordinate1, sphericalCoordinate1);
        assertEquals(cartesianCoordinate1.asSphericalCoordinate(), sphericalCoordinate1);
        assertEquals(cartesianCoordinate2.asSphericalCoordinate(), sphericalCoordinate2);
        assertEquals(cartesianCoordinate3.asSphericalCoordinate(), sphericalCoordinate3);
    }
}
