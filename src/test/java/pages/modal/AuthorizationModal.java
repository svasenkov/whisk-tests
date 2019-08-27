package pages.modal;

import io.qameta.allure.Step;
import pages.BasePage;

import static com.codeborne.selenide.Condition.visible;


public class AuthorizationModal extends BasePage {

    @Override
    @Step("Click \"{text}\" link")
    public BasePage clickLink(String text) {
        getLinkByText(text, authorizationModal).shouldBe(visible).click();

        return this;
    }
}
