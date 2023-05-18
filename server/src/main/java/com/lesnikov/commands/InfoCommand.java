package com.lesnikov.commands;

import com.lesnikov.server.CollectionManager;

public class InfoCommand {

    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute() {
        return String.format("%-32s", "Type of collection:")                +
                collectionManager.getCollection().getClass()         + "\n" +
                String.format("%-32s", "Collection initialization time:")   +
                collectionManager.getInitTime()                      + "\n" +
                String.format("%-32s", "Size of collection:")               +
                collectionManager.getCollection().size()             + "\n";
    }

}