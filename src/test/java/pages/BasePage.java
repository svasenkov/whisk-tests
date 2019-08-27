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
            authorizationModal = $d("authentication-modal"),
            modalForm = $("#modal-root form");

    @Step("Verify user is logged in")
    public BasePage verifyIsLoggedIn() {
        avatarButton.shouldBe(visible);

        return this;
    }

    @Step("Verify user is not logged in")
    public void verifyNotLoggedIn() {
        authorizationModal.shouldBe(visible);
        verifyUrlContains("/auth/");
    }

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

    // todo get rid of such awful code, need more testid
    public SelenideElement getButtonByText(String text, SelenideElement parent) {
        return parent.$x(".//div[text()[contains(.,'" + text + "')]]" +
                "/../../div[@data-focusable='true']");
    }

    public SelenideElement getButtonByText(String text) {
        return $x("//div[text()[contains(.,'" + text + "')]]" +
                "/../../div[@data-focusable='true']");
    }

    public SelenideElement getLinkByText(String text, SelenideElement parent) {
        return parent.$x(".//*[text()='" + text + "']");
    }

    public SelenideElement getLinkByText(String text) {
        return $x("//*[text()='" + text + "']");
    }


    @Step("Click \"{text}\" button")
    public BasePage clickButton(String text, SelenideElement parent) {
        getButtonByText(text, parent).shouldBe(visible).click();

        return this;
    }

    @Step("Click \"{text}\" button")
    public BasePage clickButton(String text) {
        System.out.println(getButtonByText(text).parent());
        getButtonByText(text).shouldBe(visible).click();

        return this;
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

    @Step("Verify, form has text \"{text}\"")
    public BasePage verifyAuthorizationFormHasText(String text) {
        authorizationModal.shouldHave(text(text));

        return this;
    }

    // todo move to ModalBlock
    @Step("Verify, registration modal is opened")
    public BasePage verifyModalIsOpened() {
        verifyUrlContains("gb/");
        modalForm.shouldBe(visible);

        return this;
    }

    @Step("Verify, modal has text \"{text}\"")
    public BasePage verifyModalHasText(String text) {
         modalForm.shouldHave(text(text));

        return this;
    }

    @Step("Close modal")
    public BasePage close() {
        modalForm.$x(".//div[./*[@width='16px']]").click();

        return this;
    }
}
