package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for {@link CartesianCoordinate}.
 */
public class CartesianCoordinateTest {
    @Test
    public void testAsSphericalCoordinate() {
        //Arrange
        CartesianCoordinate cartesianCoordinate1 = new CartesianCoordinate(0,0,0);
        SphericalCoordinate sphericalCoordinate1 = new SphericalCoordinate(0,0,0);
        CartesianCoordinate cartesianCoordinate2 = new CartesianCoordinate(0,1,0);
        SphericalCoordinate sphericalCoordinate2 = new SphericalCoordinate(1, Math.PI / 2, Math.PI / 2);
        CartesianCoordinate cartesianCoordinate3 = new CartesianCoordinate(1, 0,0);
        SphericalCoordinate sphericalCoordinate3 = new SphericalCoordinate(1, 0,Math.PI / 2);

        //Act
        SphericalCoordinate cartesian1ToSpherical1 = cartesianCoordinate1.asSphericalCoordinate();
        SphericalCoordinate cartesian2ToSpherical2 = cartesianCoordinate2.asSphericalCoordinate();
        SphericalCoordinate cartesian3ToSpherical3 = cartesianCoordinate3.asSphericalCoordinate();

        //Assert
        assertEquals(cartesian1ToSpherical1, sphericalCoordinate1);
        assertEquals(sphericalCoordinate1, cartesian1ToSpherical1);

        assertEquals(cartesian2ToSpherical2, sphericalCoordinate2);
        assertEquals(sphericalCoordinate2, cartesian2ToSpherical2);

        assertEquals(cartesian3ToSpherical3, sphericalCoordinate3);
        assertEquals(sphericalCoordinate3, cartesian3ToSpherical3);
    }

    @Test
    public void testEquality() {
        //Arrange
        CartesianCoordinate cartesianCoordinate1 = new CartesianCoordinate(1,2,3);
        CartesianCoordinate cartesianCoordinate2 = new CartesianCoordinate(1,2,1);

        //Act

        //Assert
        assertEquals(cartesianCoordinate1, cartesianCoordinate1);
        assertNotEquals(cartesianCoordinate1, cartesianCoordinate2);
        assertNotEquals(cartesianCoordinate1, null);
    }
}
