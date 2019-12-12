package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for {@link CartesianCoordinate}.
 */
public class CartesianCoordinateTest {
    @Test
    public void testAssertClassInvariants() {
        //Arrange
        CartesianCoordinate cartesianCoordinate1 = new CartesianCoordinate(0, 0, 0);

        CartesianCoordinate cartesianCoordinate2 = new CartesianCoordinate(Double.MAX_VALUE, 0, 0);
        CartesianCoordinate cartesianCoordinate3 = new CartesianCoordinate(0, Double.MAX_VALUE, 0);
        CartesianCoordinate cartesianCoordinate4 = new CartesianCoordinate(0, 0, Double.MAX_VALUE);

        CartesianCoordinate cartesianCoordinate5 = new CartesianCoordinate(Double.MIN_VALUE, 0, 0);
        CartesianCoordinate cartesianCoordinate6 = new CartesianCoordinate(0, Double.MIN_VALUE, 0);
        CartesianCoordinate cartesianCoordinate7 = new CartesianCoordinate(0, 0, Double.MIN_VALUE);

        //Act
        cartesianCoordinate1.assertClassInvariants();
        cartesianCoordinate2.assertClassInvariants();
        cartesianCoordinate3.assertClassInvariants();
        cartesianCoordinate4.assertClassInvariants();
        cartesianCoordinate5.assertClassInvariants();
        cartesianCoordinate6.assertClassInvariants();
        cartesianCoordinate7.assertClassInvariants();

        //Assert
    }

    @Test(expected = IllegalStateException.class)
    public void testAssertClassInvariantsWithNaN() {
        //Arrange
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(Double.NaN, 1, 2);

        //Act
        cartesianCoordinate.assertClassInvariants();

        //Assert
    }

    @Test
    public void testAsSphericalCoordinate() {
        //Arrange
        CartesianCoordinate cartesianCoordinate1 = CoordinateFactory.getInstance().getAsCartesianCoordinate(0, 0, 0);
        SphericalCoordinate sphericalCoordinate1 = CoordinateFactory.getInstance().getAsSphericalCoordinate(0, 0, 0);

        CartesianCoordinate cartesianCoordinate2 = CoordinateFactory.getInstance().getAsCartesianCoordinate(0, 1, 0);
        SphericalCoordinate sphericalCoordinate2 = CoordinateFactory.getInstance().getAsSphericalCoordinate(1, Math.PI / 2, Math.PI / 2);

        CartesianCoordinate cartesianCoordinate3 = CoordinateFactory.getInstance().getAsCartesianCoordinate(1, 0, 0);
        SphericalCoordinate sphericalCoordinate3 = CoordinateFactory.getInstance().getAsSphericalCoordinate(1, 0, Math.PI / 2);

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
        CartesianCoordinate cartesianCoordinate1 = CoordinateFactory.getInstance().getAsCartesianCoordinate(1, 2, 3);
        CartesianCoordinate cartesianCoordinate2 = CoordinateFactory.getInstance().getAsCartesianCoordinate(1, 2, 1);

        //Act

        //Assert
        assertEquals(cartesianCoordinate1, cartesianCoordinate1);
        assertNotEquals(cartesianCoordinate1, cartesianCoordinate2);
        assertNotEquals(cartesianCoordinate1, null);
    }
}
