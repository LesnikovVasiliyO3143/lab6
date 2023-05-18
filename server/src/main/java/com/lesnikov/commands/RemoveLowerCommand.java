package com.lesnikov.commands;

import com.lesnikov.server.CollectionManager;
import com.lesnikov.model.Movie;
import com.lesnikov.utils.IDGenerator;

import java.util.HashMap;
import java.util.Map;

public class RemoveLowerCommand {

    private final CollectionManager collectionManager;

    public RemoveLowerCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute(Movie movie) {
        HashMap<Long, Movie> collection = this.collectionManager.getCollection();
        HashMap<Long, Movie> newCollection = new HashMap<>();
        for (Map.Entry<Long, Movie> current : collection.entrySet()) {
            if (current.getValue().compareTo(movie) < 0) {
                IDGenerator.removeId(current.getValue().getId());
            } else {
                newCollection.put(current.getKey(), current.getValue());
            }
        }
        this.collectionManager.setCollection(newCollection);
        return "All elements which are lower then given were removed\n";
    }

}