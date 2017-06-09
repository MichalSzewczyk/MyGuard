package com.guard.myguard.services.impl;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guard.myguard.services.interfaces.ParsingService;

import java.io.IOException;
import java.io.Serializable;

public class JsonParsingFacade implements ParsingService {
    private final static String JSON_EXCEPTION = "Unable to %s object: %s";
    private final ObjectMapper objectMapper;

    public JsonParsingFacade(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String serialize(Serializable object) {
        String result = null;
        try {
            result = objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            Log.e(String.format(JSON_EXCEPTION, "serialize", object), e.getMessage());
        }
        return result;
    }

    @Override
    public <T> T deserialize(String json, Class<T> type) {
        T result = null;
        try {
            result = objectMapper.readValue(json, type);
        } catch (IOException e) {
            Log.e(String.format(JSON_EXCEPTION, "deserialize", json), e.getMessage());
        }
        return result;
    }
}
