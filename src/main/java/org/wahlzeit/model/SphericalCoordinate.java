package org.wahlzeit.model;

import java.util.Objects;

public class SphericCoordinate implements ICoordinate {
    /**
     * Constructor of this class
     */
    public SphericCoordinate(double r, double p, double q) {
        this.r = r;
        this.p = p;
        this.q = q;
    }

    /**
     * Radius and the two angles describing this Coordinate
     */
    private double r, p, q;

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double x = r * Math.sin(q) * Math.cos(p);
        double y = r * Math.sin(q) * Math.cos(p);
        double z = r * Math.cos(q);
        return new CartesianCoordinate(x, y, z);
    }

    @Override
    public double getCartesianDistance(ICoordinate coordinate) {
        return this.asCartesianCoordinate().getCartesianDistance(coordinate);
    }

    @Override
    public SphericCoordinate asSphericalCoordinate() {
        return this;
    }

    @Override
    public double getCentralAngle(ICoordinate coordinate) {
        SphericCoordinate that = (SphericCoordinate) coordinate;
        double x = Math.cos(that.q) * Math.cos(that.p) - Math.cos(q) * Math.cos(p);
        double y = Math.cos(that.q) * Math.sin(that.p) - Math.cos(q) * Math.sin(p);
        double z = Math.sin(that.q) - Math.sin(q);
        double c = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
        return 2 * Math.asin(c/2);
    }

    @Override
    public boolean isEqual(ICoordinate coordinate) {
        SphericCoordinate that = (SphericCoordinate) coordinate;
        return Double.compare(that.r, r) == 0 &&
                Double.compare(that.p, p) == 0 &&
                Double.compare(that.q, q) == 0;
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
        return Objects.hash(r, p, q);
    }
}
