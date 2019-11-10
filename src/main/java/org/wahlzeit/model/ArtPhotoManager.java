package org.wahlzeit.model;

import com.google.appengine.api.images.Image;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;
import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.services.Persistent;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class ArtPhotoManager extends PhotoManager {

    protected static final ArtPhotoManager instance = new ArtPhotoManager();

    private static final Logger log = Logger.getLogger(ArtPhotoManager.class.getName());

    /**
     * In-memory cache for artPhotos
     */
    protected Map<PhotoId, Photo> artPhotoMap = new HashMap<PhotoId, Photo>();

    public ArtPhotoManager() {
        super.photoTagCollector = ArtPhotoFactory.getInstance().createPhotoTagCollector();
    }

    @Override
    public Photo getPhotoFromId(PhotoId id) {
        if (id == null) {
            return null;
        }

        ArtPhoto result = (ArtPhoto) doGetPhotoFromId(id);

        if (result == null) {
            result = (ArtPhoto) ArtPhotoFactory.getInstance().loadPhoto(id);
            if (result != null) {
                doAddPhoto(result);
            }
        }

        return result;
    }

    @Override
    protected Photo doGetPhotoFromId(PhotoId id) {
        return artPhotoMap.get(id);
    }

    @Override
    protected void doAddPhoto(Photo myPhoto) {
        artPhotoMap.put(myPhoto.getId(), myPhoto);
    }

    @Override
    public void init() {
        loadPhotos();
    }

    @Override
    public void loadPhotos() {
        Collection<Photo> existingPhotos = ObjectifyService.run(new Work<Collection<Photo>>() {
            @Override
            public Collection<Photo> run() {
                Collection<Photo> existingPhotos = new ArrayList<Photo>();
                readObjects(existingPhotos, Photo.class);
                return existingPhotos;
            }
        });

        for (Photo photo : existingPhotos) {
            if (!doHasPhoto(photo.getId())) {
                log.config(LogBuilder.createSystemMessage().
                        addParameter("Load Photo with ID", photo.getIdAsString()).toString());
                loadScaledImages(photo);
                doAddPhoto(photo);
            } else {
                log.config(LogBuilder.createSystemMessage().
                        addParameter("Already loaded Photo", photo.getIdAsString()).toString());
            }
        }

        log.info(LogBuilder.createSystemMessage().addMessage("All photos loaded.").toString());
    }

    @Override
    protected boolean doHasPhoto(PhotoId id) {
        return artPhotoMap.containsKey(id);
    }

    @Override
    protected void loadScaledImages(Photo photo) {
        super.loadScaledImages(photo);
    }

    @Override
    public void savePhoto(Photo photo) {
        updateObject(photo);
    }

    @Override
    protected void updateDependents(Persistent obj) {
        super.updateDependents(obj);
    }

    @Override
    public List<Tag> addTagsThatMatchCondition(List<Tag> tags, String condition) {
        return super.addTagsThatMatchCondition(tags, condition);
    }

    @Override
    protected void saveScaledImages(Photo photo) {
        super.saveScaledImages(photo);
    }

    @Override
    protected void updateTags(Photo photo) {
        super.updateTags(photo);
    }

    @Override
    public void savePhotos() throws IOException {
        updateObjects(artPhotoMap.values());
    }

    @Override
    public Map<PhotoId, Photo> getPhotoCache() {
        return artPhotoMap;
    }

    @Override
    public Set<Photo> findPhotosByOwner(String ownerName) {
        return super.findPhotosByOwner(ownerName);
    }

    @Override
    public Photo getVisiblePhoto(PhotoFilter filter) {
        filter.generateDisplayablePhotoIds();
        return getPhotoFromId(filter.getRandomDisplayablePhotoId());
    }

    @Override
    public Photo createPhoto(String filename, Image uploadedImage) throws Exception {
        PhotoId id = PhotoId.getNextId();
        ArtPhoto artPhoto = (ArtPhoto) PhotoUtil.createPhoto(filename, id, uploadedImage);
        addPhoto(artPhoto);
        return artPhoto;
    }

    @Override
    public void addPhoto(Photo photo) throws IOException {
        PhotoId id = photo.getId();
        assertIsNewPhoto(id);
        doAddPhoto(photo);

        GlobalsManager.getInstance().saveGlobals();
    }

    @Override
    protected void assertIsNewPhoto(PhotoId id) {
        if (hasPhoto(id)) {
            throw new IllegalStateException("ArtPhoto already exists!");
        }
    }
}
