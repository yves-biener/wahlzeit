package org.wahlzeit.model;

import java.util.Date;

public class Artist {

    /**
     * Name of the artist
     */
    private String name;

    /**
     * Date of birth if known else null
     * If this value is null dateOfDeath has to be null as well
     */
    private Date dateOfBirth;

    /**
     * Date of death if known else null
     * If this value is null dateOfBirth has to be null as well
     */
    private Date dateOfDeath;

    //TODO: add Photo (id) of artist him-/herself

    /**
     * assertion for an argument to not be null
     *
     * @param argument argument to check
     * @throws IllegalArgumentException
     */
    private void assertNotNullArgument(Object argument) throws IllegalArgumentException {
        if (argument == null) throw new IllegalArgumentException("argument can not be null");
    }

    /**
     * test if instance is in valid state
     *
     * @throws IllegalStateException
     */
    private void assertClassInvariants() throws IllegalStateException {
        if (name == null) {
            throw new IllegalStateException("An artist without name is now allowed.");
        }

        if (dateOfDeath == null && dateOfBirth == null) {
            return;
        }

        if (dateOfDeath == null || dateOfBirth == null) {
            throw new IllegalStateException("Either live date of an artist is known (!= null) or unknown (== null). " +
                    "(date of birth and date of death both either have to be null or to a valid date combination");
        }
        if (dateOfBirth.after(dateOfDeath))
            throw new IllegalStateException("Date of birth cannot be after date of death. " +
                    "Date of birth: " + dateOfBirth.toString() + " | Date of death: " + dateOfDeath.toString());
    }

    /**
     * Constructor
     *
     * @param name name of the artist
     */
    public Artist(String name) {
        this.name = name;

        assertClassInvariants();
    }

    /**
     * Constructor
     *
     * @param name name of the artist
     * @param dateOfBirth date of birth of the artist
     * @param dateOfDeath date of death of the artist
     * @throws IllegalArgumentException
     */
    public Artist(String name, Date dateOfBirth, Date dateOfDeath) throws IllegalArgumentException {
        this.name = name;
        doSetLiveData(dateOfBirth, dateOfDeath);
        assertClassInvariants();
    }

    /**
     * getter for the artist name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * setter for the artist name
     *
     * @param name new name of the artist
     * @throws IllegalArgumentException
     */
    public void setName(String name) throws IllegalArgumentException {
        assertNotNullArgument(name);
        this.name = name;
    }

    /**
     * getter for the date of birth of the artist
     * this value can be null if the date is unknown
     *
     * @return date of birth if known else null
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * getter for the date of death of the artist
     * this value can be null if the date is unknown
     *
     * @return date of death if known else null
     */
    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    private void doSetLiveData(Date dateOfBirth, Date dateOfDeath) {
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
    }

    /**
     * setter for live data of the artist (date of birth and death)
     *
     * @param dateOfBirth date of birth of the artist
     * @param dateOfDeath date of death of the artist
     * @throws IllegalStateException
     */
    public void setLiveData(Date dateOfBirth, Date dateOfDeath) throws IllegalStateException {
        doSetLiveData(dateOfBirth, dateOfDeath);
        assertClassInvariants();
    }
}
