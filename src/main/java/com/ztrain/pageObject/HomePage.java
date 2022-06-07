package com.ztrain.pageObject;

import com.ztrain.context.Context;
import com.ztrain.context.ScenarioContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class HomePage extends Page{

    @FindBy(css = "nav h1")
    private WebElement ztrain_logo;

    @FindBy(id = "style_container__P9Oh0")
    public WebElement addToCartMessage;

    @FindBy(id = "style_popular_product_wrapper__z6J0h")
    private WebElement products;

    @FindBy(css = "h2.style_section_title__aO_Du")
    private WebElement productTitle;

    @FindBy(css = "#style_popular_product_wrapper__z6J0h > div:nth-child(2) > div.style_card_body__QuFGN")
    private WebElement Tshirt;

    @FindBy(className = "style_card__gNEqX")
    private List<WebElement> listOfProducts;

    @FindBy(id = "style_detail_wrapper__a7fpS")
    private WebElement productDetails;

    @FindBy(id = "email_login")
    private WebElement emailField;

    @FindBy(id = "password_login")
    private WebElement passwordField;

    @FindBy(id = "btn_login")
    private WebElement submitButton;

    @FindBy(id = "style_btn_add_cart__gTXM7")
    private WebElement addCardButton;

    @FindBy(id = "style_content_cart_wrapper__mqNbf")
    private WebElement cardIcon;

    @FindBy(className = "style_productName__lrC3N")
    private WebElement productName;

    @FindBy(className = "style_btn_add_cart__WFoN1")
    private List<WebElement> addToCartIcons;

    @FindBy(className = "style_input_quantity__xZDIb")
    private WebElement inputQuantity;

    @FindBy(className = "style_trash_product_cart__7Yzni")
    private WebElement deleteProductIcon;

    @FindBy(css = "div.style_card_body__EhpLW > p:nth-child(2)")
    private WebElement productPrice;

    @FindBy(css = "#style_totalPrice__o2yCy > h5:nth-child(2)")
    private WebElement totalPriceCart;

    @FindBy(id = "style_empty_cart_wrapper__23a1z")
    private WebElement emptyCartWrapper;

    @FindBy(id = "style_btn_trash_cart__ttfo9")
    private WebElement trashCartButton;

    @FindBy(css = "#style_empty_cart_wrapper__23a1z > p")
    private WebElement emptyCartMessageField;

    @FindBy(id = "style_avatar_wrapper__pEGIQ")
    private WebElement accountIcon;

    @FindBy(id = "logout")
    private WebElement logOutButton;

    @FindBy(className = "style_header_title_form__sxS9B")
    private WebElement titleLoginPage;

    @FindBy(className = "style_quantity_in__XmF4D")
    private List<WebElement> incrementQtyButton;

    @FindBy(className = "style_quantity_dec__nm5ig")
    private List<WebElement> decrementQtyButton;

    @FindBy(className = "style_quantity__qJbQ3")
    private List<WebElement> quantityField;

    @FindBy(id = "style_btn_cart__zrT9Q")
    private WebElement oderButton;

    @FindBy(css = "#style_checkout_wrapper__JTsFz>h1")
    private WebElement ValidateOderTitle;


    public void goToLoginPage() {
        driver.get(ENV.getUrl("/auth/login"));
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean isZTrainLogoDisplayed() {
        wait.until(visibilityOf(this.ztrain_logo));
        return this.ztrain_logo.isDisplayed();
    }

    public boolean displayProductPage() {
        waitUntil(visibilityOf(this.products));
        this.scrollDownToElement(products);
        return this.products.isDisplayed();
    }

    public void displayProductSheet() {
        waitUntil(visibilityOf(this.products));
        this.scrollDownToElement(products);
        clickOn(Tshirt);
    }

    public boolean displayProductDetails() {
        return this.productDetails.isDisplayed();
    }

    public void selectArticle(String article) {
        /*waitUntil(visibilityOf(this.products));
        this.scrollDownToElement(productTitle);
        action.moveToElement(listOfProducts.get(0)).build().perform();
        action.moveToElement(addToCartIcons.get(0))
                .click()
                .perform();

        action.moveToElement(listOfProducts.get(1)).build().perform();
        action.moveToElement(addToCartIcons.get(1))
                .click()
                .perform();*/
        if (longUntil(visibilityOfAllElements(listOfProducts))) {
            if (longUntil(ExpectedConditions.visibilityOfAllElements(listOfProducts))) {
                clickOn(listOfProducts.stream()
                        .filter(product -> product.getText().contains(article))
                        .collect(Collectors.toList())
                        .get(0)
                );
            }
        }
    }

    public void addToCart() {
        clickOn(addCardButton);
    }

    public String addedToCartMessage() {
        return getText(addToCartMessage);
    }

    public void openCard() {
        clickOn(cardIcon);
        if(waitUntil(visibilityOfAllElements(productName)))
            System.out.println("Items visible in cart");
        else System.out.println("Items not yet visible");
    }

    public String displayProductName() {
        return this.productName.getText();
    }

    public void deleteProductCard() {
        clickOn(deleteProductIcon);
    }

    public Double getProductPrice() {
        return Double.parseDouble(this.productPrice.getText().replace(" €", ""));
    }

    public Double getTotalPriceCart() {
        return Double.parseDouble(this.totalPriceCart.getText().replace(" €", ""));
    }

    public boolean isProductDeleted(ScenarioContext context) {
        double cartTotal = Math.round((double)context.get(Context.CART_TOTAL_PRICE)*100.0)/100.0;
        System.out.println(cartTotal + " VART TOTAL");
        double productPrice = Math.round((double) context.get(Context.PRODUCT_PRICE)*100.0)/100.0;
        System.out.println(productPrice + " PRDUCT ¨>iicE");
        double newCartPrice = cartTotal - productPrice;
        System.out.println("le nouveau prix est " + newCartPrice);
        if (waitUntil(textToBePresentInElement(totalPriceCart, Double.toString(newCartPrice))) || totalPriceCart == null) {
            return true;
        }
        return false;
    }

    public void emptyCart() {
        clickOn(trashCartButton);
        if(waitUntil(visibilityOfAllElements(emptyCartWrapper)));
    }

    public String displayEmptycartMessage() {
        return this.emptyCartMessageField.getText();
    }

    public void logOut() {
        if(shortUntil(visibilityOf(accountIcon)))
            action.moveToElement(accountIcon)
                .moveToElement(logOutButton)
                .click()
                .perform();
    }

    public void inrementQuantity() {
        clickOn(incrementQtyButton.get(0));
    }

    public void decrementQuantity() {
        clickOn(decrementQtyButton.get(0));
    }

    public void validateOder() {
        clickOn(oderButton);
    }

    public String getTitleValidationOder() {
        return this.ValidateOderTitle.getText();
    }

}
