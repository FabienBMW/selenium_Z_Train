package com.ztrain.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class RegisterPage extends Page{

    @FindBy(id = "email_register")
    private WebElement emailField;
    @FindBy(id = "password_register")
    private WebElement passwordField;
    @FindBy(id = "btn_register")
    private WebElement registerButton;
    @FindBy(className = "style_messageError__LxTAG")
    private List<WebElement> formErrors;

    public void goToRegisterPage() {
        driver.get(ENV.getUrl("/auth/register"));
    }

    public void fillEmailField(String email) {
        sendKeysSlowly(emailField, email);
    }

    public void fillPasswordField(String password) {
        sendKeysSlowly(passwordField, password);
    }

    public void submitForm() {
        clickOn(registerButton);
    }

    public boolean isFormError() {
        return shortUntil(ExpectedConditions.visibilityOfAllElements(formErrors));
    }
}
