package com.lesnikov.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.lesnikov.model.Movie;
import com.lesnikov.wrappers.MovieWrapper;

public class MovieSerializer {

    public static String serialize(Movie movie) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            MovieWrapper movieWrapper = new MovieWrapper().wrap(movie);
            return objectMapper.writeValueAsString(movieWrapper);
        } catch (JsonProcessingException jsonProcessingException) {
            System.err.println(jsonProcessingException.getMessage());
            jsonProcessingException.printStackTrace();
            return "";
        }
    }

    public static Movie deserialize(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            MovieWrapper movieWrapper = objectMapper.readValue(json, MovieWrapper.class);
            return movieWrapper.unwrap();
        } catch (JsonProcessingException jsonProcessingException) {
            return new Movie();
        }
    }

}
