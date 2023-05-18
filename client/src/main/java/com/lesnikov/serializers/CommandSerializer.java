package com.lesnikov.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.lesnikov.wrappers.Request;

public class CommandSerializer {

    public static String serialize(Request commandWrapper) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(commandWrapper);
        } catch (JsonProcessingException jsonProcessingException) {
            System.err.println(jsonProcessingException.getMessage());
            jsonProcessingException.printStackTrace();
            return null;
        }
    }

    public static Request deserialize(String json) {
        if (json == null || json.isEmpty()) {
            return new Request();
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<Request> typeRef = new TypeReference<Request>(){};
            return mapper.readValue(json, typeRef);
        } catch (JsonProcessingException jsonProcessingException) {
            System.err.println(jsonProcessingException.getMessage());
            jsonProcessingException.printStackTrace();
            return null;
        }
    }

}
