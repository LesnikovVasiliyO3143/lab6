package com.lesnikov.commands;

import com.lesnikov.server.CollectionManager;

import java.util.Map;

public class HelpCommand {

    private final CollectionManager collectionManager;

    public HelpCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute() {
        Map<String, String> tutorial = collectionManager.getManual();
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, String> entry : tutorial.entrySet()) {
            String key = String.format("%-43s", entry.getKey());
            String value = String.format(" : %1$s", entry.getValue());
            result.append(key).append(value).append("\n");
        }
        result.append("\n").append("ATTENTION. {element} syntax means, that user input required after command " +
                "running;").append("\n");
        result.append("ATTENTION. [update id]-like syntax means, that you should type [update] and type ID of " +
                "item for updating after it.").append("\n");
        return result.toString();
    }
}
