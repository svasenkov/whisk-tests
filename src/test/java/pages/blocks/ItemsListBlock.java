package pages.blocks;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static helpers.SelenideHelper.$d;


public class ItemsListBlock extends BasePage {
    private SelenideElement itemsListBlock = $d("autocomplete-content");

    @Override
    @Step("Click \"{text}\" link")
    public BasePage clickLink(String text) {
        getLinkByText(text, itemsListBlock).shouldBe(visible).click();

        return this;
    }
}
