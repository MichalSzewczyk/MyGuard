package com.guard.myguard.services.interfaces;

public interface SerializingService {
    <T> String serialize(T objectToSerialization);
    <T> T deserialize(String json, Class<T> tClass);
}
