package pages;

import models.Address;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AllureUtils;

import java.io.File;
import java.util.List;

import static org.testng.Assert.*;

public class ChangePersonalDataPage extends BasePage {
    private static final By PERSONAL_INFO = By.cssSelector(".rightcol");
    private static final By PERSONAL_NAME = By.cssSelector(".rec-change input");
    private static final By NAME_INPUT_AFTER_CLEAR = By.cssSelector(".rec-v input");
    private static final By SAVE_BUTTON = By.cssSelector(".button-small");
    private static final By SUCCESSFUL_SAVE = By.cssSelector(".c-green");
    private static final By BUTTON_UPLOAD_IMAGE = By.cssSelector("input[type=file]");
    private static final By AVATAR = By.cssSelector(".avatar-view");
    private static final By GO_TO_ADDRESS = By.xpath("//a[contains(text(), 'Адреса доставки')]");
    private static final By ADDRESS_FORM = By.cssSelector(".address-edit-frm");
    private static final By ZIP_CODE = By.id("i-zip");
    private static final By STREET_INPUT = By.id("i-street");
    private static final By NUMBER_HOUSE = By.id("i-house");
    private static final By NUMBER_APARTMENT = By.id("i-flat");
    private static final By NUMBER_ENTRANCE = By.id("i-entrance");
    private static final By NUMBER_STOREY = By.id("i-floor");
    private static final By ADD_ADDRESS = By.cssSelector(".button-small");
    private static final By SUCCESSFUL_ADD_ADDRESS = By.cssSelector(".attention-imp-p");
    private static final By ADDED_ADDRESSES = By.cssSelector(".b-addresses-list li");
    private static final By ADD_ANOTHER_ADDRESS_BUTTON = By.cssSelector(".b-addresses-add a");

    public ChangePersonalDataPage(WebDriver driver) {
        super(driver);
    }

    public ChangePersonalDataPage openPage() {
        isPageOpened();
        return this;
    }

    private void isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(PERSONAL_INFO));
        } catch (TimeoutException ex) {
            AllureUtils.takeScreenshot(driver);
            throw new TimeoutException("Страница не загрузилась");
        }
    }

    public ChangePersonalDataPage changePersonalNameAndSave(String personal_name) {
        driver.findElement(PERSONAL_NAME).clear();
        driver.findElement(NAME_INPUT_AFTER_CLEAR).sendKeys(personal_name);
        driver.findElement(SAVE_BUTTON).click();
        String result = wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFUL_SAVE)).getText();
        AllureUtils.takeScreenshot(driver);
        assertEquals(result, "Личные данные успешно сохранены!", "Данные не изменились");
        driver.navigate().refresh();
        return this;
    }

    public ChangePersonalDataPage checkPersonalName(String userName) {
        driver.navigate().refresh();
        String result = driver.findElement(PERSONAL_NAME).getAttribute("value");
        assertEquals(result, userName, "Имя не изменилось");
        return this;
    }

    public ChangePersonalDataPage uploadImage(String pathImg) {
        WebElement inputImg = driver.findElement(BUTTON_UPLOAD_IMAGE);
        File file = new File(pathImg);
        inputImg.sendKeys(file.getAbsolutePath());
        driver.findElement(SAVE_BUTTON).click();
        AllureUtils.takeScreenshot(driver);
        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(AVATAR)).isDisplayed(), "Не загрузилось изображение");
        return this;
    }

    public ChangePersonalDataPage goToAddressInfo() {
        driver.findElement(GO_TO_ADDRESS).click();
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    public ChangePersonalDataPage addShippingAddress(Address address) {
        if (driver.findElements(ADDED_ADDRESSES).size() > 0) {
            driver.findElement(ADD_ANOTHER_ADDRESS_BUTTON).click();
        }
        AllureUtils.takeScreenshot(driver);
        driver.findElement(ADDRESS_FORM).isDisplayed();
        driver.findElement(ZIP_CODE).sendKeys(address.getPostCode());
        driver.findElement(STREET_INPUT).sendKeys(address.getStreet());
        driver.findElement(NUMBER_HOUSE).sendKeys(address.getHouseNumber());
        driver.findElement(NUMBER_APARTMENT).sendKeys(address.getApartmentNumber());
        driver.findElement(NUMBER_ENTRANCE).sendKeys(address.getEntrance());
        driver.findElement(NUMBER_STOREY).sendKeys(address.getFloor());
        driver.findElement(ADD_ADDRESS).click();
        String result = wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFUL_ADD_ADDRESS)).getText();
        assertEquals("Адрес успешно добавлен\n" +
                "Позже, если захотите, вы сможете его удалить", result, "Произошла ошибка");
        driver.findElement(GO_TO_ADDRESS).click();
        return this;
    }

    public int checkAddressBeforeAdding() {
        List<WebElement> addresses = driver.findElements(ADDED_ADDRESSES);
        if (addresses.size() > 0) {
            assertNotEquals(addresses.size(), 0, "Нет сохраненных адресов");
            AllureUtils.takeScreenshot(driver);
        }
        return addresses.size();
    }

    public ChangePersonalDataPage checkAddressAfterAdding(int size) {
        List<WebElement> addressesAfterAdding = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ADDED_ADDRESSES));
        assertEquals(size + 1, addressesAfterAdding.size(), "Количество адресов до и после добавления не совпадают");
        return this;
    }
}