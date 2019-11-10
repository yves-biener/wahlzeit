package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

public class ArtPhotoFactory extends PhotoFactory {

    private static final Logger log = Logger.getLogger(PhotoFactory.class.getName());
    private static ArtPhotoFactory instance = null;

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
        return new ArtPhoto();
    }

    @Override
    public Photo createPhoto(PhotoId id) {
        return new ArtPhoto(id);
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
