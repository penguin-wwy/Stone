package com;

import java.util.HashMap;

/**
 * Created by penguin on 2016/12/6.
 */
public class BasicEnv implements Environment {
    protected HashMap<String, Object> values;
    public BasicEnv() {
        values = new HashMap<String, Object>();
    }
    public void put(String name, Object value) {
        values.put(name, value);
    }
    public Object get(String name) {
        return values.get(name);
    }
}
