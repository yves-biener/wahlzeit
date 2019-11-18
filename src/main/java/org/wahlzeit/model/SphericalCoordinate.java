package org.wahlzeit.model;

import java.util.Objects;

public class SphericalCoordinate implements ICoordinate {
    /**
     * Constructor of this class
     */
    public SphericalCoordinate(double r, double q, double p) {
        this.r = r;
        this.q = q;
        this.p = p;
    }

    /**
     * Radius and the two angles describing this Coordinate
     */
    private double r, q, p;

    /**
     * Internal implementation for getCentralAngel
     * @param coordinate spherical coordinate to calculate the angle to
     * @return angle between this and coordinate
     */
    private double doGetCentralAngle(ICoordinate coordinate) {
        double chord = this.asCartesianCoordinate().getCartesianDistance(coordinate);
        return 2 * Math.asin(chord/2);
    }

    /**
     * Convert this coordinate to cartesian coordinate
     * @return cartesian coordinate describing the same point
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double x = r * Math.sin(p) * Math.cos(q);
        double y = r * Math.sin(p) * Math.sin(q);
        double z = r * Math.cos(p);
        return new CartesianCoordinate(x, y, z);
    }

    /**
     * Calculate the distance between two cartesian coordinates
     * @param coordinate coordinate to calculate the distance to
     * @return distance between this and coordinate
     */
    @Override
    public double getCartesianDistance(ICoordinate coordinate) {
        if (coordinate == null) throw new IllegalArgumentException("the other Coordinates can not be null");
        return this.asCartesianCoordinate().getCartesianDistance(coordinate.asCartesianCoordinate());
    }

    /**
     * Convert this coordinate to spherical coordinate
     * @return spherical coordinate representing the same point
     */
    @Override
    public SphericalCoordinate asSphericalCoordinate() {
        return this;
    }

    /**
     * Calculate the central angle between two coordinates
     * @param coordinate coordinate to compare calculate the angle to
     * @return central angel between this and coordinate
     */
    @Override
    public double getCentralAngle(ICoordinate coordinate) {
        if (coordinate == null) throw new IllegalArgumentException("the other Coordinates can not be null");
        return doGetCentralAngle(coordinate.asSphericalCoordinate());
    }

    /**
     * Compare this Coordinate to other
     *
     * @param coordinate coordinate to compare against
     * @return true if r, q and p are all equal to each other
     */
    @Override
    public boolean isEqual(ICoordinate coordinate) {
        SphericalCoordinate that = coordinate.asSphericalCoordinate();
        double epsilon = 1e-15;
        return Math.abs(that.r - r) <= epsilon && Math.abs(that.q - q) <= epsilon && Math.abs(that.p - p) <= epsilon;
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
        return Objects.hash(r, q, p);
    }
}
