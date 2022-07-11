package com.ztrain.steps;

import com.ztrain.pageObject.FavoritePage;
import io.cucumber.java.en.Then;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class MyFavoriteSteps {
    private FavoritePage myFavoritePage;
    
    public MyFavoriteSteps(FavoritePage myFavoritePage) {
        this.myFavoritePage = myFavoritePage;
    }
    
    @Then("The user must see the products displayed {string} et {string}")
    public void theUserMustSeeTheProductsDisplayedEt(String arg0, String arg1) {
        assertTrue(myFavoritePage.isInWishlist(arg0));
        assertTrue(myFavoritePage.isInWishlist(arg1));
    }

    @Then("The user should see a message of absence of products {string}")
    public void theUserShouldSeeAMessageOfAbsenceOfProductsMessage(String message) {
        assertEquals(message, myFavoritePage.getEmptyMessage());
    }
}
