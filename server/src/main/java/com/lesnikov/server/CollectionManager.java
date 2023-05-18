package com.lesnikov.server;

import com.lesnikov.model.Movie;
import com.lesnikov.serializers.CollectionSerializer;
import com.lesnikov.utils.IDGenerator;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class CollectionManager {

    private static CollectionManager INSTANCE;
    private HashMap<Long, Movie> collection;
    private ZonedDateTime initTime;
    private Map<String, String> manual;

    private String filepath;

    public static CollectionManager getInstance(String pathToCollection) {
        if (INSTANCE == null) {
            INSTANCE = new CollectionManager(pathToCollection);
        }
        return INSTANCE;
    }

    private CollectionManager() {}

    private CollectionManager(String filepath) {
        downloadCollection(filepath);
        this.initTime = ZonedDateTime.now();
        prepareManual();
    }

    private void downloadCollection(String pathToFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
            StringBuilder fileData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                fileData.append(line);
            }
            Map<Long, Movie> temporaryCollection = CollectionSerializer.deserialize(fileData.toString());
            this.collection = validateCollection(CollectionSerializer.deserialize(fileData.toString()));
            System.out.println("Collection has been loaded successfully!");
            System.out.println("Loaded: " + this.collection.size() + " items.");
            assert temporaryCollection != null;
            this.filepath = pathToFile;
            System.out.println(temporaryCollection.size() - this.collection.size() + " items from file were " +
                    "incorrect and hasn't been loaded.");

        } catch (IOException e) {
            System.out.println("Cannot download collection. Downloaded empty collection. Try to check file " +
                    "and restart an app");
            System.out.println("Reason of failure: " + e.getMessage());
            System.exit(0);
        }
    }

    public String saveCollection(String json) {
        String fileName = this.filepath;
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            out.println(json);
            out.close();
            bw.close();
            fw.close();
            return "Collection has been saved successully.\n";
        } catch (IOException e) {
            return "Error writing to file.\n";
        }
    }

    private HashMap<Long, Movie> validateCollection(Map<Long, Movie> temporaryCollection) {
        if (temporaryCollection == null) return new HashMap<>();
        HashMap<Long, Movie> validatedCollection = new HashMap<>();
        for (Map.Entry<Long, Movie> entry : temporaryCollection.entrySet()) {
            if (validateCollectionItem(entry.getValue())) {
                validatedCollection.put(entry.getKey(), entry.getValue());
            }
        }
        return validatedCollection;
    }

    private boolean validateCollectionItem(Movie movie) {
        if (movie.getId() == null) return false;
        if (!IDGenerator.checkIfIDUnique(movie.getId())) return false;
        if (movie.getId() <= 0) return false;
        if (movie.getName() == null) return false;
        if (movie.getName().isEmpty()) return false;
        if (movie.getCoordinates() == null) return false;
        if (movie.getCoordinates().getX() == null) return false;
        if (movie.getCreationDate() == null) return false;
        if (movie.getOscarsCount() == null) return false;
        if (movie.getOscarsCount() <= 0) return false;
        if (movie.getTotalBoxOffice() == null) return false;
        if (movie.getTotalBoxOffice() <= 0) return false;
        if (movie.getGenre() == null) return false;
        if (movie.getMpaaRating() == null) return false;
        if (movie.getOperator() == null) return false;
        if (movie.getOperator().getName() == null) return false;
        if (movie.getOperator().getName().isEmpty()) return false;
        if (movie.getOperator().getHeight() == null) return false;
        if (movie.getOperator().getHeight() <= 0) return false;
        if (movie.getOperator().getLocation() == null) return false;
        if (movie.getOperator().getLocation().getX() == null) return false;
        return movie.getOperator().getLocation().getY() != null;
    }

    private void prepareManual() {
        this.manual = new HashMap<>();
        this.manual.put("help", "get help on alternative commands");
        this.manual.put("info", "print all collection items into the string representation");
        this.manual.put("show", "print to standard output all elements of the collection in string representation");
        this.manual.put("insert key {element}", "add new item to collection with entered key");
        this.manual.put("update id {element}", "update the value of the collection element " +
        "whose ID matches the given one");
        this.manual.put("remove_key key", "remove an element from the collection by its key, whose should be entered");
        this.manual.put("clear", "remove all items from collection");
        this.manual.put("save", "save collection to file");
        this.manual.put("execute_script filename", "read and execute script from given file. " +
        "The same views are found in the script as in the interactive mode");
        this.manual.put("exit", "exit the program (without closing in the file)");
        this.manual.put("remove_greater {element}", "remove from the collection all elements " +
        "strictly greater than the given one");
        this.manual.put("remove_lower", "remove from the collection all elements smaller than the given one");
        this.manual.put("replace_if_greater key {element}", "replace value by key if new value is greater than old");
        this.manual.put("filter_greater_than_operator operator", "display elements whose operator field value is " +
                "greater than the given one");
        this.manual.put("print_unique_total_box_office", "display the unique values of the totalBoxOffice field " +
                "of all items in the collection");
        this.manual.put("print_field_descending_operator", "print the values of the operator field of all " +
                "elements in descending order");
    }

    public HashMap<Long, Movie> getCollection() {
        return collection;
    }

    public void setCollection(HashMap<Long, Movie> collection) {
        this.collection = collection;
    }

    public ZonedDateTime getInitTime() {
        return initTime;
    }

    public Map<String, String> getManual() {
        return manual;
    }

}
