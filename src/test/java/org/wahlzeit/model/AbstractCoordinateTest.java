package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for {@link AbstractCoordinate}
 */
public class AbstractCoordinateTest {
    @Test
    public void testCartesianDistance() {
        //Arrange
        CartesianCoordinate cartesianCoordinate1 = new CartesianCoordinate(0,0,3);
        CartesianCoordinate cartesianCoordinate2 = new CartesianCoordinate(2,2,2);
        CartesianCoordinate cartesianCoordinate3 = new CartesianCoordinate(0,0,-1);

        //Act
        double distance1To1 = cartesianCoordinate1.getCartesianDistance(cartesianCoordinate1);
        double distance2To2 = cartesianCoordinate2.getCartesianDistance(cartesianCoordinate2);
        double distance3To3 = cartesianCoordinate3.getCartesianDistance(cartesianCoordinate3);

        double distance1To2 = cartesianCoordinate1.getCartesianDistance(cartesianCoordinate2);
        double distance2To1 = cartesianCoordinate2.getCartesianDistance(cartesianCoordinate1);
        double distance1To3 = cartesianCoordinate1.getCartesianDistance(cartesianCoordinate3);
        double distance3To1 = cartesianCoordinate3.getCartesianDistance(cartesianCoordinate1);

        //Arrange
        assertEquals(0, distance1To1, 1e-15);
        assertEquals(0, distance2To2, 1e-15);
        assertEquals(0, distance3To3, 1e-15);

        assertEquals(3, distance1To2,1e-15);
        assertEquals(distance1To2, distance2To1,1e-15);
        assertEquals(4, distance1To3,1e-15);
        assertEquals(distance1To3, distance3To1,1e-15);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCartesianDistanceWithNull() {
        //Arrange
        AbstractCoordinate cartesianCoordinate = new CartesianCoordinate(1,2,3);

        //Act
        double distance = cartesianCoordinate.getCartesianDistance(null);

        //Arrange
        assertEquals(4, distance,1e-15);
    }

    @Test
    public void testCentralAngle() {
        //Arrange
        SphericalCoordinate sphericalCoordinate1 = new SphericalCoordinate(1, Math.PI/ 2, Math.PI/2); // [0,1,0]
        SphericalCoordinate sphericalCoordinate2 = new SphericalCoordinate(1, 0, Math.PI / 2); // [1,0,0]
        SphericalCoordinate sphericalCoordinate3 = new SphericalCoordinate(1, 0,0); // [0,0,1]

        //Act
        double centralAngle1To1 = sphericalCoordinate1.getCentralAngle(sphericalCoordinate1);
        double centralAngle2To2 = sphericalCoordinate2.getCentralAngle(sphericalCoordinate2);
        double centralAngle3To3 = sphericalCoordinate3.getCentralAngle(sphericalCoordinate3);

        double centralAngle1To2 = sphericalCoordinate1.getCentralAngle(sphericalCoordinate2);
        double centralAngle1To3 = sphericalCoordinate1.getCentralAngle(sphericalCoordinate3);
        double centralAngle3To2 = sphericalCoordinate3.getCentralAngle(sphericalCoordinate2);
        double centralAngle2To3 = sphericalCoordinate2.getCentralAngle(sphericalCoordinate3);

        //Assert
        assertEquals(0, centralAngle1To1, 1e-15);
        assertEquals(0, centralAngle2To2, 1e-15);
        assertEquals(0, centralAngle3To3, 1e-15);

        assertEquals(Math.PI / 2, centralAngle1To2,1e-15);
        assertEquals(Math.PI / 2, centralAngle1To3,1e-15);
        assertEquals(Math.PI / 2, centralAngle3To2, 1e-15);
        assertEquals(centralAngle3To2, centralAngle2To3, 1e-15);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCentralAngleWithNull() {
        //Arrange
        SphericalCoordinate sphericalCoordinate = new SphericalCoordinate(1, 0,0); // [0,0,1]

        //Act
        double centralAngle = sphericalCoordinate.getCentralAngle(null);

        //Assert
        assertEquals(0, centralAngle, 1e-15);
    }
}
