package org.wahlzeit.model;

public class Location {
    /**
     * Constructor of this class
     *
     * @param name
     * @param coordinate
     */
    public Location(String name, ICoordinate coordinate) {
        this.name = name;
        if (coordinate == null) throw new IllegalArgumentException("Coordiante can not be null");
        this.coordinate = coordinate;
    }

    /**
     * name of the location
     */
    protected String name;

    /**
     * Coordinate associated with this Location (aggregation)
     */
    protected ICoordinate coordinate;

    /**
     * getter of this Location's name
     */
    public String getName() {
        return name;
    }

    /**
     * setter of this Location's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter of this Location's Coordinate
     */
    public ICoordinate getCoordinate() {
        return this.coordinate;
    }

    /**
     * setter of this Location's Coordinate
     */
    public void setCoordinate(ICoordinate coordinate) {
        if (coordinate == null) throw new IllegalArgumentException("Coordiante can not be null");
        this.coordinate = coordinate;
    }
}
