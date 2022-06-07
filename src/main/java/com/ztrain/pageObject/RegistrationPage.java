package com.ztrain.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends Page {

    @FindBy(className = "style_header_title_form__sxS9B")
    private WebElement registrationPageTitle;

    public String getRegisterTitle() {
        waitUntil(ExpectedConditions.visibilityOf(this.registrationPageTitle));
        return this.registrationPageTitle.getText();
    }


}

