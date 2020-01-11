package org.wahlzeit.model;

import com.google.appengine.api.images.Image;
import com.googlecode.objectify.annotation.Entity;
import org.wahlzeit.services.EmailAddress;
import org.wahlzeit.services.Language;
import org.wahlzeit.tools.annotations.PatternInstance;

import java.util.Date;

@Entity
@PatternInstance(
        patternName = "Abstract Factory",
        participants = {
                "ConcreteProduct"
        }
)
public class ArtPhoto extends Photo {

    /**
     * Artist of this Art
     */
    protected Artist artist;

    protected PhotoId id = null;

    /**
     * Creation date of this Art
     */
    protected Date creationDate;

    public ArtPhoto() {
        artist = null;
    }

    public ArtPhoto(Artist artist) {
        this.artist = artist;
    }

    public ArtPhoto(PhotoId id) {
        this.id = id;
        this.artist = null;
    }

    public ArtPhoto(PhotoId id, Artist artist) {
        this.id = id;
        this.artist = artist;
    }

    @Override
    public Image getImage(PhotoSize photoSize) {
        return super.getImage(photoSize);
    }

    @Override
    public void setImage(PhotoSize photoSize, Image image) {
        super.setImage(photoSize, image);
    }

    @Override
    public String getIdAsString() {
        return super.getIdAsString();
    }

    @Override
    public PhotoId getId() {
        return this.id;
    }

    @Override
    public String getOwnerId() {
        return super.getOwnerId();
    }

    @Override
    public void setOwnerId(String newName) {
        super.setOwnerId(newName);
    }

    @Override
    public String getSummary(ModelConfig cfg) {
        return super.getSummary(cfg);
    }

    @Override
    public String getCaption(ModelConfig cfg) {
        return super.getCaption(cfg);
    }

    @Override
    public boolean getOwnerNotifyAboutPraise() {
        return super.getOwnerNotifyAboutPraise();
    }

    @Override
    public void setOwnerNotifyAboutPraise(boolean newNotifyAboutPraise) {
        super.setOwnerNotifyAboutPraise(newNotifyAboutPraise);
    }

    @Override
    public Language getOwnerLanguage() {
        return super.getOwnerLanguage();
    }

    @Override
    public void setOwnerLanguage(Language newLanguage) {
        super.setOwnerLanguage(newLanguage);
    }

    @Override
    public boolean hasSameOwner(Photo photo) {
        return super.hasSameOwner(photo);
    }

    @Override
    public EmailAddress getOwnerEmailAddress() {
        return super.getOwnerEmailAddress();
    }

    @Override
    public void setOwnerEmailAddress(EmailAddress newEmailAddress) {
        super.setOwnerEmailAddress(newEmailAddress);
    }

    @Override
    public int getWidth() {
        return super.getWidth();
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }

    @Override
    public int getThumbWidth() {
        return super.getThumbWidth();
    }

    @Override
    public boolean isWiderThanHigher() {
        return super.isWiderThanHigher();
    }

    @Override
    public int getThumbHeight() {
        return super.getThumbHeight();
    }

    @Override
    public void setWidthAndHeight(int newWidth, int newHeight) {
        super.setWidthAndHeight(newWidth, newHeight);
    }

    @Override
    public boolean hasPhotoSize(PhotoSize size) {
        return super.hasPhotoSize(size);
    }

    @Override
    public PhotoSize getMaxPhotoSize() {
        return super.getMaxPhotoSize();
    }

    @Override
    public String getPraiseAsString(ModelConfig cfg) {
        return super.getPraiseAsString(cfg);
    }

    @Override
    public double getPraise() {
        return super.getPraise();
    }

    @Override
    public void addToPraise(int value) {
        super.addToPraise(value);
    }

    @Override
    public boolean isVisible() {
        return super.isVisible();
    }

    @Override
    public PhotoStatus getStatus() {
        return super.getStatus();
    }

    @Override
    public void setStatus(PhotoStatus newStatus) {
        super.setStatus(newStatus);
    }

    @Override
    public boolean hasTag(String tag) {
        return super.hasTag(tag);
    }

    @Override
    public Tags getTags() {
        return super.getTags();
    }

    @Override
    public void setTags(Tags newTags) {
        super.setTags(newTags);
    }

    @Override
    public long getCreationTime() {
        return super.getCreationTime();
    }

    @Override
    public String getEnding() {
        return super.getEnding();
    }

    @Override
    public void setEnding(String ending) {
        super.setEnding(ending);
    }

    @Override
    public boolean hasNewPraise() {
        return super.hasNewPraise();
    }

    @Override
    public void setNoNewPraise() {
        super.setNoNewPraise();
    }

    @Override
    public void setLocation(Location location) {
        super.setLocation(location);
    }

    @Override
    public Location getLocation() {
        return super.getLocation();
    }

    /*
       Extension start
    */

    /**
     * test if class is in valid state
     *
     * @throws IllegalStateException
     */
    private void assertClassInvariants() throws IllegalStateException {
        if (this.creationDate != null && this.artist != null) {
            Date dateOfBirth = artist.getDateOfBirth();
            Date dateOfDeath = artist.getDateOfDeath();
            if (dateOfBirth.after(creationDate)) {
                throw new IllegalStateException("The creation date of the art cannot be before the associated artists birth. " +
                        "Date of creation: " + creationDate.toString() + " | Date of birth: " + dateOfBirth.toString());
            }
            if (dateOfDeath.before(creationDate)) {
                throw new IllegalStateException("The creation date of the art cannot be after the associated artists death. " +
                        "Date of creation: " + creationDate.toString() + " | Date of death: " + dateOfDeath.toString());
            }
        }
    }

    /**
     * getter for the artist associated with this art photo
     * value can be null if the artist is unknown
     *
     * @return artist of the art if known else null
     */
    public Artist getArtist() {
        return this.artist;
    }

    /**
     * setter for the artist associated with this art photo
     * value can be null if the artist is unknown
     *
     * @param artist artist of this art if known else null
     */
    public void setArtist(Artist artist) throws IllegalStateException {
        this.artist = artist;
        assertClassInvariants();
    }

    /**
     * getter for the date of creation of the art shown in the photo
     * this is not the time when this photo was uploaded
     *
     * @return date of creation of the artwork represented by this photo
     */
    public Date getCreationDate() {
        return this.creationDate;
    }

    /**
     * setter for the date of creation of the art shown in the photo
     * this is not the time when this photo was uploaded
     *
     * @param creationDate
     * @throws IllegalStateException
     */
    public void setCreationDate(Date creationDate) throws IllegalStateException {
        this.creationDate = creationDate;
        assertClassInvariants();
    }

    /*
       Extension end
    */
}
