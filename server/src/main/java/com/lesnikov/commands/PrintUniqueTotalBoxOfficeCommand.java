package com.lesnikov.commands;

import com.lesnikov.server.CollectionManager;
import com.lesnikov.model.Movie;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class PrintUniqueTotalBoxOfficeCommand {

    private CollectionManager collectionManager;

    public PrintUniqueTotalBoxOfficeCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute() {
        Map<Long, Movie> collection = this.collectionManager.getCollection();
        Set<Double> uniqueTotalBoxes = new LinkedHashSet<>();
        for (Map.Entry<Long, Movie> entry : collection.entrySet()) {
            uniqueTotalBoxes.add(entry.getValue().getTotalBoxOffice());
        }
        return uniqueTotalBoxes.toString();
    }

}
