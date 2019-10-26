package org.wahlzeit.model;

import java.util.Arrays;

public class Coordinate {
    /**
     * Constructor of this class
     */
    public Coordinate(double x, double y, double z) {
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

    public double getDistance(Coordinate other) {
        if (other == null) throw new IllegalArgumentException("the other Coordinates can not be null");
        double[] otherCoordinates = other.getCartesianCoordinates();
        return Math.sqrt(Math.pow(this.x - otherCoordinates[0], 2) +
                Math.pow(this.y - otherCoordinates[1], 2) +
                Math.pow(this.z - otherCoordinates[2], 2));
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj.getClass() == this.getClass()) {
            return this.isEqual((Coordinate) obj);
        } else {
            return false;
        }
    }

    /**
     * Compare this Coordinate to other
     *
     * @param other
     * @return true if x, y and z are both equal to each other
     */
    private boolean isEqual(Coordinate other) {
        return Arrays.equals(this.getCartesianCoordinates(), other.getCartesianCoordinates());
    }
}
