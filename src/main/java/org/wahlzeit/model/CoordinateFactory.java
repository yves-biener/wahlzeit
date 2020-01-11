package org.wahlzeit.model;

import org.wahlzeit.tools.annotations.PatternInstance;

import java.util.HashMap;
import java.util.Map;

@PatternInstance(
        patternName = "Abstract Factory",
        participants = {
                "ConcreteFactory"
        }
)
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
     * map for storing the shared instances of all used CartesianCoordinate instances
     */
    private Map<String, CartesianCoordinate> sharedCartesianCoordinates;

    /**
     * map for storing the shared instances of all used SphericalCoordinate instances
     */
    private Map<String, SphericalCoordinate> sharedSphericalCoordinates;

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
        return doGetCartesianCoordinate(x, y, z);
    }

    /**
     * Creates an unique String which used as a Key for the Maps
     * So it's unique for each instance of a Coordinate object
     * @param x or r
     * @param y or q
     * @param z or p
     * @return String with created Key
     */
    private String createKeyForCoordinate(double x, double y, double z) {
        return String.format("%f_%f_%f", x, y, z);
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
        return doGetSphericalCoordinate(r, q, p);
    }

    /**
     * Implementation of the look-up for the shared Cartesian Coordinate instances
     *
     * @param x, y, z used coordinates
     * @return ether instance which was already used or new one which will be stored internally for sharing
     */
    private CartesianCoordinate doGetCartesianCoordinate(double x, double y, double z) {
        CartesianCoordinate result = sharedCartesianCoordinates.get(createKeyForCoordinate(x, y, z));
        if (result == null) {
            synchronized (this) {
                result = sharedCartesianCoordinates.get(createKeyForCoordinate(x, y, z));
                if (result == null) {
                    int mapSize = sharedCartesianCoordinates.size();
                    result = new CartesianCoordinate(x, y, z);
                    sharedCartesianCoordinates.put(createKeyForCoordinate(x, y, z), result);
                    assert mapSize + 1 == sharedCartesianCoordinates.size() : "adding a new element to the shared map failed.";
                }
            }
        }
        return result;
    }

    /**
     * Implementation of the look-up for the shared Spherical Coordinate instances
     *
     * @param r, q, p used coordinates
     * @return ether instance which was already used or new one which will be stored internally for sharing
     */
    private SphericalCoordinate doGetSphericalCoordinate(double r, double q, double p) {
        SphericalCoordinate result = sharedSphericalCoordinates.get(createKeyForCoordinate(r, q, p));
        if (result == null) {
            synchronized (this) {
                result = sharedSphericalCoordinates.get(createKeyForCoordinate(r, q, p));
                if (result == null) {
                    int mapSize = sharedSphericalCoordinates.size();
                    result = new SphericalCoordinate(r, q, p);
                    sharedSphericalCoordinates.put(createKeyForCoordinate(r, q, p), result);
                    assert mapSize + 1 == sharedSphericalCoordinates.size() : "adding a new element to the shared map failed.";
                }
            }
        }
        return result;
    }
}
