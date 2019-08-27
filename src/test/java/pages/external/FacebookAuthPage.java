package pages.external;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


public class FacebookAuthPage extends BasePage {
    private SelenideElement emailInput = $("#email"),
                            passwordInput = $("#pass"),
                            loginButton = $("#loginbutton"),
                            okButton = $(byName("__CONFIRM__"));

    @Step ("Type email \"{text}\"")
    public FacebookAuthPage typeEmail(String text) {
        emailInput.shouldBe(visible).val(text);
        System.out.println(text);

        return this;
    }

    @Step ("Type password \"{text}\"")
    public FacebookAuthPage typePassword(String text) {
        passwordInput.shouldBe(visible).val(text);
        System.out.println(text);

        return this;
    }

    @Step ("Click \"Log In\"")
    public FacebookAuthPage clickLogin() {
        loginButton.shouldBe(visible).click();

        return this;
    }

    @Step ("Click \"Ok\"")
    public FacebookAuthPage clickOk() {
        if(okButton.exists()) { // sometimes it appears
            okButton.shouldBe(visible).click();
        }
        return this;
    }
}
