package com.guard.myguard.services.interfaces;

import java.io.Serializable;


public interface ParsingService {
    /**
     * Method is responsible for serializing object to json
     *
     * @param object is object to serialize
     * @return json representation of serialized object
     */
    String serialize(Serializable object);

    /**
     * Method is responsible for deserialization object from json
     *
     * @param json is string representation of serialized object
     * @param type is class to which we want to deserialize json
     * @return deserialized object
     */
    <T> T deserialize(String json, Class<T> type);
}
