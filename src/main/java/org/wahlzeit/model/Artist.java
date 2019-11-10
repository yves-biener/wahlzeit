package org.wahlzeit.model;

import java.util.Date;

public class Artist {

    private String name;
    private Date dateOfBirth;
    private Date dateOfDeath;

    // TODO add Photo of artist him-/herself
    //private Photo photo;

    public Artist() {
    }

    public Artist(String name) {
        this.name = name;
    }

    public Artist(String name, Date dateOfBirth, Date dateOfDeath) {
        this.name = name;
        if (dateOfBirth != null && dateOfDeath != null) {
            if (dateOfBirth.after(dateOfDeath)) {
                throw new IllegalArgumentException("Date of death cannot be before date of birth");
            }
        }
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        if (dateOfBirth == null) {
            this.dateOfBirth = dateOfBirth;
            return;
        }
        if (this.dateOfDeath == null) {
            this.dateOfBirth = dateOfBirth;
            return;
        }
        if (this.dateOfDeath.before(dateOfBirth)) {
            throw new IllegalArgumentException("The date of birth cannot be after the date of birth");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        if (dateOfDeath == null) {
            this.dateOfDeath = dateOfDeath;
            return;
        }
        if (this.dateOfBirth == null) {
            this.dateOfDeath = dateOfDeath;
            return;
        }

        if (this.dateOfBirth.after(dateOfDeath)) {
            throw new IllegalArgumentException("The date of death cannot be before the date of death");
        }
        this.dateOfDeath = dateOfDeath;
    }
}
