package org.wahlzeit.model;

import java.util.Objects;

public class CartesianCoordinate extends AbstractCoordinate {
    /**
     * Constructor of this class
     *
     * @throws IllegalStateException
     */
    public CartesianCoordinate(double x, double y, double z) throws IllegalStateException {
        this.x = x;
        this.y = y;
        this.z = z;

        assertClassInvariants();
    }

    /**
     * Cartesian coordinates
     */
    private double x, y, z;

    /**
     * implementation of asSphericalCoordinate
     *
     * @return SphericalCoordinate representing this instance (representing the same point in space)
     */
    private SphericalCoordinate doAsSphericalCoordinate() {
        double r = getCartesianDistance(new CartesianCoordinate(0, 0, 0)), q, p = 0;
        q = Math.atan2(y, x);
        if (r != 0) p = Math.acos(z / r);
        return new SphericalCoordinate(r, q, p);
    }

    /**
     * test if instance is in valid state
     *
     * @throws IllegalStateException
     */
    @Override
    protected void assertClassInvariants() {
        if (Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z))
            throw new IllegalStateException("X, Y, Z Coordinates are not allowed to be NaN");
    }

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
     * @throws IllegalStateException
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() throws IllegalStateException {
        assertClassInvariants();
        return this;
    }

    /**
     * Convert this coordinate to spherical coordinate
     *
     * @return spherical coordinate representing the same point
     * @throws IllegalStateException
     */
    @Override
    public SphericalCoordinate asSphericalCoordinate() throws IllegalStateException {
        assertClassInvariants();

        return doAsSphericalCoordinate();
    }

    /**
     * Compare this Coordinate to other
     *
     * @param coordinate coordinate to compare against
     * @return true if x, y and z are all equal to each other
     * @throws IllegalArgumentException
     * @throws IllegalStateException
     */
    @Override
    public boolean isEqual(ICoordinate coordinate) throws IllegalArgumentException, IllegalStateException {
        assertNotNullArgument(coordinate);
        assertClassInvariants();

        CartesianCoordinate that = coordinate.asCartesianCoordinate();
        return Math.abs(that.x - x) <= EPSILON &&
                Math.abs(that.y - y) <= EPSILON &&
                Math.abs(that.z - z) <= EPSILON;
    }

    /**
     * hashCode method for java internal structures
     *
     * @return hashCode for this instance
     * @throws IllegalStateException
     */
    @Override
    public int hashCode() throws IllegalStateException {
        assertClassInvariants();
        return Objects.hash(x, y, z);
    }
}
