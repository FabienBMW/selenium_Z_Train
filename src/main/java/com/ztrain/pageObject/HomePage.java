package com.ztrain.pageObject;

import com.ztrain.context.Context;
import com.ztrain.context.ScenarioContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class HomePage extends Page {
    private final static Logger LOG = LogManager.getLogger(Page.class);

    @FindBy(className = "style_quantity_in__XmF4D")
    private List<WebElement> plusIcon;
    @FindBy(className = "style_quantity_dec__nm5ig")
    private List<WebElement> decreaseQuantityCart;

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
    private List<WebElement> productName;

    @FindBy(css = "#style_quantity_wrapper__2QMug > button:nth-child(3) > svg")
    private WebElement addQuantityButton;

    @FindBy(className = "style_btn_add_cart__WFoN1")
    private List<WebElement> addToCartIcons;

    @FindBy(className = "style_input_quantity__xZDIb")
    private WebElement inputQuantity;

    @FindBy(className = "style_trash_product_cart__7Yzni")
    private List<WebElement> deleteProductIcon;

    @FindBy(css = "div.style_card_body__EhpLW > p:nth-child(2)")
    private List<WebElement> productCartPrice;

    @FindBy(id = "style_price__QNXBx")
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

    @FindBy(css = "style_card_body__EhpLW")
    private List<WebElement> cartProducts;

    @FindBy(css = "#style_price__QNXBx > span")
    private WebElement popupPrice;

    @FindBy(css = "#style_detail_wrapper__a7fpS > div:nth-child(4)")
    private WebElement popupDescription;

    @FindBy(className = "style_card_footer__q1lbJ")
    private List<WebElement> productsName;


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
        String message = getText(addToCartMessage);
        if (waitUntil(invisibilityOf(addToCartMessage)))
            LOG.info("La notif a disparu");
        return message;
    }

    public void openCard() {
        if (waitUntil(visibilityOf(addToCartMessage)))
            waitUntil(invisibilityOf(addToCartMessage));
        clickOn(cardIcon);
        if (waitUntil(visibilityOfAllElements(productName)))
            LOG.info("Items visible in cart");
        else LOG.info("Items not yet visible");
    }

    /*public String displayProductName() {
        return this.productName.getText();
    }*/

    public void deleteProductCard(String productN) {
        int index = getSpecificWebElement(productName, productN);
        clickOn(deleteProductIcon.get(index));
    }

    public Double getProductPrice() {
        return Double.parseDouble(this.productPrice.getText().replace(" €", ""));
    }

    public Double getProductCartPrice(String productN) {
        int index = getSpecificWebElement(productName, productN);
        return Double.parseDouble(this.productCartPrice.get(index).getText().replace(" €", ""));
    }

    public Double getTotalPriceCart() {
        return Double.parseDouble(this.totalPriceCart.getText().replace(" €", ""));
    }

    public boolean isProductDeleted(ScenarioContext context) {
        double cartTotal = Math.round((double) context.get(Context.CART_TOTAL_PRICE) * 100.0) / 100.0;
        double productPrice = Math.round((double) context.get(Context.PRODUCT_PRICE) * 100.0) / 100.0;
        double newCartPrice = cartTotal - productPrice;
        System.out.println("le get text donne " + totalPriceCart.getText());
        return waitUntil(textToBePresentInElement(totalPriceCart, Double.toString(newCartPrice))) || waitUntil(visibilityOf(emptyCartMessageField));
    }

    public void emptyCart() {
        clickOn(trashCartButton);
        waitUntil(visibilityOfAllElements(emptyCartWrapper));
    }

    public String displayEmptycartMessage() {
        return this.emptyCartMessageField.getText();
    }

    public void logOut() {
        if (shortUntil(visibilityOf(accountIcon)))
            action.moveToElement(accountIcon)
                    .moveToElement(logOutButton)
                    .click()
                    .perform();
    }

    public void increaseProductQuantity(String pN) {
        int index = 0;
        if (waitUntil(visibilityOfAllElements(productName))) {
            for (WebElement productN : productName) {
                if (productN.getText().contains(pN.substring(0, 15))) {
                    break;
                }
                index++;
            }
            clickOn(plusIcon.get(index));
        }

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

    public boolean isProductQuantityAdded(double productPrice) {
        double newTotalCartPrice = Math.round((getTotalPriceCart() + productPrice) * 100) / 100.0;
        return waitUntil(textToBePresentInElement(totalPriceCart, Double.toString(newTotalCartPrice)));
    }

    public void addProductQuantity() {
        clickOn(addQuantityButton);
    }

    public String getProductQuantity(String pN) {
        int index = getSpecificWebElement(productName, pN);
        if (index == -1)
            return "0";
        return getText(quantityField.get(index));
    }

    public boolean isQuantityUpdated(String productN, String actualQuantity) {
        int index = getSpecificWebElement(productName, productN);
        return waitUntil(textToBePresentInElement(quantityField.get(index), actualQuantity));
    }

    public String getPopupPrice() {
        return getText(popupPrice).replace(" €", "");
    }

    public boolean isDescription() {
        return waitUntil(visibilityOf(popupDescription));
    }

    public void addToCartWithIcon(String productN) {
        waitUntil(visibilityOfAllElements(productsName));
        int index = getSpecificWebElement(productsName, productN);
        if (index >= 0) {
            moveToElement().moveToElement(productsName.get(index)).perform();
            clickOn(addToCartIcons.get(index));
        } else {
            LOG.info("Produit non trouvé");
        }
    }

    public double totalCartPriceCalculation() {
        return productCartPrice.stream().mapToDouble(webElement -> Double.parseDouble(getText(webElement).replace(" €", ""))).sum();
    }

    public void fillProductQuantity(String quantity) {
        sendKeysSlowly(inputQuantity, quantity);
    }

    public void decreaseProductQuantity(String productN, String number, int totalQuantity) {
        int index = getSpecificWebElement(productName, productN);
        for (int i =0; i< Integer.parseInt(number); i++) {
            clickOn(decreaseQuantityCart.get(index));
            if (waitUntil(textToBePresentInElement(quantityField.get(index), String.valueOf(totalQuantity-1)))) {
                totalQuantity--;
                LOG.info("button clicked");
            }
            System.out.println(quantityField.get(index).getText());
        }

    }
}
