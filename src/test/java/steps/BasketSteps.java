package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.BasketPage;

public class BasketSteps {
    private BasketPage basket;

    public BasketSteps(WebDriver driver) {
        basket = new BasketPage(driver);
    }

    @Step("Delete Product From Basket")
    public BasketSteps deleteProduct(String name) {
        basket.deleteProduct(name);
        return this;
    }
}