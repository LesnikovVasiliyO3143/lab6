package com.lesnikov.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lesnikov.model.Location;

public class LocationSerializer {

    public static String serialize(Location location) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(location);
        } catch (JsonProcessingException jsonProcessingException) {
            return null;
        }
    }

    public static Location deserialize(String json) {
        try {
            return new ObjectMapper().readValue(json, Location.class);
        } catch (JsonProcessingException jsonProcessingException) {
            return null;
        }
    }

}
