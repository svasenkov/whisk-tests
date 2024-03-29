package helpers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.Level;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.EnvHelper.*;
import static org.openqa.selenium.logging.LogType.BROWSER;


public class SelenideHelper {
    static Logger logger = LoggerFactory.getLogger(SelenideHelper.class);

    public static final String SCREEN_RESOLUTION = "1360x768";

    public static void configureSelenide() {
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--disable-notifications");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(EnvHelper.browser);
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", isVideoOn);
        capabilities.setCapability("screenResolution", SCREEN_RESOLUTION + "x24");
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        Configuration.browser = EnvHelper.browser;
        Configuration.baseUrl = url;
        Configuration.browserSize = SCREEN_RESOLUTION;
        Configuration.headless = isHeadless;
        Configuration.timeout = 5000;
        Configuration.browserCapabilities = capabilities;
        if (isSelenoid) {
            Configuration.remote = selenoidUrl + "/wd/hub/";
        }

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(false));

        logger.info(
            "headless: " + Configuration.headless + "\n" +
            "selenoid: " + isSelenoid + "\n" +
            "selenoidUrl: " + selenoidUrl + "\n" +
            "video: " + isVideoOn);
    }

    public static String getConsoleLogs() {
        return String.join("\n", Selenide.getWebDriverLogs(BROWSER));
    }

    public static String getSessionId() {
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    }

    public static SelenideElement $d(String role) {
        return $(by("data-testid", role));
    }
}
