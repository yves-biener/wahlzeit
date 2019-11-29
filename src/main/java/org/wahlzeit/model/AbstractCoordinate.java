package org.wahlzeit.model;

public abstract class AbstractCoordinate implements ICoordinate {
    /**
     * implementation for getCentralAngle
     *
     * @param coordinate coordinate to calculate angle to
     * @return central angle between this and given coordinate
     */
    private double doGetCentralAngle(ICoordinate coordinate) {
        double chord = this.asCartesianCoordinate().getCartesianDistance(coordinate);
        return 2 * Math.asin(chord / 2);
    }

    /**
     * implementation for getCartesianDistance
     *
     * @param coordinate coordinate to calculate distance to
     * @return distance between this and given coordinate
     */
    private double doGetCartesianDistance(ICoordinate coordinate) {
        CartesianCoordinate other = coordinate.asCartesianCoordinate();
        CartesianCoordinate me = this.asCartesianCoordinate();
        return Math.sqrt(Math.pow(me.getX() - other.getX(), 2) +
                Math.pow(me.getY() - other.getY(), 2) +
                Math.pow(me.getZ() - other.getZ(), 2));
    }

    /**
     * Epsilon used for double-comparisons
     */
    protected final double EPSILON = 1e-15;

    /*  Preconditions  */

    /**
     * assertion for an argument to not be null
     *
     * @param argument argument to check
     * @throws IllegalArgumentException
     */
    protected void assertNotNullArgument(Object argument) throws IllegalArgumentException {
        if (argument == null) throw new IllegalArgumentException("argument can not be null");
    }

    /*  Class invariants  */

    /**
     * assertion for an implementing class to check the state of the class
     * will throw IllegalStateException if state of class is not valid
     */
    protected abstract void assertClassInvariants();

    @Override
    public CartesianCoordinate asCartesianCoordinate() throws IllegalStateException {
        return null;
    }

    @Override
    public double getCartesianDistance(ICoordinate coordinate) throws IllegalArgumentException {
        assertNotNullArgument(coordinate);
        return doGetCartesianDistance(coordinate);
    }

    @Override
    public SphericalCoordinate asSphericalCoordinate() throws IllegalStateException {
        return null;
    }

    @Override
    public double getCentralAngle(ICoordinate coordinate) throws IllegalArgumentException {
        assertNotNullArgument(coordinate);
        return doGetCentralAngle(coordinate);
    }

    @Override
    public boolean isEqual(ICoordinate coordinate) throws IllegalArgumentException, IllegalStateException {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (obj instanceof AbstractCoordinate) {
            return this.isEqual((ICoordinate) obj);
        } else {
            return false;
        }
    }
}
