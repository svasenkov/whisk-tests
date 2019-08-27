package helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Boolean.parseBoolean;

public class EnvHelper {
    static Logger logger = LoggerFactory.getLogger(EnvHelper.class);

    public static final String
            ALLURE_ENV_PROPERTIES_LOCATION = "build/allure-results/environment.properties",
            DEFAULT_URL = "dev.whisk.com",
            DEFAULT_LANGUAGE = "en",
            DEFAULT_BROWSER = "chrome",
            DEFAULT_HEADLESS = "false",
            DEFAULT_SELENOID = "false",
            DEFAULT_VIDEO = "false";

    public static String
            url,
            language,
            browser,
            selenoid_url;

    public static boolean
            isHeadless,
            isSelenoid,
            isVideoOn;


    public static void loadEnvironment() {
        url = "https://" + System.getProperty("url", DEFAULT_URL);
        selenoid_url = "https://" + System.getProperty("url", DEFAULT_URL);
        language = System.getProperty("language", DEFAULT_LANGUAGE);
        browser = System.getProperty("browser", DEFAULT_BROWSER);
        isHeadless = parseBoolean(System.getProperty("headless", DEFAULT_HEADLESS));
        isSelenoid = parseBoolean(System.getProperty("selenoid", DEFAULT_SELENOID));
        isVideoOn = parseBoolean(System.getProperty("video", DEFAULT_VIDEO));
        selenoid_url = "http://" + System.getProperty("selenoid_video_url") + ":4444";
    }

    public static String getEnvPropertiesForAllure() {
        return "URL: " + url + "\n" +
               "language: " + language;
    }
}
