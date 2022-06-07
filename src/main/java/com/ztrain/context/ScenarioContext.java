package com.ztrain.context;

import java.util.HashMap;

public class ScenarioContext {

    private static final ScenarioContext INSTANCE = new ScenarioContext();

    private final HashMap<String, Object> buffer = new HashMap<>();

    public void set(Context key, Object value ){
        this.buffer.put(key.toString(), value);
    }

    public Object get( Context key ){
        return this.buffer.get(key.toString());
    }

    public static ScenarioContext getINSTANCE() {
        return INSTANCE;
    }

}
