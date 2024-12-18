package com.hype360kh.explore.utils;

import java.util.HashMap;

public class State extends HashMap<String, Object> {
    public <T> T get(String key, Class<T> clazz) {
        final var value = this.get(key);
        if (value != null) {
            return clazz.cast(value);
        }
        return null;
    }


    public <T> T get(String key, Class<T> clazz, T defaultValue) {
        final var value = this.get(key);
        if (value != null) {
            return clazz.cast(value);
        }
        return defaultValue;
    }
}
