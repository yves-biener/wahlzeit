package org.wahlzeit.model;

import java.util.Objects;

public class CartesianCoordinate implements ICoordinate {
    /**
     * Constructor of this class
     */
    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Cartesian coordinates
     */
    private double x, y, z;

    /**
     * get the Cartesian coordinates of this Coordiante
     *
     * @return double[] in form of [x, y, z]
     */
    private double[] getCartesianCoordinates() {
        return new double[]{this.x, this.y, this.z};
    }

    /**
     * Implementation of the distance calculation for getDistance
     *
     * @param other coordinate to calculate the distance to
     * @return distance between this and other coordinate
     */
    private double doGetDistance(CartesianCoordinate other) {
        double[] otherCoordinates = other.getCartesianCoordinates();
        return Math.sqrt(Math.pow(this.x - otherCoordinates[0], 2) +
                Math.pow(this.y - otherCoordinates[1], 2) +
                Math.pow(this.z - otherCoordinates[2], 2));
    }

    /**
     * Convert this coordinate to cartesian coordinate
     *
     * @return cartesian coordinate representing the same point
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    /**
     * Calculate the distance between two cartesian coordinates
     *
     * @param coordinate coordinate to calculate the distance to
     * @return distance between this and coordinate
     */
    @Override
    public double getCartesianDistance(ICoordinate coordinate) {
        if (coordinate == null) throw new IllegalArgumentException("the other Coordinates can not be null");
        return doGetDistance(coordinate.asCartesianCoordinate());
    }

    /**
     * Convert this coordinate to spherical coordinate
     *
     * @return spherical coordinate representing the same point
     */
    @Override
    public SphericalCoordinate asSphericalCoordinate() {
        double r = doGetDistance(new CartesianCoordinate(0, 0, 0)), q = 0, p = 0;
        q = Math.atan2(y, x);
        if (r != 0) p = Math.acos(z / r);
        return new SphericalCoordinate(r, q, p);
    }

    /**
     * Calculate the central angle between two coordinates
     *
     * @param coordinate coordinate to compare calculate the angle to
     * @return central angel between this and coordinate
     */
    @Override
    public double getCentralAngle(ICoordinate coordinate) {
        if (coordinate == null) throw new IllegalArgumentException("the other Coordinates can not be null");
        return this.asSphericalCoordinate().getCentralAngle(coordinate.asSphericalCoordinate());
    }

    /**
     * Compare this Coordinate to other
     *
     * @param other coordinate to compare against
     * @return true if x, y and z are all equal to each other
     */
    @Override
    public boolean isEqual(ICoordinate other) {
        CartesianCoordinate that = other.asCartesianCoordinate();
        double epsilon = 1e-15;
        return Math.abs(that.x - x) <= epsilon && Math.abs(that.y - y) <= epsilon && Math.abs(that.z - z) <= epsilon;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (obj instanceof ICoordinate) {
            return this.isEqual((ICoordinate) obj);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
