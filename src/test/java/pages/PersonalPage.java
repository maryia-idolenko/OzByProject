package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AllureUtils;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class PersonalPage extends BasePage {
    private static final By USER_ACCOUNT = By.cssSelector(".b-user-card");
    private static final By GO_TO_PERSONAL_INFO = By.xpath("//a[contains(text(), 'Личные данные')]");
    private static final By GO_TO_BIRTHDAYS = By.xpath("//a[contains(text(), 'Дни рождения')]");
    private static final By GO_TO_FAVORITE = By.id("user-tab-wishlist");
    private static final By SEARCH_PRODUCT = By.id("top-s");
    private static final By GO_TO_BASKET = By.cssSelector(".top-panel__userbar__cart__item");
    private static final By PRODUCT_IN_FAVORITELIST = By.cssSelector(".viewer-type-card_has-filter li");
    private static final By PRODUCT_NAME = By.cssSelector(".item-type-card__title");
    private static final By DELETE_BUTTON = By.cssSelector(".item-type-card__controls-trigger");
    private static final By CONFIRM_DELETE = By.cssSelector(".item-type-card__controls-button");
    private static final By DELETE_PROGRESS = By.cssSelector(".item-type-card__controls-button-spinner");
    private static final By FAVORITELIST_INFO = By.cssSelector(".search-info-results__content");

    public PersonalPage(WebDriver driver) {
        super(driver);
    }

    public PersonalPage openPage() {
        driver.get("https://oz.by/personal/");
        isPageOpened();
        return this;
    }

    private void isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(USER_ACCOUNT));
        } catch (TimeoutException ex) {
            AllureUtils.takeScreenshot(driver);
            throw new TimeoutException("Страница не загрузилась");
        }
    }

    public ChangePersonalDataPage goToPersonalData() {
        driver.findElement(GO_TO_PERSONAL_INFO).click();
        ChangePersonalDataPage info = new ChangePersonalDataPage(driver);
        info.openPage();
        return info;
    }

    public BirthdayPage goToBirthdays() {
        driver.findElement(GO_TO_BIRTHDAYS).click();
        BirthdayPage birthday = new BirthdayPage(driver);
        birthday.openPage();
        return birthday;
    }

    public PersonalPage goToFavoriteList() {
        openPage();
        driver.findElement(GO_TO_FAVORITE).click();
        return this;
    }

    public ProductPage searchProduct(String name) {
        driver.findElement(SEARCH_PRODUCT).sendKeys(name);
        driver.findElement(SEARCH_PRODUCT).sendKeys(Keys.ENTER);
        return new ProductPage(driver);
    }

    public int countFavoriteProduct() {
        List<WebElement> list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCT_IN_FAVORITELIST));
        return list.size();
    }

    public PersonalPage deleteProductFromFavoriteList(String name) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCT_IN_FAVORITELIST));
        List<WebElement> list = driver.findElements(PRODUCT_IN_FAVORITELIST);
        for (WebElement product : list) {
            String productName = product.findElement(PRODUCT_NAME).getText();
            if (productName.equals(name)) {
                Actions act = new Actions(driver);
                act.moveToElement(product).build().perform();
                act.moveToElement(product.findElement(DELETE_BUTTON)).build().perform();
                act.click(product.findElement(DELETE_BUTTON)).build().perform();
                act.click(product.findElement(CONFIRM_DELETE)).build().perform();
                wait.until(ExpectedConditions.invisibilityOf(product.findElement(DELETE_PROGRESS)));
                wait.until(ExpectedConditions.visibilityOfElementLocated(FAVORITELIST_INFO));
            }
            break;
        }
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(FAVORITELIST_INFO));
        return this;
    }

    public PersonalPage countAfterDeleteProduct(int count) {
        List<WebElement> list = driver.findElements(PRODUCT_IN_FAVORITELIST);
        assertEquals(count - 1, list.size(), " Товар не удалился");
        return this;
    }

    public BasketPage goToBasket() {
        driver.findElement(GO_TO_BASKET).click();
        BasketPage basket = new BasketPage(driver);
        return basket;
    }
}