package org.wahlzeit.model;

// Coordinate - Collaboration
public interface ICoordinate {
    CartesianCoordinate asCartesianCoordinate() throws IllegalStateException;
    double getCartesianDistance(ICoordinate coordinate) throws IllegalArgumentException;
    SphericalCoordinate asSphericalCoordinate() throws IllegalStateException;
    double getCentralAngle(ICoordinate coordinate) throws IllegalArgumentException;
    boolean isEqual(ICoordinate coordinate) throws IllegalStateException;
}
