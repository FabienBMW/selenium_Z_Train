package com.ztrain.steps;

import com.ztrain.pageObject.HomePage;
import com.ztrain.pageObject.LoginPage;
import com.ztrain.pageObject.RegistrationPage;
import com.ztrain.pageObject.SocialNetworkPage;
import io.cucumber.java.en.*;

import java.util.Locale;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class LoginSteps {

    private LoginPage   loginPage;
    private HomePage homepage;
    private RegistrationPage registrationPage;
    private SocialNetworkPage networkPage;

    public LoginSteps(LoginPage loginPage, HomePage homepage, RegistrationPage registrationPage, SocialNetworkPage networkPage) {
        this.loginPage = loginPage;
        this.homepage = homepage;
        this.registrationPage = registrationPage;
        this.networkPage = networkPage;
    }

    // TEST_OF-808: Verify successful login with credentials
    @Given("User is on the login page")
    public void userIsOnTheLoginPage() {
        loginPage.goToLoginPage();
    }

    @When("User is logging-in with {string} {string}")
    public void userIsLoggingInWithUsernamePassword(String email, String password) {
        loginPage.login(email, password);
    }

    @Then("User should be redirected to the home page")
    public void userShouldBeRedirectedToHomePage() {
        assertTrue(homepage.isZTrainLogoDisplayed(), "This is not the passage");
        homepage.saveScreenShotPNG();
    }

    // TEST_OF-807: Login and password field requiered
    @When("User click on connexion button")
    public void userClickOnConnexionButton() {
        loginPage.loginFieldRequired();
    }

    @Then("User should see and error message below the login field")
    public void userShouldSeeAndErrorMessageBelowTheLoginField() {
    assertEquals("Le format de l'email est invalid", this.loginPage.getErrorMessage());
    }

    //TEST_OF-799: Access to the registration page
    @When("User fill in {string} field and click on displayed password button")
    public void userFillInFieldAndClickOnDisplayedPasswordButton(String password) {
        loginPage.displayPwd(password);
    }

    @Then("The password is displayed in clear")
    public void thePasswordIsDisplayedInClear() {
        assertTrue(this.loginPage.isPasswordInputOfType("text"));
    }

    //TEST_OF-799: Access to the registration page
    @When("User clicks on the resgister link")
    public void userClicksOnTheResgisterLink() {
        this.loginPage.goToRegistrationPage();
    }

    @Then("User should be directed to the registration page")
    public void userShouldBeDirectedToTheRegistrationPage() {
        assertEquals(this.registrationPage.getRegisterTitle(), "Inscription", "kjdzl");
    }

    //TEST_OF8-810: Verify password reset
    @When("User clicks on forgot password link")
    public void userClicksOnForgotPasswordLink() {
        this.loginPage.goToResetPasswordPage();
    }

    @And("User fill in {string} and {string} and validate")
    public void userFillInAndAndValidate(String email, String newPassword) {
        this.loginPage.resetPassword(email, newPassword);
    }

    @Then("User should be redirected on login page")
    public void userShouldBeRedirectedOnLoginPage() {
        assertEquals(this.loginPage.loginpage(), "Connexion", "This is not the login page");
    }

    //TEST_OF8-829: Display products page
    @Then("The products page should display")
    public void theProductsPageShouldDisplay() {
        assertTrue(homepage.displayProductPage(),  "This is not product page");
    }

    @When("^The user click on (.*) icon$")
    public void theUserClickOnSocial_networkIcon(String socialNetwork) {
        loginPage.socialConnection();
    }

    @Then("^The user is redirected to a (.*)$")
    public void theUserIsRedirectedToASocial_network(String socialNetwork) {

        assertTrue(loginPage.getNewWindow(socialNetwork).contains(socialNetwork.toLowerCase()));
        networkPage.fillGoogleEmailField("bibo.inator@gmail.com");
        networkPage.fillPasswordField("G123456789");
    }

    @But("No change on the page")
    public void noChangeOnThePage() {
        assertTrue(homepage.isZTrainLogoDisplayed());
    }

    @Then("The user reads : {string}")
    public void theUserReads(String message) {
        assertTrue(loginPage.isWelcomeMessage(message));
    }

    @And("The user fill the email's field with value {string}")
    public void theUserFillTheEmailSFieldWithValue(String email) {
        loginPage.fillEmailField(email);
    }

    @When("the user clicks on the next field")
    public void theUserClicksOnTheNextField() {
        loginPage.clickOnPasswordField();
    }

    @Then("An error appears to report invalid email syntax")
    public void anErrorAppearsToReportInvalidEmailSyntax() {
        assertTrue(loginPage.isEmailError());
    }
}
