package tests;

import helpers.TestBase;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.ShoppingListPage;
import pages.blocks.TopbarBlock;
import pages.external.GoogleAuthPage;
import pages.modal.AuthorizationModal;

import static com.codeborne.selenide.Selenide.switchTo;
import static pages.BasePage.openUrl;
import static pages.BasePage.openUrlWithSkip;
import static utils.RandomUtils.getRandomEmail;
import static utils.RandomUtils.getRandomString;

@Story("Authorization tests (GUI)")
public class AuthorizationTests extends TestBase {

    @Test( description = "Successful registration with new email")
    public void test00_registrationWithNewEmail() {
        String randomEmail = getRandomEmail();

        openUrlWithSkip("/");
        new AuthorizationModal()
                .typeEmail(randomEmail)
                .clickLink("Continue");

        new ShoppingListPage().verifyIsLoggedInAs(randomEmail);
    }

//    @Test( description = "Successful authorization with google account")
//    public void test00_authorizationWithGoogle() {
//        openUrlWithSkip("/");
//        new AuthorizationModal()
//                .clickLink("Continue with Google");
//        switchTo().window(1);
//
//        new GoogleAuthPage()
//                .typeEmail(DEFAULT_GOOGLE_EMAIL)
//                .clickNext()
//                .typePassword(DEFAULT_GOOGLE_PASSWORD)
//                .clickNext();
//
//        switchTo().window(0);
//
//        new BasePage().verifyIsLoggedIn();
//        new ShoppingListPage().verifyPageIsOpened();
//    }

}
