package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.ProductPage;

public class ProductSteps {
    private ProductPage product;

    public ProductSteps(WebDriver driver) {
        product = new ProductPage(driver);
    }

    @Step("Get Count Product in the Basket Before Adding")
    public int countProductBefore() {
        return product.countBeforeAddProduct();
    }

    @Step("Choice Any Product in List")
    public ProductSteps choiceProduct(String name) {
        product.isPageOpened();
        product.choiceProduct(name);
        return this;
    }

    @Step("Check Count Products After Adding")
    public ProductSteps checkProductCount(int count) {
        product.addProductAndCheckCountAfterAdding(count);
        return this;
    }

    @Step("Count Product Before Adding to FavoriteList")
    public int countBeforeAddToFavoriteList() {
        return product.countBeforeAddInFavoriteList();
    }

    @Step("Count Product After Adding in FavoriteList")
    public ProductSteps validationProductCountInFavoriteList(int count) {
        product.addToFavoriteListAndCheckCountAfter(count);
        return this;
    }
}