package org.wahlzeit.model;

import org.wahlzeit.services.ObjectManager;

import java.lang.reflect.Constructor;
import java.util.Map;

public class ArtistManager extends ObjectManager {

    /**
     * In-Memory Map of the instances of Epochs
     */
    protected Map<String, Epoch> epochs;

    /**
     * In-Memory Map of the instances of Artists
     */
    protected Map<PhotoId, Artist> artists;

    /**
     * Check if the given epoch name is known to the system
     * @param epochName name of the epoch to check for
     */
    protected void assertIsInvalidEpochName(String epochName) {
        try {
            Class<?> clazz = Class.forName(epochName);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("the given epoch name: " + epochName + " was not valid or could not be found.");
        }
    }

    /**
     * Tries to find the epoch meant via given epochName
     * returns already created instances if epoch was created before or creates a new instance
     * @param epochName name of the epoch to create
     * @return Epoch of the given Epoch
     */
    protected Epoch getEpoch(String epochName) {
        assertIsInvalidEpochName(epochName);

        if (epochs.containsKey(epochName)) {
            return epochs.get(epochName);
        }

        try {
            Class<?> clazz = Class.forName(epochName);
            Constructor<?> constructor = clazz.getConstructors()[0];
            Epoch epoch = (Epoch) constructor.newInstance(new Object[] {});
            epochs.put(epochName, epoch);
            return epoch;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Something went wrong. See printed stack trace for more details.");
        }
    }

    /**
     * Create an Instance of an Artist via given Epoch by name
     * @param epochName epoch by name e.g. Romanticism
     * @return dummy Artist connected with the given epoch
     * @throws IllegalArgumentException if the given epoch name is invalid
     */
    public Artist createArtist(String epochName) {
        Epoch epoch = getEpoch(epochName);
        Artist artist = epoch.createInstance();
        artists.put(artist.getId(), artist);
        return artist;
    }
}
