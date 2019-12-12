package org.wahlzeit.model;

import java.util.Objects;

public class SphericalCoordinate extends AbstractCoordinate {
    /**
     * Constructor of this class
     *
     * @throws IllegalStateException
     */
    SphericalCoordinate(double r, double q, double p) throws IllegalStateException {
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
     * implementation of asCartesianCoordinate
     *
     * @return CartesianCoordinate representing this instance (representing the same point in space)
     */
    private CartesianCoordinate doAsCartesianCoordinate() {
        double x = r * Math.sin(p) * Math.cos(q);
        double y = r * Math.sin(p) * Math.sin(q);
        double z = r * Math.cos(p);
        return CoordinateFactory.getInstance().getAsCartesianCoordinate(x, y, z);
    }

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

        return doAsCartesianCoordinate();
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
}
