package com.lesnikov.commands;

import com.lesnikov.server.CollectionManager;
import com.lesnikov.model.Movie;

import java.util.*;

public class ShowCommand {

    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        Map<Long, Movie> collectionCopy = new LinkedHashMap<>(collectionManager.getCollection());
        stringBuilder.append("Collection content:").append("\n");
        for (Map.Entry<Long, Movie> entry : collectionCopy.entrySet()) {
            stringBuilder.append(entry.getKey()).append(" : ").append(entry.getValue().toString()).append("\n");
        }
        return stringBuilder.toString();
    }

}