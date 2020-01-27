package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.tools.annotations.PatternInstance;

import java.util.logging.Logger;

@PatternInstance(
        patternName = "Abstract Factory",
        participants = {
                "ConcreteFactory"
        }
)
public class ArtPhotoFactory extends PhotoFactory {

    private static final Logger log = Logger.getLogger(ArtPhotoFactory.class.getName());
    private static ArtPhotoFactory instance = null;

    protected ArtistManager artistManager = new ArtistManager();

    protected ArtPhotoFactory() { }

    /**
     * Public singleton access method.
     */
    public static synchronized ArtPhotoFactory getInstance() {
        if (instance == null) {
            log.config(LogBuilder.createSystemMessage().addAction("setting ArtPhotoFactory").toString());
            setInstance(new ArtPhotoFactory());
        }

        return instance;
    }

    /**
     * Method to set the singleton instance of ArtPhotoFactory.
     */
    protected static synchronized void setInstance(ArtPhotoFactory artPhotoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initalize ArtPhotoFactory twice");
        }

        instance = artPhotoFactory;
    }

    @Override
    public Photo createPhoto() {
        Artist artist = artistManager.createArtist("EpochLess");
        return new ArtPhoto(artist);
    }

    @Override
    public Photo createPhoto(PhotoId id) {
        Artist artist = artistManager.createArtist("EpochLess");
        return new ArtPhoto(id, artist);
    }

    @Override
    public Photo loadPhoto(PhotoId id) {
        return null;
    }

    @Override
    public PhotoFilter createPhotoFilter() {
        return new PhotoFilter();
    }

    @Override
    public PhotoTagCollector createPhotoTagCollector() {
        return new PhotoTagCollector();
    }
}
