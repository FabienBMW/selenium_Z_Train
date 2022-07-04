package com.ztrain.pageObject;

public class FavoritePage extends Page {

    public void goToFavoritePage() {
        driver.get(ENV.getUrl("/myfavorite"));
    }
}
