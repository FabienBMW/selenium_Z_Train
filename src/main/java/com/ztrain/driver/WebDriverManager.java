package com.ztrain.driver;

import org.openqa.selenium.WebDriver;

public final class WebDriverManager {

    private static ThreadLocal<Driver> driver = new ThreadLocal<>();

    private static final WebDriverManager INSTANCE = new WebDriverManager();

    public WebDriverManager getInstance() { return INSTANCE; }

    private WebDriverManager() {
    }

    public static WebDriver getDriver() {
        return driver.get().getDriver();
    }

    public static void setDriver(String browser) {
        driver.set(DriverFactory.getDriver(browser));
    }
}