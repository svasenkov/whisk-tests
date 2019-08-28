package helpers;

import static java.lang.Boolean.parseBoolean;

public class EnvHelper {
      private static final String
            DEFAULT_URL = "dev.whisk.com",
            DEFAULT_SELENOID_URL = "0.0.0.0",
            DEFAULT_BROWSER = "chrome",
            DEFAULT_LANGUAGE = "ru",
            DEFAULT_HEADLESS = "false",
            DEFAULT_SELENOID = "false",
            DEFAULT_VIDEO = "false";

    public static String
            url,
            browser,
            language,
            selenoidUrl;
    public static boolean
            isHeadless,
            isSelenoid,
            isVideoOn;

    public static void loadEnvironment() {
        url = "https://" + System.getProperty("url", DEFAULT_URL);
        selenoidUrl = "http://" + System.getProperty("selenoid_url", DEFAULT_SELENOID_URL) + ":4444";
        browser = System.getProperty("browser", DEFAULT_BROWSER);
        language = System.getProperty("language", DEFAULT_LANGUAGE);
        isHeadless = parseBoolean(System.getProperty("headless", DEFAULT_HEADLESS));
        isSelenoid = parseBoolean(System.getProperty("selenoid", DEFAULT_SELENOID));
        isVideoOn = parseBoolean(System.getProperty("video", DEFAULT_VIDEO));
    }

    public static String getEnvPropertiesForAllure() {
        return "URL: " + url + "\n" +
               "language: " + language;
    }
}
