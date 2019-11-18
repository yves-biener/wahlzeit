package org.wahlzeit.model;

public interface ICoordinate {
    CartesianCoordinate asCartesianCoordinate();
    double getCartesianDistance(ICoordinate coordinate);
    SphericalCoordinate asSphericalCoordinate();
    double getCentralAngle(ICoordinate coordinate);
    boolean isEqual(ICoordinate coordinate);
    boolean equals(Object obj);
}
