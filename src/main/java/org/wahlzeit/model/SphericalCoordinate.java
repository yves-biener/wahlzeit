package org.wahlzeit.model;

import java.util.Objects;

public class SphericalCoordinate extends AbstractCoordinate {
    /**
     * Constructor of this class
     *
     * @throws IllegalStateException
     */
    public SphericalCoordinate(double r, double q, double p) throws IllegalStateException {
        this.r = r;
        this.q = q;
        this.p = p;

        assertClassInvariants();
    }

    /**
     * Radius and the two angles describing this Coordinate
     */
    private double r, q, p;

    /**
     * test if instance is in valid state
     *
     * @throws IllegalStateException
     */
    @Override
    protected void assertClassInvariants() {
        if (Double.isNaN(r) || Double.isNaN(q) || Double.isNaN(p))
            throw new IllegalStateException("r, q, p are not allowed to be NaN");

        if (r < 0)
            throw new IllegalStateException("the radius has to be greater or equals 0 but was: " + r);

        if (q < 0 || (q - Math.PI) > EPSILON)
            throw new IllegalStateException("q has to be between 0 (including) and 180°/PI (including) but was: " + q);

        if (p < 0 || (p - 2 * Math.PI) > EPSILON)
            throw new IllegalStateException("p has to be between 0 (including) and 360°/2*PI (including) but was: " + p);
    }

    /**
     * getter for p
     * 0 <= p <= 2*PI
     *
     * @return p
     */
    public double getP() {
        return p;
    }

    /**
     * getter for q
     * 0 <= q <= PI
     *
     * @return q
     */
    public double getQ() {
        return q;
    }

    /**
     * getter for r
     * r >= 0
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
     * @throws IllegalStateException
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() throws IllegalStateException {
        assertClassInvariants();

        double x = r * Math.sin(p) * Math.cos(q);
        double y = r * Math.sin(p) * Math.sin(q);
        double z = r * Math.cos(p);
        return new CartesianCoordinate(x, y, z);
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
        return this;
    }

    /**
     * Compare this Coordinate to other
     *
     * @param coordinate coordinate to compare against
     * @return true if r, q and p are all equal to each other
     * @throws IllegalArgumentException
     * @throws IllegalStateException
     */
    @Override
    public boolean isEqual(ICoordinate coordinate) throws IllegalArgumentException, IllegalStateException {
        assertNotNullArgument(coordinate);
        assertClassInvariants();

        SphericalCoordinate that = coordinate.asSphericalCoordinate();
        return Math.abs(that.r - r) <= EPSILON &&
                Math.abs(that.q - q) <= EPSILON &&
                Math.abs(that.p - p) <= EPSILON;
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
        return Objects.hash(r, q, p);
    }
}
