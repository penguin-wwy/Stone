package com;

/**
 * Created by penguin on 2016/11/30.
 */

public interface Environment {
    void put(String name, Object value);
    Object get(String name);
}