package com.ztrain.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class FavoritePage extends Page {

    @FindBy(className = "style_card_footer__q1lbJ")
    private List<WebElement> wishlistProductsName;
    @FindBy(id = "style_empty_favorite_wrapper__UPcGu")
    private WebElement emptyProductMessage;

    public void goToFavoritePage() {
        driver.get(ENV.getUrl("/myfavorite"));
    }

    public boolean isInWishlist(String productName) {
        if (waitUntil(ExpectedConditions.visibilityOfAllElements(wishlistProductsName))) {
            return wishlistProductsName.stream().anyMatch(product -> product.getText().contains(productName));
        }
        return false;
    }

    public String getEmptyMessage() {
        return emptyProductMessage.getText();
    }
}
