package pages.blocks;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;


public class TopbarBlock extends BasePage {
    private SelenideElement blockDiv = $x("//div[contains(@class, '__TopbarDesktopLayout___topbar')]|" +
            "//div[contains(@class, 'Header-nav')]");

    @Override
    @Step("Click \"{text}\" link")
    public BasePage clickLink(String text) {
        getLinkByText(text, blockDiv).shouldBe(visible).click();

        return this;
    }
}
