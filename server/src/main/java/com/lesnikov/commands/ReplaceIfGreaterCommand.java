package com.lesnikov.commands;

import com.lesnikov.server.CollectionManager;
import com.lesnikov.model.Movie;

import java.util.HashMap;
import java.util.Map;

public class ReplaceIfGreaterCommand {

    private final CollectionManager collectionManager;

    public ReplaceIfGreaterCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute(Long key, Movie movie) {
        HashMap<Long, Movie> collection = this.collectionManager.getCollection();
        HashMap<Long, Movie> collectionCopy = this.collectionManager.getCollection();
        for (Map.Entry<Long, Movie> entry : collectionCopy.entrySet()) {
            if (entry.getKey().equals(key)) {
                if (entry.getValue().compareTo(movie) > 0) {
                    collection.remove(entry.getKey());
                    collection.put(entry.getKey(), entry.getValue());
                    return "Item has been replaced!\n";
                }
                return "Item has not been replaced because it's not greater than a given.\n";
            }
        }
        return "Item hasn't been replaced because entered key not found in collection.\n";
    }

}
