package pages.modal;

import io.qameta.allure.Step;
import pages.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.by;


public class AuthorizationModal extends BasePage {

    @Override
    @Step("Click \"{text}\" link")
    public BasePage clickLink(String text) {
        getLinkByText(text, authorizationModal).shouldBe(visible).click();

        return this;
    }

    @Step ("Type email \"{text}\"")
    public AuthorizationModal typeEmail(String text) {
        authorizationModal.$(by("data-testid","input")).shouldBe(visible).val(text);

        return this;
    }
}
