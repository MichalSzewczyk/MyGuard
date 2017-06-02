package com.guard.myguard.utils;

import java.io.Serializable;

public final class Tuple<K, V> implements Serializable {
    private final K key;
    private final V value;

    public Tuple(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
