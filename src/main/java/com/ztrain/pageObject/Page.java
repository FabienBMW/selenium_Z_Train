package com.ztrain.pageObject;

import com.ztrain.config.Env;
import com.ztrain.config.SystemPropertiesReader;
import com.ztrain.context.Context;
import com.ztrain.context.ScenarioContext;
import com.ztrain.driver.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Page {

    private final static Logger LOG = LogManager.getLogger(Page.class);

    public static final Env ENV = SystemPropertiesReader.getInstance().env;

    protected final WebDriver driver;
    protected JavascriptExecutor js;
    protected Actions action;

    protected WebDriverWait wait;
    protected WebDriverWait loadingWait;
    protected WebDriverWait shortWait;
    protected WebDriverWait longWait;
    public ScenarioContext context = new ScenarioContext();

    public Page() {
        this.driver = WebDriverManager.getDriver();
        PageFactory.initElements(this.driver, this);
        this.shortWait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        this.longWait = new WebDriverWait(this.driver, Duration.ofSeconds(15));
        this.loadingWait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
        this.js = (JavascriptExecutor) driver;
        this.action = new Actions(driver);

        LOG.info("Log message in shortwait ok");
    }

    /**
     * Wait until the condition in the function is satisfied
     *
     * @param isTrue the condition
     * @param <V>    the condition return type
     * @return true is the condition is satisfied, false if the condition hasn't been satisfied in the given time
     */
    protected <V>boolean waitUntil(Function<? super WebDriver, V> isTrue){
        try{
            wait.until(isTrue);
            return true;
        }catch (Exception e){
            LOG.debug(e.getMessage());
            return false;
        }
    }

    protected <V>boolean shortUntil(Function<? super WebDriver, V> isTrue){
        try{
            shortWait.until(isTrue);
            LOG.info("Log message in shortwait ok");
            return true;
        }catch (Exception e){
            return false;
        }
    }


    protected <V>boolean longUntil(Function<? super WebDriver, V> isTrue){
        try{
            longWait.until(isTrue);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Wait until the page is loaded
     */
    protected void waitForLoadingPage(){
        if(!longUntil(driver->js.executeScript("return document.readyState").equals("complete") || js.executeScript("return document.readyState").equals("interactive") )){
            LOG.error(driver.getCurrentUrl() + " not loaded");
            return;
        }
        LOG.info(driver.getCurrentUrl() + " successfully loaded");
    }

    /**
     * Open the page with the given url
     * @param url
     */
    protected void get(String url){
        driver.get(url);
        waitForLoadingPage();
    }

    protected void clickOn(WebElement element){
        if( !shortUntil(visibilityOf(element)) ){
            LOG.error("Element not visible after click");
        }

        if( !shortUntil(elementToBeClickable(element))){
            LOG.error("Element not clickable");
        }
        element.click();
    }

    @Attachment(value = "screenshot", type = "image/png")
    public void saveScreenShotPNG(){
        Allure.addAttachment("screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    protected void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true); scrollBy(0, -window.innerHeight/2 + arguments[0].offsetHeight/2);", element);
    }

    protected void scrollUpToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true); scrollBy(0, -window.innerHeight/2 - arguments[0].offsetHeight/2);", element);
    }

    protected void scrollDownToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);arguments[0].scrollTop=arguments[0].scrollHeight", element);
    }


    public void sendKeysSlowly(WebElement webElement, String key) {
        for (int i = 0; i < key.length(); i++) {
            webElement.sendKeys(key.substring(i, i + 1));
        }
    }

    public Actions moveToElement() {
        return action;
    }

    public String getText(WebElement elt) {
        if (waitUntil(visibilityOf(elt)))
            return elt.getText();
        return "Element was not found";
    }

    public int getSpecificWebElement(List<WebElement> webElements, String searchText) {
        int index = 0;
        if (waitUntil(visibilityOfAllElements(webElements))) {
            for (WebElement webElement : webElements) {
                System.out.println(webElement.getText());
                if (webElement.getText().contains(searchText.substring(0, 15))) {
                    break;
                }
                index++;
            }
            System.out.println("la size est " + webElements.size() + " et l'index est " + index);
            if (index >= webElements.size())
                return -1 ;
            return index;
        } else {
            LOG.info("Elements does not appear");
            return -1;
        }
    }

    public double getPrice(WebElement element) {
        return Double.parseDouble(element.getText().replace(" €", ""));
    }

    public double roundTo2Decimal(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
