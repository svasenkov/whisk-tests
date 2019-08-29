package tests;

import helpers.TestBase;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.ShoppingListPage;
import pages.modal.AuthorizationModal;
import utils.ClipboardUtils;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static helpers.AttachmentHelper.attachAsText;
import static helpers.SelenideHelper.$d;
import static pages.BasePage.openUrlWithSkip;
import static utils.RandomUtils.getRandomEmail;

@Story("Share cookie receipt url (GUI)")
public class CookieReceiptTests extends TestBase {

    String sharedUrl;

    @Test( description = "Create receipt and save shared url")
    public void test00_createReceipt() {
        String randomEmail = getRandomEmail();

        openUrlWithSkip("/");
        new AuthorizationModal()
                .typeEmail(randomEmail)
                .clickLink("Continue");

        new ShoppingListPage().verifyIsLoggedInAs(randomEmail);

        $$(by("placeholder","Add item")).filter(visible).get(0).click();
        $d("autocomplete-content").$(byText("Milk")).click();
        $d("autocomplete-content").$(byText("Eggs")).click();
        $d("autocomplete-content").$(byText("Butter")).click();

        $$("#share-button").filter(visible).get(0).click();

        $d("onboarding-skip").shouldBe(visible).click();
        
        $$("#share-button").filter(visible).get(0).click();

        $(".popup-enter-done").shouldBe(visible)
                .$(byText("Get shareable link")).shouldBe(visible).click(); // why do we need this step?
        $(".popup-enter-done").shouldBe(visible)
                .$(byText("Copy link")).shouldBe(visible).click();

        sharedUrl = new ClipboardUtils().getContent();
        attachAsText("Text from clipboard", sharedUrl);
    }

    @Test( description = "Open shared url and check receipt")
    public void test01_verifySharedUrlHasReceipt() {
        String randomEmail = getRandomEmail();

        openUrlWithSkip(sharedUrl);
        new AuthorizationModal()
                .typeEmail(randomEmail)
                .clickLink("Continue");

        new ShoppingListPage().verifyIsLoggedInAs(randomEmail);

        $d("onboarding-skip").shouldBe(visible).click();

        $("#shopping-list").$(byText("Milk")).should(exist);
        $("#shopping-list").$(byText("Eggs")).should(exist);
        $("#shopping-list").$(byText("Butter")).should(exist);
        $("#shopping-list").$(byText("Onion")).should(exist);
    }

}
