package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class ProductPage extends BasePage {
    private static final By PRODUCT_LIST = By.cssSelector(".viewer-type-card_has-filter .viewer-type-card__li");
    private static final By PRODUCT_NAME = By.cssSelector(".item-type-card__title");
    private static final By PRODUCT_TITLE = By.cssSelector(".b-product-title__heading h1");
    private static final By ADD_PRODUCT_TO_BASKET = By.cssSelector(".b-product-control__button");
    private static final By COUNTER_BASKET = By.cssSelector(".top-panel__userbar__cart__count");
    private static final By PRODUCT_STATUS = By.cssSelector(".b-product-action__text_out");
    private static final By COUNTER_PRODUCT_FAVORITELIST = By.cssSelector(".top-panel__userbar__favs__count");
    private static final By ADD_TO_FAVORITELIST = By.xpath("//*[@id=\"top-page\"]/div[3]/div/div[1]/div/div[1]/div[2]/div[3]/div/div/label");
    private static final By ADD_INDICATOR = By.cssSelector(".b-product-action__spinner");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage isPageOpened() {
        return this;
    }

    public int countBeforeAddProduct() {
        int countBefore;
        if (driver.findElement(COUNTER_BASKET).getText().equals("")) {
            countBefore = 0;
        } else {
            countBefore = Integer.parseInt(driver.findElement(COUNTER_BASKET).getText());
        }
        return countBefore;
    }

    public ProductPage choiceProduct(String name) {
        List<WebElement> products = driver.findElements(PRODUCT_LIST);
        for (WebElement a : products) {
            String nameProduct = a.findElement(PRODUCT_NAME).getText();
            if (nameProduct.equals(name)) {
                Actions act = new Actions(driver);
                act.moveToElement(a).build().perform();
                act.click(a).build().perform();
            }
            break;
        }
        driver.findElement(PRODUCT_TITLE).isDisplayed();
        return this;
    }

    public void addProductAndCheckCountAfterAdding(int count) {
        driver.findElement(ADD_PRODUCT_TO_BASKET).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(COUNTER_BASKET));
        driver.navigate().refresh();
        int countProductAfter = Integer.parseInt(driver.findElement(COUNTER_BASKET).getText());
        if (count == 1) {
            assertEquals(count, countProductAfter, "Количество добавленных товаров не равно количеству товаров в корзине");
        } else {
            assertEquals(count + 1, countProductAfter, "Количество добавленных товаров не равно количеству товаров в корзине");
        }
    }

    public int countBeforeAddInFavoriteList() {
        int countWishListBefore;
        if (driver.findElement(COUNTER_PRODUCT_FAVORITELIST).getText().equals("")) {
            countWishListBefore = 0;
        } else {
            countWishListBefore = Integer.parseInt(driver.findElement(COUNTER_PRODUCT_FAVORITELIST).getText());
        }
        return countWishListBefore;
    }

    public void addToFavoriteListAndCheckCountAfter(int count) {
        driver.findElement(ADD_TO_FAVORITELIST).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(ADD_INDICATOR));
        String status = wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_STATUS)).getText();
        assertEquals(status, "В избранном", "Товар в избранное не добавился");
        driver.navigate().refresh();
        int countAfter = Integer.parseInt(wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(COUNTER_PRODUCT_FAVORITELIST))).getText());
        assertEquals(count + 1, countAfter, "Количество добавленных товаров не равно количеству товаров в корзине");
    }
}