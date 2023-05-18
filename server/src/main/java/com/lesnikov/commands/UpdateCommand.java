package com.lesnikov.commands;

import com.lesnikov.server.CollectionManager;
import com.lesnikov.model.Movie;

import java.util.Map;

public class UpdateCommand {

    private final CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute(long id, Movie updatedMovie) {
        Map<Long, Movie> collection = this.collectionManager.getCollection();
        Map<Long, Movie> collectionCopy = this.collectionManager.getCollection();
        for (Map.Entry<Long, Movie> entry : collectionCopy.entrySet()) {
            if (entry.getValue().getId().equals(id)) {
                collection.remove(entry.getKey());
                updatedMovie.setId(id);
                collection.put(entry.getKey(), updatedMovie);
                return "Element has been updated!\n";
            }
        }
        return "Element has not been updated because there are no element with entered ID.\n";
    }

}
