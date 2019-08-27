package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.blocks.RegistrationBaseBlock;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;


public class ShoppingListPage extends BasePage {

    @Step("Verify \"Shopping List\" page is opened")
    public void verifyPageIsOpened() {
        verifyUrlContains("shopping-list");
    }



}
