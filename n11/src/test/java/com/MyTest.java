package com;

import org.junit.Test;

public class MyTest extends ChromeDriverSetUp {

    @Test
    public void Ebay_product_add_to_watch() {
        try {
            new MainPage(driver)
                    .checkThePageCorrect("https://www.ebay.com/")
                    .selectOptionFromDropdown("Books")
                    .searchIt()
                    .checkTheError("Top level category browsing is not allowed. Please provide keywords or more filters for the applied top level category.")
                    .searchProduct("Lord of the rings")
                    .chooseTheFirstOne()
                    .collectPriceAndAssigneToVar()
                    .addToWatchList()
                    .login("canerDenemeKullanici","canerDenemeSifre")
                    .justWait();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
