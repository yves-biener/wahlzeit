package org.wahlzeit.model;

public abstract class AbstractCoordinate implements ICoordinate {

    private void assertCoordinateNotNull(ICoordinate coordinate) {
        if (coordinate == null) throw new IllegalArgumentException("coordinate can not be null");
    }

    private double doGetCentralAngle(ICoordinate coordinate) {
        assertCoordinateNotNull(coordinate);
        double chord = this.asCartesianCoordinate().getCartesianDistance(coordinate);
        return 2 * Math.asin(chord / 2);
    }

    private double doGetCartesianDistance(ICoordinate coordinate) {
        assertCoordinateNotNull(coordinate);
        CartesianCoordinate other = coordinate.asCartesianCoordinate();
        CartesianCoordinate me = this.asCartesianCoordinate();
        return Math.sqrt(Math.pow(me.getX() - other.getX(), 2) +
                Math.pow(me.getY() - other.getY(), 2) +
                Math.pow(me.getZ() - other.getZ(), 2));
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return null;
    }

    @Override
    public double getCartesianDistance(ICoordinate coordinate) {
        return doGetCartesianDistance(coordinate);
    }

    @Override
    public SphericalCoordinate asSphericalCoordinate() {
        return null;
    }

    @Override
    public double getCentralAngle(ICoordinate coordinate) {
        return doGetCentralAngle(coordinate);
    }

    @Override
    public boolean isEqual(ICoordinate coordinate) {
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
