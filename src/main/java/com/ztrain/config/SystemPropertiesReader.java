package com.ztrain.config;

public enum SystemPropertiesReader {

    INSTANCE;

    public static SystemPropertiesReader getInstance() {
        return INSTANCE;
    }

    public final String browser;
    public final boolean headless;
    public final Env env;

    SystemPropertiesReader() {
        browser = System.getProperty("browser", "firefox");
        headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        env = Env.valueOf(System.getProperty("env", "development").toUpperCase());
    }


}
