package pages;

import io.qameta.allure.Step;


public class ShoppingListPage extends BasePage {

    @Step("Verify \"Shopping List\" page is opened")
    public void verifyPageIsOpened() {
        verifyUrlContains("shopping-list");
    }



}
