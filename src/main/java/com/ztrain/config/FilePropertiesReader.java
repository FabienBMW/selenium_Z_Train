package com.ztrain.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public enum FilePropertiesReader {

    INSTANCE;

    public static FilePropertiesReader getInstance() {return INSTANCE;}

    private Properties properties = new Properties();

    public final String chromeDriverPath;
    public final String firefoxDriverPath;

    FilePropertiesReader() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("config/config.properties"));
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        chromeDriverPath = readProperty("chromeDriverPath");
        firefoxDriverPath = readProperty("firefoxDriverPath");

    }

    private String readProperty(String key) {
        String property = properties.getProperty(key);
        if(property == null || property.isEmpty()) {
            System.out.println(key + "value is missing");
        }
        return property;
    }
}
