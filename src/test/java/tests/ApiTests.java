package tests;

import com.google.gson.JsonObject;
import helpers.TestBase;
import io.qameta.allure.Story;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ShoppingListPage;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static pages.BasePage.openUrl;
import static utils.RandomUtils.getRandomEmail;

@Story("Authorization tests (API)")
public class ApiTests extends TestBase {

//    @Test( description = "Successful login from landing page")
//    public void test00_loginFromApi() {
//        String sessionToken = getLoginSessionId(DEFAULT_EMAIL, DEFAULT_PASSWORD);
//        openUrl("/homepage/staging/favicon.ico");
//
//        Cookie ck = new Cookie("sessionToken", sessionToken);
//        getWebDriver().manage().addCookie(ck);
//
//        openUrl("/gb/jobs/london");
//        new JobsPage().verifyIsLoggedIn();
//    }

    @Test( description = "Successful login from landing page")
    public void test02_registerFromApi() {
        String randomEmail = getRandomEmail();
        String sessionToken = getRegisterSessionId(randomEmail);
        openUrl("/robots.txt");

        Cookie ck = new Cookie("dev.whisk.USER_TOKEN", sessionToken);
        getWebDriver().manage().addCookie(ck);

        openUrl("/");
        new ShoppingListPage().verifyIsLoggedInAs(randomEmail);
    }

//    private String getLoginSessionId(String login, String password) {
//
//        JsonObject data = new JsonObject();
//        data.addProperty("email", login);
//        data.addProperty("password", password);
//
//        Response response = RestAssured
//                .given()
//                .filter(new AllureRestAssured())
//                .contentType(ContentType.JSON)
//                .body(data.toString())
//                .when()
//                .post("")
//                .then()
//                .extract()
//                .response();
//
//        Assert.assertEquals(response.getStatusCode(), 200);
//
//        return response.path("data.sessionToken");
//    }

    private String getRegisterSessionId(String login) {

        JsonObject data = new JsonObject();
        data.addProperty("email", login);

        Response response1 = RestAssured
                .given()
                .filter(new AllureRestAssured())
                .get("https://dev.whisk.com")
                .then()
                .extract()
                .response();

        Response response = RestAssured
                .given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(data.toString())
                .when()
                .post("https://login-dev.whisk.com/auth/create/quick")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.getStatusCode(), 200);

        return response.path("token.access_token");
    }
}
