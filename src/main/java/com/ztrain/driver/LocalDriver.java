package com.ztrain.driver;

import com.ztrain.config.FilePropertiesReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LocalDriver implements Driver {

    private final String CHROME_WEBDRIVER = "webdriver.chrome.driver";
    private final String FIREFOX_WEBDRIVER = "webdriver.gecko.driver";

    private final WebDriver driver;

    public LocalDriver(String browser) {
        this.setUpDriver(browser);
        switch (browser.toLowerCase()) {
            case "firefox":
                this.driver = new FirefoxDriver(getFirefoxOptions());
                break;
            default:
                this.driver = new ChromeDriver(getChromeOptions());
                break;
        }
    }

    @Override
    public WebDriver getDriver() {
        return this.driver;
    }

    @Override
    public void closeDriver() {
        this.driver.close();
    }

    private void setUpDriver(String browser) {
        String prop, path;
        switch (browser.toLowerCase()) {
            case "firefox":
                prop = FIREFOX_WEBDRIVER;
                path = FilePropertiesReader.getInstance().firefoxDriverPath;
                break;
            default:
                prop = CHROME_WEBDRIVER;
                path = FilePropertiesReader.getInstance().chromeDriverPath;
                break;
        }
        System.setProperty(prop, path);
    }
}
