package com.lesnikov.commands;

import com.lesnikov.server.CollectionManager;
import com.lesnikov.model.Movie;
import com.lesnikov.model.Person;

import java.util.*;

public class PrintFieldDescendingOperatorCommand {

    private final CollectionManager collectionManager;

    public PrintFieldDescendingOperatorCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute() {
        Map<Long, Movie> collection = this.collectionManager.getCollection();
        TreeSet<Person> persons = new TreeSet<>();
        for (Map.Entry<Long, Movie> entry : collection.entrySet()) {
            persons.add(entry.getValue().getOperator());
        }
        ArrayList<Person> personArrayList = new ArrayList<>(persons);
        personArrayList.sort(Collections.reverseOrder());
        return personArrayList.toString();
    }


}
