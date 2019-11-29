package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for {@link SphericalCoordinate}.
 */
public class SphericalCoordinateTest {
    @Test
    public void testAssertClassInvariants() {
        //Arrange
        SphericalCoordinate sphericalCoordinate1 = new SphericalCoordinate(0, 0, 0);
        SphericalCoordinate sphericalCoordinate2 = new SphericalCoordinate(Double.MAX_VALUE, 0, 0);

        SphericalCoordinate sphericalCoordinate3 = new SphericalCoordinate(0, 0, 2 * Math.PI);

        SphericalCoordinate sphericalCoordinate4 = new SphericalCoordinate(0, Math.PI, 0);

        //Act
        sphericalCoordinate1.assertClassInvariants();
        sphericalCoordinate2.assertClassInvariants();
        sphericalCoordinate3.assertClassInvariants();
        sphericalCoordinate4.assertClassInvariants();

        //Assert
    }

    @Test(expected = IllegalStateException.class)
    public void testAssertClassInvariantsWithNaN() {
        //Arrange
        SphericalCoordinate sphericalCoordinate = new SphericalCoordinate(Double.NaN, 0, 0);

        //Act
        sphericalCoordinate.assertClassInvariants();

        //Assert
    }

    @Test(expected = IllegalStateException.class)
    public void testAssertClassInvariantsWithQNegative() {
        //Arrange
        SphericalCoordinate sphericalCoordinate = new SphericalCoordinate(0, -42, 0);

        //Act
        sphericalCoordinate.assertClassInvariants();

        //Assert
    }

    @Test(expected = IllegalStateException.class)
    public void testAssertClassInvariantsWithQGreaterThanPI() {
        //Arrange
        SphericalCoordinate sphericalCoordinate = new SphericalCoordinate(0, 666, 0);

        //Act
        sphericalCoordinate.assertClassInvariants();

        //Assert
    }

    @Test(expected = IllegalStateException.class)
    public void testAssertClassInvariantsWithPNegative() {
        //Arrange
        SphericalCoordinate sphericalCoordinate = new SphericalCoordinate(0, 0, -17.24);

        //Act
        sphericalCoordinate.assertClassInvariants();

        //Assert
    }

    @Test(expected = IllegalStateException.class)
    public void testAssertClassInvariantsWithPGreaterThen2PI() {
        //Arrange
        SphericalCoordinate sphericalCoordinate = new SphericalCoordinate(0, 0, 0x420b);

        //Act
        sphericalCoordinate.assertClassInvariants();

        //Assert
    }

    @Test
    public void testAsCartesianCoordinate() {
        //Arrange
        SphericalCoordinate sphericalCoordinate1 = new SphericalCoordinate(1, Math.PI / 2, Math.PI / 2); // [0,1,0]
        SphericalCoordinate sphericalCoordinate2 = new SphericalCoordinate(1, 0, Math.PI / 2); // [1,0,0]
        SphericalCoordinate sphericalCoordinate3 = new SphericalCoordinate(1, 0, 0); // [0,0,1]

        CartesianCoordinate cartesianCoordinate1 = new CartesianCoordinate(0, 1, 0);
        CartesianCoordinate cartesianCoordinate2 = new CartesianCoordinate(1, 0, 0);
        CartesianCoordinate cartesianCoordinate3 = new CartesianCoordinate(0, 0, 1);

        //Act
        CartesianCoordinate spherical1ToCartesian1 = sphericalCoordinate1.asCartesianCoordinate();
        CartesianCoordinate spherical2ToCartesian2 = sphericalCoordinate2.asCartesianCoordinate();
        CartesianCoordinate spherical3ToCartesian3 = sphericalCoordinate3.asCartesianCoordinate();

        //Assert
        assertEquals(spherical1ToCartesian1.getX(), cartesianCoordinate1.getX(), 1e-15);
        assertEquals(spherical1ToCartesian1.getY(), cartesianCoordinate1.getY(), 1e-15);
        assertEquals(spherical1ToCartesian1.getZ(), cartesianCoordinate1.getZ(), 1e-15);

        assertEquals(spherical2ToCartesian2.getX(), cartesianCoordinate2.getX(), 1e-15);
        assertEquals(spherical2ToCartesian2.getY(), cartesianCoordinate2.getY(), 1e-15);
        assertEquals(spherical2ToCartesian2.getZ(), cartesianCoordinate2.getZ(), 1e-15);

        assertEquals(spherical3ToCartesian3.getX(), cartesianCoordinate3.getX(), 1e-15);
        assertEquals(spherical3ToCartesian3.getY(), cartesianCoordinate3.getY(), 1e-15);
        assertEquals(spherical3ToCartesian3.getZ(), cartesianCoordinate3.getZ(), 1e-15);
    }

    @Test
    public void testEquality() {
        //Arrange
        SphericalCoordinate sphericalCoordinate1 = new SphericalCoordinate(1, Math.PI / 2, Math.PI / 2); // [0,1,0]
        SphericalCoordinate sphericalCoordinate2 = new SphericalCoordinate(1, 0, Math.PI / 2); // [1,0,0]
        SphericalCoordinate sphericalCoordinate3 = new SphericalCoordinate(1, 0, 0); // [0,0,1]

        //Act

        //Assert
        assertEquals(sphericalCoordinate1, sphericalCoordinate1);
        assertEquals(sphericalCoordinate2, sphericalCoordinate2);
        assertEquals(sphericalCoordinate3, sphericalCoordinate3);

        assertNotEquals(sphericalCoordinate1, sphericalCoordinate2);
        assertNotEquals(sphericalCoordinate3, sphericalCoordinate1);
        assertNotEquals(sphericalCoordinate2, sphericalCoordinate3);

        assertNotEquals(sphericalCoordinate1, null);
    }
}
