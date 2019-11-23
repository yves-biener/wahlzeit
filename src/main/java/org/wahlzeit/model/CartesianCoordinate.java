package org.wahlzeit.model;

import java.util.Objects;

public class CartesianCoordinate extends AbstractCoordinate {
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
     * getter for x
     *
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * getter for y
     *
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * getter for z
     *
     * @return z
     */
    public double getZ() {
        return z;
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
     * Convert this coordinate to spherical coordinate
     *
     * @return spherical coordinate representing the same point
     */
    @Override
    public SphericalCoordinate asSphericalCoordinate() {
        double r = getCartesianDistance(new CartesianCoordinate(0, 0, 0)), q = 0, p = 0;
        q = Math.atan2(y, x);
        if (r != 0) p = Math.acos(z / r);
        return new SphericalCoordinate(r, q, p);
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
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ());
    }
}
