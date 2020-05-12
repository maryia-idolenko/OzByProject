package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AllureUtils;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class BasketPage extends BasePage {
    private static final By PRODUCT_LIST = By.xpath("//div[contains(@class, 'i-layout-column_full')]//tr[contains(@class, 'goods-table__row ')]");
    private static final By PRODUCT_NAME = By.xpath("//div[@class='goods-table-cell__link']/a");
    private static final By PRODUCT_CHECKBOX = By.xpath("//div[@class='goods-table-cell']//input");
    private static final By DELETE_CONFIRMATION = By.cssSelector(".goods-table-popup_visible .remove-yes");
    private static final By EMPTY_BASKET = By.cssSelector(".i-textual__plain");
    private static final By DELETE_BUTTON = By.xpath("//div[contains(@class,'i-layout-column_full')]//button[contains(@class, 'remove')]");
    private static final By CHECK_FOR_DELETE_BUTTON = By.xpath("//div[contains(@class, 'i-layout-column_full')]//" + "tr[contains(@class, 'goods-table__row_footer')]//span[@class ='quantity-help-text']");
    private static final By INFO_MODAL_LIST = By.cssSelector(".i-popover__footer");
    private static final By POP_UP_INFO2 = By.xpath("//div[contains(@class, 'deal-form-main__popover_top')]//button");

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public BasketPage openPage() {
        isPageOpened();
        return this;
    }

    private void isPageOpened() {
    }

    public BasketPage deleteProduct(String name) {
        List<WebElement> list = driver.findElements(PRODUCT_LIST);
        for (WebElement product : list) {
            String productName = product.findElement(PRODUCT_NAME).getText();
            if (productName.equals(name)) {
                product.findElement(PRODUCT_CHECKBOX).click();
                if (driver.findElements(INFO_MODAL_LIST).size() > 2) {
                    driver.findElement(POP_UP_INFO2).click();
                }
                wait.until(ExpectedConditions.textToBePresentInElementLocated(CHECK_FOR_DELETE_BUTTON, "Выбрано"));
                WebElement firstButton = wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(DELETE_BUTTON)));
                firstButton.click();
                WebElement confButton = wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(DELETE_CONFIRMATION)));
                confButton.click();
                break;
            }
        }
        driver.navigate().refresh();
        driver.findElement(EMPTY_BASKET).isDisplayed();
        return this;
    }

    public BasketPage checkProductsAfterDeleting(int count) {
        List<WebElement> list = driver.findElements(PRODUCT_LIST);
        if (list.size() == 0) {
            driver.findElement(EMPTY_BASKET).isDisplayed();
        } else {
            assertEquals(count - 1, list.size(), "Товар из корзины не удалился");
            AllureUtils.takeScreenshot(driver);
        }
        return this;
    }
}