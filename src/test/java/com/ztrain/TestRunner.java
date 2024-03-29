package com.ztrain;

import com.ztrain.config.SystemPropertiesReader;
import com.ztrain.driver.WebDriverManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;


@CucumberOptions(
        features = {"./src/test/resources/features"},
        glue = {"com.ztrain.steps", "com/ztrain/pageObjects"},
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/reports/html/htmlreport",
                "json:target/reports/jsonreports/index.json",
                "junit:target/reports/xmlreport.xml",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        tags = "@TEST_OF-831"
)
public class TestRunner extends AbstractTestNGCucumberTests {

    private static WebDriver driver;

    private static final SystemPropertiesReader SYSTEM_PROPERTIES = SystemPropertiesReader.getInstance();

    @BeforeClass
    public static void beforeTest() {
        WebDriverManager.setDriver(SYSTEM_PROPERTIES.browser);
        driver = WebDriverManager.getDriver();
    }


    @AfterClass
    public static void afterTest() {
        WebDriverManager.getDriver().quit();
    }
}

