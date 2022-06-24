package com.ztrain.steps;

import com.ztrain.context.Context;
import com.ztrain.context.ScenarioContext;
import com.ztrain.pageObject.HomePage;
import com.ztrain.pageObject.LoginPage;
import com.ztrain.pageObject.Page;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.AssertJUnit;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class HomeSteps extends Page {
    private HomePage homePage;
    private LoginPage loginPage;
    private ScenarioContext context;

    public HomeSteps(HomePage homepage, LoginPage loginPage, ScenarioContext context) {
        this.homePage = homepage;
        this.loginPage = loginPage;
        this.context = context;
    }

    // TEST_OF-830: Display the sheet of a product
    @Given("User is on the homepage")
    public void userIsLoggedIn(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        this.loginPage.goToLoginPage();
        this.loginPage.login(rows.get(0).get("email"), rows.get(0).get("password"));
    }

    @When("User clicks on a product")
    public void userClicksOnAProduct() {
        homePage.displayProductSheet();
    }

    @Then("The product sheet should appear")
    public void theProductSheetShouldAppear() {
        assertTrue(homePage.displayProductDetails(), "This is not the product sheet");
    }

    // TEST_OF-831: Add product to cart
    @When("user selects {string}")
    public void userSelectProduct(String product) {
        homePage.selectArticle(product);
    }

    @And("he clicks on add to cart")
    public void heClicksOnAdd() {
        context.set(Context.PRODUCT_PRICE, homePage.getProductPrice());
        homePage.addToCart();
    }

    @Then("^he observes a pop pop which indicates the following message (.*)$")
    public void weObserveAPopPopWhichIndicatesTheFollowingMessageMessage(String message) {
        AssertJUnit.assertEquals(message, homePage.addedToCartMessage());
    }

    // TEST_OF-839: Delete product to card
    @And("User clicks on delete icon of {string}")
    public void userClicksOnDeleteIconOfProduct(String productName) {
        context.set(Context.PRODUCT_PRICE, homePage.getProductCartPrice(productName));
        context.set(Context.CART_TOTAL_PRICE, homePage.getTotalPriceCart());
        homePage.deleteProductCard(productName);
    }

    @Then("The product is not already visible in the card")
    public void theProductIsNotAlreadyVisibleInTheCard() {
        boolean result = homePage.isProductDeleted(context);
        System.out.println("le resultat est " + result);
        assertTrue(result);
    }

    //TEST_OF-902: Trash cart
    @And("User clicks on trash cart button")
    public void userClicksOnTrashCartButton() {
        homePage.emptyCart();
    }

    @Then("The cart should be empty")
    public void theCartShouldBeEmpty() {
        assertEquals(this.homePage.displayEmptycartMessage(), "Votre panier est vide", "The cart is not empty");
    }

    //TEST_OF-840: log out to account
    @When("User move to account icon and clicks on logout")
    public void userMoveToAccountIconAndClicksOnLogout() {
        homePage.logOut();
    }

    //TEST_OF-901: Test the order button
    @And("User clicks on order button")
    public void userClicksOnOrderButton() {
        homePage.validateOder();
    }

    @Then("The order validation pop-up is displaying")
    public void theOrderValidationPopUpIsDisplaying() {
        assertEquals(this.homePage.getTitleValidationOder(), "Valider votre commande", "it's not the page");
    }

    @And("user clicks on cart icon")
    public void userClicksOnCartIcon() {
        homePage.openCard();
    }

    @And("click on the + button to increase the quantity of this product in the cart {string}")
    public void clickOnTheButtonToIncreaseTheQuantityOfThisProductInTheCart(String pN) {
        homePage.increaseProductQuantity(pN);
    }

    @And("he sees the notification")
    public void heSeesTheNotification() {
        assertEquals("Votre panier à été mis à jour", homePage.addedToCartMessage());
    }


    @Then("we observe in the list that the basket is modified, the quantity of the product has increased")
    public void weObserveInTheListThatTheBasketIsModifiedTheQuantityOfTheProductHasIncreased() {
        assertTrue(homePage.isProductQuantityAdded((Double) context.get(Context.PRODUCT_PRICE)));
    }

    @And("he fills in the quantity of the product to add with + icons")
    public void heFillsInTheQuantityOfTheProductToAddWithIcons() {
        if (context.get(Context.PRODUCT_QUANTITY) != null)
            context.set(Context.PRODUCT_QUANTITY, ((int) context.get(Context.PRODUCT_QUANTITY)) + 2);
        else
            context.set(Context.PRODUCT_QUANTITY, 2);
        homePage.addProductQuantity();
    }

    @Then("user observes that the basket has not been updated {string}")
    public void weObserveThatTheBasketHasNotBeenUpdated(String productName) {
        assertFalse(homePage.isQuantityUpdated(productName, String.valueOf(context.get(Context.PRODUCT_QUANTITY)) ));
    }

    @Given("user knows the quantity of {string} in cart")
    public void userKnowsTheQuantityOfInCart(String arg0) {
        homePage.openCard();
        context.set(Context.PRODUCT_QUANTITY, Integer.parseInt(homePage.getProductQuantity(arg0)));
    }

    @Then("^Spawn a popup on the page with description of the (.*) and the (.*)$")
    public void spawnAPopupOnThePageWithDescriptionOfTheArticleAndThePrecise_price(String product, String price) {
        assertEquals(price, homePage.getPopupPrice(), "Prices are not matched");
        assertTrue(homePage.isDescription(), "No description provided");
    }

    @When("User clicks on the basket icon of the products {string}")
    public void userClicksOnTheBasketIconOfTheProducts(String arg0) {
        homePage.addToCartWithIcon(arg0);
    }

    @Then("User should see the sum total of the prices of the items in the cart")
    public void userShouldSeeTheSumTotalOfThePricesOfTheItemsInTheCart() {
        assertEquals(homePage.totalCartPriceCalculation(), homePage.getTotalPriceCart(), "Prices are not the same");
    }

    @And("user enters {string} quantity {string}")
    public void userEntersProductQuantity(String productName, String quantity) {
        homePage.fillProductQuantity(quantity);
        context.set(Context.PRODUCT_QUANTITY, (int) context.get(Context.PRODUCT_QUANTITY) + Integer.parseInt(quantity));
    }

    @And("user clicks {string} times on the \\(-) button to reduce the quantity of {string}")
    public void userClicksTimesOnTheButtonToReduceTheQuantityOfThisProduct(String number, String product) {
        homePage.decreaseProductQuantity(product, number, (int) context.get(Context.PRODUCT_QUANTITY));
    }

    @Then("we observe in the list that the basket is modified the {string} has been deleted or the {string} decreases")
    public void weObserveInTheListThatTheBasketIsModifiedTheProductHasBeenDeletedOrTheQuantityDecreases(String productName, String quantity) {
        assertTrue(homePage.isQuantityUpdated(productName, String.valueOf((int) context.get(Context.PRODUCT_QUANTITY) - Integer.parseInt(quantity)) ));
    }

    @And("User sends the order form")
    public void userSendsTheOrderForm() {
        homePage.submitOrderForm();
    }

    @Then("A required fields error message should appear under the fields")
    public void aRequiredFieldsErrorMessageShouldAppearUnderTheFields() {
        assertTrue(homePage.isOrderFormErrorMessage());
    }

    @And("^User fills fields with (.*), (.*), (.*), (.*) and (.*)$")
    public void userFillsFieldsWithCardNumberExpiredDateCvcAndAdresseDeLivraison(String cardNumber, String expirationDate, String cvc, String address, int methodeLivraison) {
        homePage.fillPaymentForm(cardNumber, expirationDate, cvc, address, methodeLivraison);
    }

    @Then("A validation confirmation message should appear on the screen")
    public void aValidationConfirmationMessageShouldAppearOnTheScreen() {
        assertTrue(homePage.isPaymentMessage());
    }
}
