package com.lesnikov.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.lesnikov.model.Person;

public class PersonSerializer {

    public static String serialize(Person person) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(person);
        } catch (JsonProcessingException jsonProcessingException) {
            return null;
        }
    }

    public static Person deserialize(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, Person.class);
        } catch (JsonProcessingException jsonProcessingException) {
            return null;
        }
    }

}
