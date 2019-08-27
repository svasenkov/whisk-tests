package tests;

import helpers.TestBase;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.external.TermsPage;
import pages.modal.AuthorizationModal;

import static com.codeborne.selenide.Selenide.switchTo;
import static pages.BasePage.openUrlWithSkip;

@Story("Terms tests (GUI)")
public class TermsTests extends TestBase {

    @Test( description = "Terms page should be opened from Authorization modal")
    public void test00_termsPageShouldBeOpened() {
        openUrlWithSkip("/");
        new AuthorizationModal().clickLink("Terms");
        switchTo().window(1);
        new TermsPage().verifyPageIsOpened();
    }
}
