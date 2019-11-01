package org.wahlzeit.model;

public class Location {
    /**
     * Constructor of this class
     *
     * @param name
     * @param coordinate
     */
    public Location(String name, Coordinate coordinate) {
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
    protected Coordinate coordinate;

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
    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    /**
     * setter of this Location's Coordinate
     */
    public void setCoordinate(Coordinate coordinate) {
        if (coordinate == null) throw new IllegalArgumentException("Coordiante can not be null");
        this.coordinate = coordinate;
    }
}
