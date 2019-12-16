package ru.job4j.nonblock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * the class of model collection.
 */
public class Cash {

    /**
     * the map where models are contained.
     */
    private final ConcurrentMap<Integer, Base> map;

    /**
     * the main constructor.
     */
    public Cash() {
        this.map = new ConcurrentHashMap<>();
    }

    /**
     * adds the model to the map.
     *
     * @param model the model for adding.
     * @return the model.
     */
    public Base add(Base model) {
        return this.map.putIfAbsent(model.getId(), model);
    }

    /**
     * updates model in the map. If modification version is not match to mapping model
     * the method throws OptimisticException.
     *
     * @param model - the modified model.
     * @return the model.
     * @throws OptimisticException
     */
    public Base update(Base model) throws OptimisticException {
        return this.map.computeIfPresent(model.getId(), (k, v) -> {
            if (v.getVersion() != model.getVersion() - 1) {
                throw new OptimisticException("The model was modified by other thread.");
            }
            return model;
        });
    }

    /**
     * deletes the model from the map.
     *
     * @param model - the model for deleting.
     * @return true if the model were deleted, false if not.
     */
    public boolean delete(Base model) {
        return this.map.remove(model.getId(), model);
    }
}
