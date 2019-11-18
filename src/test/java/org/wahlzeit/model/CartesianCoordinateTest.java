package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for {@link CartesianCoordinate}.
 */
public class CartesianCoordinateTest {
    @Test
    public void testDistance() {
        CartesianCoordinate cartesianCoordinate1 = new CartesianCoordinate(0,0,3);
        CartesianCoordinate cartesianCoordinate2 = new CartesianCoordinate(2,2,2);
        CartesianCoordinate cartesianCoordinate3 = new CartesianCoordinate(0,0,-1);
        assertEquals(3, cartesianCoordinate1.getCartesianDistance(cartesianCoordinate2),1e-15);
        assertEquals(3, cartesianCoordinate2.getCartesianDistance(cartesianCoordinate1),1e-15);
        assertEquals(4, cartesianCoordinate1.getCartesianDistance(cartesianCoordinate3),1e-15);
        assertEquals(4, cartesianCoordinate3.getCartesianDistance(cartesianCoordinate1),1e-15);
        CartesianCoordinate cartesianCoordinate4 = new CartesianCoordinate(0,0,0);
        assertEquals(0, cartesianCoordinate4.getCartesianDistance(cartesianCoordinate4),1e-15);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDistanceWithNull() {
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(1,2,3);
        assertEquals(4, cartesianCoordinate.getCartesianDistance(null),1e-15);
    }

    @Test
    public void testAsSphericCoordinate() {
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(0,0,0);
        SphericalCoordinate sphericalCoordinate = new SphericalCoordinate(0,0,0);
        assertEquals(cartesianCoordinate.asSphericalCoordinate(), sphericalCoordinate);
        assertEquals(sphericalCoordinate, cartesianCoordinate.asSphericalCoordinate());
        cartesianCoordinate = new CartesianCoordinate(1, 0,0);
        sphericalCoordinate = new SphericalCoordinate(1, 0,Math.PI / 2);
        assertEquals(cartesianCoordinate.asSphericalCoordinate(), sphericalCoordinate);
        assertEquals(sphericalCoordinate, cartesianCoordinate.asSphericalCoordinate());
        cartesianCoordinate = new CartesianCoordinate(0,1,0);
        sphericalCoordinate = new SphericalCoordinate(1, Math.PI / 2, Math.PI / 2);
        assertEquals(cartesianCoordinate.asSphericalCoordinate(), sphericalCoordinate);
        assertEquals(sphericalCoordinate, cartesianCoordinate.asSphericalCoordinate());
    }

    @Test
    public void testEquality() {
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(1,2,3);
        CartesianCoordinate cartesianCoordinate1 = new CartesianCoordinate(1,2,1);
        assertEquals(cartesianCoordinate, cartesianCoordinate);
        assertNotEquals(cartesianCoordinate, cartesianCoordinate1);
        assertNotEquals(cartesianCoordinate, null);
    }
}
