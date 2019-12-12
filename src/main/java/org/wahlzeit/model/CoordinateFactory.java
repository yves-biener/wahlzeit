package org.wahlzeit.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoordinateFactory {
    /**
     * Singleton Pattern for this factory
     */
    private static CoordinateFactory instance;

    CoordinateFactory() {
        sharedSphericalCoordinates = new HashMap<>();
        sharedCartesianCoordinates = new HashMap<>();
    }

    public synchronized static CoordinateFactory getInstance() {
        if (instance == null) {
            instance = new CoordinateFactory();
        }
        return instance;
    }

    /**
     * check if the given list has the exact size of 3
     *
     * @param list list to check on
     */
    private void assertListSizeIs3(List list) {
        if (list.size() != 3)
            throw new IllegalStateException("Lists used for sharing the instances have to be of size 3. " +
                    "But current size was: " + list.size());
    }

    /**
     * map for storing the shared instances of all used CartesianCoordinate instances
     */
    private Map<List<Double>, CartesianCoordinate> sharedCartesianCoordinates;

    /**
     * map for storing the shared instances of all used SphericalCoordinate instances
     */
    private Map<List<Double>, SphericalCoordinate> sharedSphericalCoordinates;

    /**
     * Getter for CartesianCoordinates
     *
     * @param x x coordinate of the coordinate to get in Cartesian-Type
     * @param y y coordinate of the coordinate to get in Cartesian-Type
     * @param z z coordinate of the coordinate to get in Cartesian-Type
     * @return instance of the asked coordinate
     * @throws IllegalStateException
     */
    public CartesianCoordinate getAsCartesianCoordinate(double x, double y, double z) throws IllegalStateException {
        List<Double> coordinates = new ArrayList<Double>(3);
        coordinates.add(x);
        coordinates.add(y);
        coordinates.add(z);
        assertListSizeIs3(coordinates);

        return doGetCartesianCoordinate(coordinates);
    }

    /**
     * Getter for SphericalCoordinates
     *
     * @param r radius for coordinate to get in Spherical-Type
     * @param q q angle for the coordinate to get in Spherical-Type
     * @param p p angle for the coordinate to get in Spherical-Type
     * @return instance of the asked coordinate
     * @throws IllegalStateException
     */
    public SphericalCoordinate getAsSphericalCoordinate(double r, double q, double p) throws IllegalStateException {
        List<Double> coordinates = new ArrayList<Double>(3);
        coordinates.add(r);
        coordinates.add(q);
        coordinates.add(p);
        assertListSizeIs3(coordinates);

        return doGetSphericalCoordinate(coordinates);
    }

    /**
     * Implementation of the look-up for the shared Cartesian Coordinate instances
     *
     * @param coordinates key for the map used for the look-up
     * @return ether instance which was already used or new one which will be stored internally for sharing
     */
    private CartesianCoordinate doGetCartesianCoordinate(List<Double> coordinates) {
        CartesianCoordinate result = sharedCartesianCoordinates.get(coordinates);
        if (result == null) {
            synchronized (this) {
                result = sharedCartesianCoordinates.get(coordinates);
                if (result == null) {
                    int mapSize = sharedCartesianCoordinates.size();
                    result = new CartesianCoordinate(coordinates.get(0), coordinates.get(1), coordinates.get(2));
                    sharedCartesianCoordinates.put(coordinates, result);
                    assert mapSize + 1 == sharedCartesianCoordinates.size() : "adding a new element to the shared map failed.";
                }
            }
        }
        return result;
    }

    /**
     * Implementation of the look-up for the shared Spherical Coordinate instances
     *
     * @param coordinates key for the map used for the look-up
     * @return ether instance which was already used or new one which will be stored internally for sharing
     */
    private SphericalCoordinate doGetSphericalCoordinate(List<Double> coordinates) {
        SphericalCoordinate result = sharedSphericalCoordinates.get(coordinates);
        if (result == null) {
            synchronized (this) {
                result = sharedSphericalCoordinates.get(coordinates);
                if (result == null) {
                    int mapSize = sharedSphericalCoordinates.size();
                    result = new SphericalCoordinate(coordinates.get(0), coordinates.get(1), coordinates.get(2));
                    sharedSphericalCoordinates.put(coordinates, result);
                    assert mapSize + 1 == sharedSphericalCoordinates.size() : "adding a new element to the shared map failed.";
                }
            }
        }
        return result;
    }
}
