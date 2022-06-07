package com.ztrain.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public enum Env {

    PRODUCTION,
    INTEGRATION,
    DEVELOPMENT;

    private final static String CONFIG_ENV = "config/env.properties";

    private String environmentUrl;

    public void initEnv() {
        Properties prop = new Properties();
        try {
            prop.load(new FileReader(CONFIG_ENV));
            prop.forEach((key, value) -> {
                Env.valueOf(key.toString().toUpperCase()).setUrl(value.toString());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUrl(String url) {
        this.environmentUrl = url;
    }

    public String getUrl() {
        if(this.environmentUrl == null)
            this.initEnv();
        return environmentUrl;
    }

    public String getUrl(String endpoint) {
        String base = getUrl();
        if(!base.endsWith("/") && !endpoint.startsWith("/"))
            return base + "/" + endpoint;
        else
            return base + endpoint;
    }

}
