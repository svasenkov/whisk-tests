package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static helpers.SelenideHelper.$d;
import static org.testng.Assert.assertTrue;


public class BasePage {
    public SelenideElement avatarButton = $d("avatar-button"),
            authorizationModal = $d("authentication-modal");

    @Step("Open url \"{url}\"")
    public static void openUrl(String url) {
        open(url);
    }

    @Step("Open url \"{url}\" with skip Tour modal")
    public static void openUrlWithSkip(String url) {
        open(url);

        if ($d("onboarding-skip").exists()) {
            $d("onboarding-skip").click();
        }
    }

    @Step("Verify user is logged in as \"{text}\"")
    public BasePage verifyIsLoggedInAs(String text) {
        avatarButton.shouldBe(visible).shouldHave(text(text));

        return this;
    }

    public SelenideElement getLinkByText(String text, SelenideElement parent) {
        return parent.$x(".//*[text()='" + text + "']");
    }

    public SelenideElement getLinkByText(String text) {
        return $x("//*[text()='" + text + "']");
    }

    @Step("Click \"{text}\" link")
    public BasePage clickLink(String text, SelenideElement parent) {
        getLinkByText(text, parent).shouldBe(visible).click();

        return this;
    }

    @Step("Click \"{text}\" link")
    public BasePage clickLink(String text) {
        getLinkByText(text).shouldBe(visible).click();

        return this;
    }

    @Step("Current url should contain \"{text}\"")
    public void verifyUrlContains(String text) {
        assertTrue(url().contains(text),
                "Current url \"" + url() + "\" doesn't contain " + text);
    }

}
