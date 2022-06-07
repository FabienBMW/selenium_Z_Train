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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
        homePage.addToCart();
    }

    @Then("^he observes a pop pop which indicates the following message (.*)$")
    public void weObserveAPopPopWhichIndicatesTheFollowingMessageMessage(String message) {
        AssertJUnit.assertEquals(message, homePage.addedToCartMessage());
    }

    // TEST_OF-839: Delete product to card
    @And("User clicks on delete icon of product")
    public void userClicksOnDeleteIconOfProduct() {
        context.set(Context.PRODUCT_PRICE, homePage.getProductPrice());
        context.set(Context.CART_TOTAL_PRICE, homePage.getTotalPriceCart());
        homePage.deleteProductCard();
    }

    @Then("The product is not already visible in the card")
    public void theProductIsNotAlreadyVisibleInTheCard() {

        assertTrue(homePage.isProductDeleted(context));
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
    @And("User clicks on oder button")
    public void userClicksOnOderButton() {
        homePage.validateOder();
    }

    @Then("The oder validation pop-up is displaying")
    public void theOderValidationPopUpIsDisplaying() {
        assertEquals(this.homePage.getTitleValidationOder(), "Valider votre commande", "it's not the page");
    }

    @And("user clicks on cart icon")
    public void userClicksOnCartIcon() {
        homePage.openCard();
    }
}
