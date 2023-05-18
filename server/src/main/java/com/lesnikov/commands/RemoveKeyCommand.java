package com.lesnikov.commands;

import com.lesnikov.server.CollectionManager;
import com.lesnikov.model.Movie;
import com.lesnikov.utils.IDGenerator;

import java.util.Map;

public class RemoveKeyCommand {

    private final CollectionManager collectionManager;

    public RemoveKeyCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute(Long key) {
        Map<Long, Movie> collection = this.collectionManager.getCollection();
        Map<Long, Movie> collectionCopy = this.collectionManager.getCollection();
        for (Map.Entry<Long, Movie> entry : collectionCopy.entrySet()) {
            if (entry.getKey().equals(key)) {
                collection.remove(entry.getKey());
                IDGenerator.removeId(entry.getValue().getId());
                return "Item has been removed successfully.\n";
            }
        }
        return "Item has not been deleted because entered key wasn't found in collection.\n";
    }

}
