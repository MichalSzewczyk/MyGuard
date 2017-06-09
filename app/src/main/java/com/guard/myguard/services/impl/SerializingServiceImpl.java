package com.guard.myguard.services.impl;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guard.myguard.services.interfaces.SerializingService;

import java.io.IOException;

public class SerializingServiceImpl implements SerializingService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> String serialize(T objectToSerialization) {
        String serializationResult = null;
        try {
            serializationResult = objectMapper.writeValueAsString(objectToSerialization);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serializationResult;
    }

    @Override
    public <T> T deserialize(String json, Class<T> tClass) {
        T deserialized = null;
        try {
            deserialized = new ObjectMapper().readValue(json, tClass);
        } catch (IOException e) {
            Log.e("Deserialization error", e.getMessage());
        }
        return deserialized;
    }
}
