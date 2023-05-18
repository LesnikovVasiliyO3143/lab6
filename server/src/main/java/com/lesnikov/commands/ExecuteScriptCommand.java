package com.lesnikov.commands;

import com.lesnikov.model.Movie;
import com.lesnikov.server.CollectionManager;
import com.lesnikov.wrappers.Request;

import java.util.List;

public class ExecuteScriptCommand {

    private final CollectionManager collectionManager;

    public ExecuteScriptCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute(List<Request> requests) {
        StringBuilder results = new StringBuilder();
            for (Request request : requests) {
                switch (request.getCommand()) {
                    case "help":
                        results.append(new HelpCommand(this.collectionManager).execute())
                                .append("\n");
                        break;
                    case "info":
                        results.append(new InfoCommand(this.collectionManager).execute())
                                .append("\n");
                        break;
                    case "show":
                        results.append(new ShowCommand(this.collectionManager).execute())
                                .append("\n");
                        break;
                    case "insert":
                        if (request.getKey() == null || request.getMovieWrapper() == null) {
                            results.append("Incorrect arguments for a command").append("\n");
                        } else {
                            Long key = request.getKey();
                            Movie movie = request.getMovieWrapper().unwrap();
                            results.append(
                                    new InsertCommand(this.collectionManager).execute(key, movie)).append("\n");
                        }
                        break;
                    case "update":
                        if (request.getKey() == null || request.getMovieWrapper() == null) {
                            results.append("Incorrect arguments for a command").append("\n");
                        } else {
                            Long key = request.getKey();
                            Movie movie = request.getMovieWrapper().unwrap();
                            results.append(
                                    new UpdateCommand(this.collectionManager).execute(key, movie)).append("\n");
                        }
                        break;
                    case "remove_key":
                        if (request.getKey() == null) {
                            results.append("Incorrect arguments for a command").append("\n");
                        } else {
                            Long key = request.getKey();
                            results.append(
                                    new RemoveKeyCommand(this.collectionManager).execute(key)).append("\n");
                        }
                        break;
                    case "clear":
                        results.append(new ClearCommand(this.collectionManager).execute())
                                .append("\n");
                        break;
                    case "execute_script":
                        if (request.getCommandList() == null || request.getCommandList().isEmpty()) {
                            results.append("Incorrect arguments for a command").append("\n");
                        } else {
                            results.append(new ExecuteScriptCommand(this.collectionManager)
                                    .execute(request.getCommandList())).append("\n");
                        }
                        break;
                    case "filter_greater_than_operator":
                        if (request.getPerson() == null) {
                            results.append("Incorrect arguments for a command").append("\n");
                        } else {
                            results.append(new FilterGreaterThanOperatorCommand(this.collectionManager).execute(
                                    request.getPerson())).append("\n");
                        }
                        break;
                    case "remove_greater":
                        if (request.getMovieWrapper() == null) {
                            results.append("Incorrect arguments for a command").append("\n");
                        } else {
                            results.append(new RemoveGreaterCommand(this.collectionManager)
                                            .execute(request.getMovieWrapper().unwrap())).append("\n");
                        }
                        break;
                    case "remove_lower":
                        if (request.getMovieWrapper() == null) {
                            results.append("Incorrect arguments for a command").append("\n");
                        } else {
                            results.append(new RemoveLowerCommand(this.collectionManager)
                                            .execute(request.getMovieWrapper().unwrap()))
                                    .append("\n");
                        }
                        break;
                    case "print_field_descending_operator":
                        results.append(new PrintFieldDescendingOperatorCommand(this.collectionManager)
                                .execute()).append("\n");
                        break;
                    case "print_unique_total_box_office":
                        results.append(new PrintUniqueTotalBoxOfficeCommand(this.collectionManager).execute())
                                .append("\n");
                        break;
                    case "replace_if_greater":
                        if (request.getKey() == null || request.getMovieWrapper() == null) {
                            results.append("Incorrect arguments for a command").append("\n");
                        } else {
                            Long key = request.getKey();
                            Movie movie = request.getMovieWrapper().unwrap();
                            results.append(new ReplaceIfGreaterCommand(this.collectionManager)
                                    .execute(key, movie)).append("\n");
                        }
                        break;
                    default:
                        results.append("Unknown command!").append("\n");
                        break;
                }
            }
        return results.toString();
    }

}