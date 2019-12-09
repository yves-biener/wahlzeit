package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class ArtPhotoTest {

    private ArtPhoto artPhoto;
    private Date dateOfBirth = new Date(1800, Calendar.JANUARY, 8);
    private Date dateOfDeath = new Date(1890, Calendar.SEPTEMBER, 14);

    @Before
    public void initArtPhoto() {
        Artist testArtist = new Artist("TestArtist", dateOfBirth, dateOfDeath);
        artPhoto = new ArtPhoto(testArtist);
    }

    @Test
    public void testArtPhotoSetCreationTime() {
        //Arrange
        Date testDate = new Date(1840, Calendar.JANUARY, 1);

        //Act
        artPhoto.setCreationDate(testDate);

        //Assert
        assertEquals(testDate, artPhoto.getCreationDate());
    }

    @Test
    public void testArtPhotoSetCreationTimeShortlyAfterBirth() {
        //Arrange
        Date shortlyAfterBirth = new Date(1800, Calendar.JANUARY, 9);

        //Act
        artPhoto.setCreationDate(shortlyAfterBirth);

        //Assert
        assertEquals(shortlyAfterBirth, artPhoto.getCreationDate());
    }

    @Test
    public void testArtPhotoSetCreationTimeShortlyBeforeDeath() {
        //Arrange
        Date shortlyBeforeDeath = new Date(1890, Calendar.SEPTEMBER, 13);

        //Act
        artPhoto.setCreationDate(shortlyBeforeDeath);

        //Assert
        assertEquals(shortlyBeforeDeath, artPhoto.getCreationDate());
    }

    @Test(expected = IllegalStateException.class)
    public void testArtPhotoSetCreationTimeBeforeBirth() {
        //Arrange
        Date beforeBirth = new Date(1700, Calendar.SEPTEMBER, 3);

        //Act
        artPhoto.setCreationDate(beforeBirth);

        //Assert
    }

    @Test(expected = IllegalStateException.class)
    public void testArtPhotoSetCreationTimeAfterDeath() {
        //Arrange
        Date afterDeath = new Date(2000, Calendar.JANUARY, 12);

        //Act
        artPhoto.setCreationDate(afterDeath);

        //Assert
    }
}
