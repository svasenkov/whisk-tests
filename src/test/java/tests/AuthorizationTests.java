package tests;

import helpers.TestBase;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.ShoppingListPage;
import pages.external.FacebookAuthPage;
import pages.external.GoogleAuthPage;
import pages.modal.AuthorizationModal;

import static com.codeborne.selenide.Selenide.switchTo;
import static pages.BasePage.openUrlWithSkip;
import static utils.RandomUtils.getRandomEmail;

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
//    public void test01_authorizationWithGoogle() {
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
//        new ShoppingListPage().verifyIsLoggedInAs(DEFAULT_GOOGLE_EMAIL);
//    }

    @Test( description = "Successful login with facebook account")
    public void test02_loginWithFacebook() {
        openUrlWithSkip("/");
        new AuthorizationModal()
                .clickLink("Continue with Facebook");
        switchTo().window(1);

        new FacebookAuthPage()
                .typeEmail(DEFAULT_FACEBOOK_EMAIL)
                .typePassword(DEFAULT_FACEBOOK_PASSWORD)
                .clickLogin()
                .clickOk();

        switchTo().window(0);

        new ShoppingListPage().verifyIsLoggedInAs(DEFAULT_FACEBOOK_EMAIL);
    }

}
