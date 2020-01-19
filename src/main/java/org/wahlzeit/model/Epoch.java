package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Epoch extends DataObject {

    /**
     * Epoch hierarchically over this epoch
     */
    protected Epoch superEpoch;

    /**
     * Epochs hierarchically under this epoch
     */
    protected Set<Epoch> subEpochs;

    /**
     * Constructor
     */
    public Epoch() {
        this.superEpoch = null;
        this.subEpochs = new HashSet<>();
    }

    /**
     * Getter for the epoch hierarchically over this epoch
     * @return null if no epoch is over this one otherwise the corresponding epoch
     */
    public Epoch getSuperEpoch() {
        return superEpoch;
    }

    /**
     * Getter for an iterator for iterating over all epochs hierarchically under this epoch
     * @return iterator for sub epochs
     */
    public Iterator<Epoch> getSubEpochIterator() {
        return subEpochs.iterator();
    }

    /**
     * Add an Epoch as an epoch hierarchically under this one
     * This function also sets for the sub epoch the super epoch to this epoch
     * @param epoch Epoch to set as sub epoch of called epoch
     */
    public void addSubEpoch(Epoch epoch) {
        assert (epoch != null) : "tried to set null sub-epoch in ArtistEpoch::addSubEpoch";
        epoch.setSuperEpoch(this);
        subEpochs.add(epoch);
    }

    /**
     * Setter for the epoch hierarchically over this epoch
     * @param epoch Epoch to set hierarchically over this epoch
     */
    protected void setSuperEpoch(Epoch epoch) {
        assert (epoch != null) : "tried to set null super-epoch in ArtistEpoch::setSuperEpoch";
        superEpoch = epoch;
    }

    /**
     * Check if given Epoch is sub epoch of this Epoch
     * @param epoch Epoch to look for
     * @return boolean if given Epoch is sub epoch
     */
    public boolean isSubEpoch(Epoch epoch) {
        if (epoch == this) {
            return true;
        } else {
            if (epoch.getSuperEpoch() == null) {
                return false;
            }
            return isSubEpoch(epoch.getSuperEpoch());
        }
    }

    /**
     * Create a new dummy Artist instance
     * @return dummy Artist instance
     */
    public Artist createInstance() {
        return new Artist(this);
    }
}
