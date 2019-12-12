package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * test class for {@link CoordinateFactory}
 */
public class CoordinateFactoryTest {

    //ignoring the singleton pattern for easier testing

    @Test(expected = IllegalStateException.class)
    public void testCartesianCoordinateIllegalInstance() {
        //Arrange
        CoordinateFactory factory = new CoordinateFactory();
        //Act
        factory.getAsCartesianCoordinate(Double.NaN, 0,0);
        //Assert
    }

    @Test(expected = IllegalStateException.class)
    public void testSphericalCoordinateIllegalInstance() {
        //Arrange
        CoordinateFactory factory = new CoordinateFactory();
        //Act
        factory.getAsSphericalCoordinate(-1, 0, 0);
        //Assert
    }
}
