package pages;

import models.Birthday;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utils.AllureUtils;

import java.util.List;

public class BirthdayPage extends BasePage {
    private static final By BIRTHDAY_PAGE = By.id("birthdays-list-container");
    private static final By BIRTHDAY_CARDS = By.cssSelector(".edit-birthday");
    private static final By ADD_BIRTHDAY = By.cssSelector(".add-birthday");
    private static final By BIRTHDAY_FORM = By.cssSelector(".i-popup-birthday");
    private static final By NAME = By.name("birthday[name]");
    private static final By PHONE = By.name("birthday[phone]");
    private static final By B_DAY = By.name("birthday[date]");
    private static final By B_MONTH = By.name("birthday[month]");
    private static final By B_YEAR = By.name("birthday[year]");
    private static final By FEMALE = By.xpath("//input[@value='female']/../..");
    private static final By MALE = By.xpath("//input[@value='male']/../..");
    private static final By COMMENT = By.name("birthday[note]");
    private static final By ADD_BIRTHDAY_BUTTON = By.className("i-popup__footer-accent");
    private static final By B_NAME_CARD = By.cssSelector(".birthday-card__name");
    private static final By EDIT_FORM = By.cssSelector(".i-popup-birthday");
    private static final By DELETE_BIRTHDAY = By.cssSelector(".delete-birthday");
    private static final By DELETE_CONFIRM = By.cssSelector(".delete-birthday-confirmed");
    private static final By POPUP_CONFIRM = By.cssSelector(".i-popup__line_delete");

    public BirthdayPage(WebDriver driver) {
        super(driver);
    }

    public BirthdayPage openPage() {
        isPageOpened();
        return this;
    }

    private void isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(BIRTHDAY_PAGE));
        } catch (TimeoutException ex) {
            AllureUtils.takeScreenshot(driver);
            throw new TimeoutException("Страница не загрузилась");
        }
    }

    public int countBirthdayCards() {
        List<WebElement> card = driver.findElements(BIRTHDAY_CARDS);
        return card.size();
    }

    public BirthdayPage addBirthday(Birthday user) {
        driver.findElement(ADD_BIRTHDAY).click();
        driver.findElement(BIRTHDAY_FORM).isDisplayed();
        driver.findElement(NAME).sendKeys(user.getName());
        driver.findElement(PHONE).sendKeys(user.getTelephone());
        driver.findElement(B_DAY).click();
        Select selectDay = new Select(driver.findElement(B_DAY));
        selectDay.selectByValue(user.getDay());
        driver.findElement(B_MONTH).click();
        Select selectMonth = new Select(driver.findElement(B_MONTH));
        selectMonth.selectByVisibleText(user.getMonth());
        driver.findElement(B_YEAR).click();
        Select selectYear = new Select(driver.findElement(B_YEAR));
        selectYear.selectByVisibleText(user.getYear());
        driver.findElement(B_YEAR).click();
        if (user.getGender().equals("Женский")) {
            driver.findElement(FEMALE).click();
        } else {
            driver.findElement(MALE).click();
        }
        driver.findElement(COMMENT).sendKeys(user.getComment());
        driver.findElement(ADD_BIRTHDAY_BUTTON).click();
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    public BirthdayPage deleteBirthdayCard(String name) {
        List<WebElement> birthdayCards = driver.findElements(BIRTHDAY_CARDS);
        for (WebElement card : birthdayCards) {
            String birthdayName = card.findElement(B_NAME_CARD).getText();
            if (birthdayName.equals(name)) {
                wait.until(ExpectedConditions.elementToBeClickable(card)).click();
                driver.findElement(EDIT_FORM).isDisplayed();
                driver.findElement(DELETE_BIRTHDAY).click();
                AllureUtils.takeScreenshot(driver);
                wait.until(ExpectedConditions.visibilityOfElementLocated(POPUP_CONFIRM)).isDisplayed();
                WebElement confirmDelete = driver.findElement(DELETE_CONFIRM);
                wait.until(ExpectedConditions.elementToBeClickable(confirmDelete)).click();
            }
        }
        return this;
    }
}