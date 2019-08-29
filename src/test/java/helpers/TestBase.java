package helpers;

import io.qameta.allure.Step;
import org.testng.annotations.*;

import static com.codeborne.selenide.Selenide.close;
import static helpers.EnvHelper.loadEnvironment;
import static helpers.SelenideHelper.configureSelenide;

@Listeners(TestsListener.class)
public class TestBase {
    public String DEFAULT_EMAIL = "",
                  DEFAULT_PASSWORD = "",

                  DEFAULT_GOOGLE_EMAIL = "testwhisk7@gmail.com",
                  DEFAULT_GOOGLE_PASSWORD = "testwhisk%7#1",

                  DEFAULT_FACEBOOK_EMAIL = "whiskautotest@gmail.com",
                  DEFAULT_FACEBOOK_PASSWORD = "asd55441";

    @BeforeSuite
    @Step("Setup test environment for UI tests")
    public void setUp() {
        loadEnvironment();
        configureSelenide();
    }

//    @AfterTest(alwaysRun = true)
//    @Step("Verify no errors in browser console")
//    public void verifyNoConsoleErrors(){
//        String consoleText = getConsoleLogs();
//        attachBrowserConsoleLogs();
//        assertFalse(consoleText.contains("SEVERE"), consoleText);
//    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        AttachmentHelper.attachCiInfoToReport();
        close();
    }
}



