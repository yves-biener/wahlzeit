package org.wahlzeit.model;

public interface ICoordinate {
    CartesianCoordinate asCartesianCoordinate() throws IllegalStateException;
    double getCartesianDistance(ICoordinate coordinate) throws IllegalArgumentException;
    SphericalCoordinate asSphericalCoordinate() throws IllegalStateException;
    double getCentralAngle(ICoordinate coordinate) throws IllegalArgumentException;
    boolean isEqual(ICoordinate coordinate) throws IllegalArgumentException, IllegalStateException;
    boolean equals(Object obj);
}
