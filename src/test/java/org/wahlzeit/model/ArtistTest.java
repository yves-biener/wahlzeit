package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class ArtistTest {

    private Artist artist;
    private Epoch epoch;

    @Before
    public void initArtistTest() {
        artist = new Artist(epoch, "Test");
    }

    @Test(expected = IllegalStateException.class)
    public void testArtistConstructor() {
        //Arrange
        Date dateOfBirth = new Date(1800, Calendar.JANUARY, 8);
        Date dateOfDeath = new Date(1890, Calendar.SEPTEMBER, 14);

        //Act
        artist = new Artist(epoch ,"Test", dateOfDeath, dateOfBirth);

        //Assert
    }

    @Test
    public void testSetLiveDataKnownDates() {
        //Arrange
        Date dateOfBirth = new Date(1800, Calendar.JANUARY, 15);
        Date dateOfDeath = new Date(1889, Calendar.JULY, 12);

        //Act
        artist.setLiveData(dateOfBirth, dateOfDeath);

        //Assert
        assertEquals(artist.getDateOfDeath(), dateOfDeath);
        assertEquals(artist.getDateOfBirth(), dateOfBirth);
    }

    @Test(expected = IllegalStateException.class)
    public void testSetLiveDataInvalidState() {
        //Arrange
        Date dateOfBirth = new Date(1800, Calendar.JANUARY, 15);
        Date dateOfDeath = new Date(1889, Calendar.JULY, 12);

        //Act
        artist.setLiveData(dateOfDeath, dateOfBirth);

        //Assert
        assertEquals(artist.getDateOfDeath(), dateOfBirth);
        assertEquals(artist.getDateOfBirth(), dateOfDeath);
    }

    @Test
    public void testSetLiveDataWithNull() {
        //Arrange

        //Act

        //Assert
        assertNull(artist.getDateOfBirth());
        assertNull(artist.getDateOfDeath());
    }
}
