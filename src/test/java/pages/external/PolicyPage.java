package pages.external;

import io.qameta.allure.Step;
import pages.BasePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;


public class PolicyPage extends BasePage {

    @Step("Verify \"Privacy policy\" page is opened")
    public void verifyPageIsOpened() {
        verifyUrlContains("policy/privacy");
        $("html").shouldHave(text("This privacy notice (our \"Policy\") describes how Foodient Ltd."));
    }
}
