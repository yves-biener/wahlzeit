package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class ArtistTest {

    Artist artist;
    Date dateOfBirth = new Date(1800, Calendar.JANUARY, 8);
    Date dateOfDeath = new Date(1890, Calendar.SEPTEMBER, 14);

    @Before
    public void initArtistTest() {
        artist = new Artist("Test", dateOfBirth, dateOfDeath);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArtistConstructor() {
        artist = new Artist("Test", dateOfDeath, dateOfBirth);
    }

    @Test
    public void testSetDateOfBirthValid() {
        Date newDateOfBirth = new Date(1800, Calendar.JANUARY, 15);
        artist.setDateOfBirth(newDateOfBirth);
        assertEquals(artist.getDateOfBirth(), newDateOfBirth);
        artist.setDateOfBirth(null);
        assertNull(artist.getDateOfBirth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfBirthInvalid() {
        Date newDateOfBirth = new Date(1890, Calendar.DECEMBER, 8);
        artist.setDateOfBirth(newDateOfBirth);
    }

    @Test
    public void testSetDateOfDeathValid() {
        Date newDateOfDeath = new Date(1889, Calendar.JULY, 12);
        artist.setDateOfDeath(newDateOfDeath);
        assertEquals(artist.getDateOfDeath(), newDateOfDeath);
        artist.setDateOfDeath(null);
        assertNull(artist.getDateOfDeath());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfDeathInvalid() {
        Date newDateOfDeath = new Date(1780, Calendar.MAY, 24);
        artist.setDateOfDeath(newDateOfDeath);
    }


}
