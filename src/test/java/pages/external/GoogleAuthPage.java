package pages.external;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


public class GoogleAuthPage extends BasePage {
    private SelenideElement emailInput = $("#identifierId"),
                            passwordInput = $(byName("password")),
                            nextButton = $x("//div[@id='identifierNext']|" +
            "//div[@id='passwordNext']");

    @Step ("Type email \"{text}\"")
    public GoogleAuthPage typeEmail(String text) {
        emailInput.shouldBe(visible).val(text);
        System.out.println(text);

        return this;
    }

    @Step ("Type password \"{text}\"")
    public GoogleAuthPage typePassword(String text) {
        passwordInput.shouldBe(visible).val(text);
        System.out.println(text);

        return this;
    }

    @Step ("Click \"Next\"")
    public GoogleAuthPage clickNext() {
        nextButton.shouldBe(visible).click();

        return this;
    }
}
