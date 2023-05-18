package com.lesnikov.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lesnikov.model.Movie;
import com.lesnikov.wrappers.MovieWrapper;

import java.util.HashMap;
import java.util.Map;

public class CollectionSerializer {

    public static String serialize(HashMap<Long, Movie> collection) {
        try {
            HashMap<Long, MovieWrapper> copy = new HashMap<>();
            for (Map.Entry<Long, Movie> entry : collection.entrySet()) {
                copy.put(entry.getKey(), MovieWrapper.wrap(entry.getValue()));
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(copy);
        } catch (JsonProcessingException jsonProcessingException) {
            System.err.println(jsonProcessingException.getMessage());
            jsonProcessingException.printStackTrace();
            return null;
        }
    }

    public static HashMap<Long, Movie> deserialize(String json) {
        if (json.isEmpty() || json == null) {
            return new HashMap<>();
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<HashMap<Long, MovieWrapper>> typeRef = new TypeReference<HashMap<Long, MovieWrapper>>(){};
            HashMap<Long, MovieWrapper> copy = mapper.readValue(json, typeRef);
            HashMap<Long, Movie> collection = new HashMap<>();
            for (Map.Entry<Long, MovieWrapper> entry : copy.entrySet()) {
                collection.put(entry.getKey(), entry.getValue().unwrap());
            }
            return collection;
        } catch (JsonProcessingException jsonProcessingException) {
            System.err.println(jsonProcessingException.getMessage());
            jsonProcessingException.printStackTrace();
            return null;
        }
    }

}
