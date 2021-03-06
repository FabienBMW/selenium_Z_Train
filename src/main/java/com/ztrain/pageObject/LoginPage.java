package com.ztrain.pageObject;

import com.ztrain.context.Context;
import com.ztrain.context.ScenarioContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.TreeSet;

public class LoginPage extends Page {

    @FindBy(id = "email_login")
    private WebElement emailField;

    @FindBy(id = "password_login")
    private WebElement passwordField;

    @FindBy(id = "btn_login")
    private WebElement submitButton;

    @FindBy(className = "style_messageError__LxTAG")
    private List<WebElement> errorMessages;

    @FindBy(css = "#style_container_input_password___0rEz > div > svg")
    private WebElement displaypwdBtn;

    @FindBy(className = "style_link__unbWN")
    private WebElement registerLink;

    @FindBy(className = "style_header_title_form__sxS9B")
    private WebElement titleLoginPage;

    @FindBy(className = "style_forgotpass__PRHm_")
    private WebElement resetPasswordLink;

    @FindBy(id = "email_reset_pass")
    private WebElement resetEmailField;

    @FindBy(id = "reset_password")
    private WebElement resetNewPasswordField;

    @FindBy(id = "btn_reset_password")
    private WebElement resetButton;

    @FindBy(css = "#__next > div > main > div.style_col_2__kzyDS > div > button")
    private WebElement socialConnectionButton;

    @FindBy(className = "style_header_subTitle__jdTen")
    private WebElement welcomeMessage;

    /*@FindBy(css = "#identifierId")
    private WebElement emailAddressGoogle;
    @FindBy(className = "VfPpkd-RLmnJb")
    private WebElement next;
    @FindBy(className = "whsOnd zHQkBf")
    private WebElement googlePassword;*/

    public void goToLoginPage() {
        driver.get(ENV.getUrl("/auth/login"));
    }

    public void login(String email, String password) {
        sendKeysSlowly(emailField, email);
        sendKeysSlowly(passwordField, password);
        clickOn(submitButton);
    }

    public void loginFieldRequired() {
        clickOn(submitButton);
    }

    public String getErrorMessage() {
        return this.errorMessages.get(0).getText();
    }

    public void displayPwd(String password) {
        sendKeysSlowly(passwordField, password);
        clickOn(displaypwdBtn);
    }

    public boolean isPasswordInputOfType(String type) {
        return passwordField.getAttribute("type").equals(type);
    }

    public void goToRegistrationPage() {
        clickOn(registerLink);
    }

    public void goToResetPasswordPage() {
        resetPasswordLink.click();
    }

    public void resetPassword(String email, String newPassword) {
        sendKeysSlowly(resetEmailField, email);
        sendKeysSlowly(resetNewPasswordField, newPassword);
        clickOn(resetButton);
    }

    public String loginpage() {
        this.wait.until(ExpectedConditions.visibilityOf(this.titleLoginPage));
        return this.titleLoginPage.getText();
    }

    public void socialConnection() {
        clickOn(socialConnectionButton);
    }

    public String getNewWindow(String socialNetwork) {
        String motherWindow = driver.getWindowHandle();
        context.set(Context.WINDOW, motherWindow);
        String lastWindowHandle = new TreeSet<>(driver.getWindowHandles()).last();
        driver.switchTo().window(lastWindowHandle);
        String url = driver.getCurrentUrl();
        switch (socialNetwork) {
            case "google":
                /*fillGoogleEmailField("bibo.inator@gmail.com");
                fillPasswordField("G123456789");*/
                break;
            case "twitter":
                break;
        }
        /*driver.close();
        driver.switchTo().window(motherWindow);*/
        return url;
    }

    /*public void fillGoogleEmailField(String googleEmail) {
        sendKeysSlowly(emailAddressGoogle, googleEmail);
        clickOn(next);
    }

    public void fillPasswordField(String password) {
        sendKeysSlowly(googlePassword, password);
        clickOn(next);
    }*/

    public void fillEmailField(String email) {
        sendKeysSlowly(emailField, email);
    }

    public void clickOnPasswordField() {
        clickOn(passwordField);
    }

    public boolean isWelcomeMessage(String message) {
        return welcomeMessage.getText().contains(message);
    }

    public boolean isEmailError() {
        for (WebElement webElement: errorMessages) {
            if (webElement.getText().contains("email"))
                return true;
        }
        return false;
    }
}
