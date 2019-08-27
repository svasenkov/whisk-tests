package tests;

import helpers.TestBase;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.external.PolicyPage;
import pages.modal.AuthorizationModal;

import static com.codeborne.selenide.Selenide.switchTo;
import static pages.BasePage.openUrlWithSkip;

@Story("Privacy policy tests (GUI)")
public class PrivacyPolicyTests extends TestBase {

    @Test( description = "Privacy Policy page should be opened from Authorization modal")
    public void test00_termsPageShouldBeOpened() {
        openUrlWithSkip("/");
        new AuthorizationModal().clickLink("Privacy Policy");
        switchTo().window(1);
        new PolicyPage().verifyPageIsOpened();
    }
}
