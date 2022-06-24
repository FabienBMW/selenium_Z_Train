package com.ztrain.steps;

import com.ztrain.pageObject.RegisterPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.AssertJUnit.assertTrue;

public class RegisterSteps {
    private RegisterPage registerPage;

    public RegisterSteps(RegisterPage registerPage) {
        this.registerPage = registerPage;
    }

    @Given("User is on the register page")
    public void userIsOnTheLoginPage() {
        registerPage.goToRegisterPage();
    }

    @And("The user fill {string}, {string} fields")
    public void theUserFillFields(String email, String password) {
        registerPage.fillEmailField(email);
        registerPage.fillPasswordField(password);
    }

    @And("the user leaves the {string} field blank")
    public void theUserLeavesTheFieldBlank(String arg0) {
        
    }

    @When("The user click on Inscription button")
    public void theUserClickOnInscriptionButton() {
        registerPage.submitForm();
    }

    @Then("user receives error message due to empty confirm password field")
    public void userReceivesErrorMessageDueToEmptyConfirmPasswordField() {
        assertTrue(registerPage.isFormError());
    }
}
