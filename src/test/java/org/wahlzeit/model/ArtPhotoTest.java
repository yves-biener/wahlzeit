package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class ArtPhotoTest {

    private ArtPhoto artPhoto;
    Date dateOfBirth = new Date(1800, Calendar.JANUARY, 8);
    Date dateOfDeath = new Date(1890, Calendar.SEPTEMBER, 14);

    @Before
    public void initArtPhoto() {
        Artist testArtist = new Artist();
        testArtist.setDateOfBirth(dateOfBirth);
        testArtist.setDateOfDeath(dateOfDeath);
        artPhoto = new ArtPhoto(testArtist);
    }

    @Test
    public void testArtPhotoSetCreationTime() {
        Date testDate = new Date(1840, Calendar.JANUARY, 1);
        Date shortlyAfterBirth = new Date(1800, Calendar.JANUARY, 9);
        artPhoto.setCreationDate(testDate);
        assertEquals(artPhoto.getCreationDate(), testDate);
        artPhoto.setCreationDate(shortlyAfterBirth);
        assertEquals(artPhoto.getCreationDate(), shortlyAfterBirth);
        Date shortlyBeforeDeath = new Date(1890, Calendar.SEPTEMBER, 13);
        artPhoto.setCreationDate(shortlyBeforeDeath);
        assertEquals(artPhoto.getCreationDate(), shortlyBeforeDeath);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArtPhotoSetCreationTimeOnBirth() {
        artPhoto.setCreationDate(dateOfBirth);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArtPhotoSetCreationTimeOnDeath() {
        artPhoto.setCreationDate(dateOfDeath);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArtPhotoSetCreationTimeBeforeBirth() {
        Date beforeBirth = new Date(1700, Calendar.SEPTEMBER, 3);
        artPhoto.setCreationDate(beforeBirth);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArtPhotoSetCreationTimeAfterDeath() {
        Date afterDeath = new Date(2000, Calendar.JANUARY, 12);
        artPhoto.setCreationDate(afterDeath);
    }
}
