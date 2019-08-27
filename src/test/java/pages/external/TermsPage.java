package pages.external;

import io.qameta.allure.Step;
import pages.BasePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;


public class TermsPage extends BasePage {

    @Step("Verify \"Terms\" page is opened")
    public void verifyPageIsOpened() {
        verifyUrlContains("policy/terms");
        $("html").shouldHave(text("These terms of service and the documents referred to in them"));
    }
}
