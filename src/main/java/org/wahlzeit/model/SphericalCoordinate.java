package org.wahlzeit.model;

import java.util.Objects;

public class SphericalCoordinate extends AbstractCoordinate {
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
     * getter for p
     *
     * @return p
     */
    public double getP() {
        return p;
    }

    /**
     * getter for q
     *
     * @return q
     */
    public double getQ() {
        return q;
    }

    /**
     * getter for r
     *
     * @return r
     */
    public double getR() {
        return r;
    }

    /**
     * Convert this coordinate to cartesian coordinate
     *
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
     * Convert this coordinate to spherical coordinate
     *
     * @return spherical coordinate representing the same point
     */
    @Override
    public SphericalCoordinate asSphericalCoordinate() {
        return this;
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
    public int hashCode() {
        return Objects.hash(getR(), getQ(), getP());
    }
}
