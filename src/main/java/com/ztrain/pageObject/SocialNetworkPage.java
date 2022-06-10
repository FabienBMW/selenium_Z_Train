package com.ztrain.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SocialNetworkPage extends Page{

    @FindBy(css = "#identifierId")
    private WebElement emailAddressGoogle;
    @FindBy(css = "#identifierNext > div > button > span")
    private WebElement next;
    /*@FindBy(className = "whsOnd zHQkBf")
    private WebElement googlePassword;*/

    public void fillGoogleEmailField(String googleEmail) {
        sendKeysSlowly(emailAddressGoogle, googleEmail);
        clickOn(next);
    }

    public void fillPasswordField(String password) {
        sendKeysSlowly(emailAddressGoogle, password);
        clickOn(next);
    }
}
