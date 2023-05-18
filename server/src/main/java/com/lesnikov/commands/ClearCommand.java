package com.lesnikov.commands;

import com.lesnikov.server.CollectionManager;
import com.lesnikov.utils.IDGenerator;

public class ClearCommand {

    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute() {
        this.collectionManager.getCollection().clear();
        IDGenerator.removeAllIds();
        return "Collection has been cleaned!\n";
    }

}