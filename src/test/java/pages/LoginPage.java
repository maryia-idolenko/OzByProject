package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AllureUtils;

public class LoginPage extends BasePage {
    private final static By LOGO_OZ = By.className("top-panel__logo__item");
    private final static By LOGIN_PANEL = By.className("top-panel__userbar__auth__lbl");
    private static final By LOGIN_FORM = By.id("loginPopupIntro");
    private final static By EMAIL_BUTTON = By.id("loginFormLoginEmailLink");
    private final static By EMAIL_FIELD = By.xpath("//*[@id=\"loginForm\"]/div[2]/div[1]/div[1]/input");
    private final static By PASSWORD_FIELD = By.cssSelector(".i-input-group__cell .i-input_with-padding");
    private final static By LOGIN_BUTTON = By.xpath("//*[@id=\"loginForm\"]/button");
    private final static By USERBAR_PANEL = By.cssSelector(".top-panel__userbar__user");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage openPage() {
        driver.get("https://oz.by/");
        isPageOpened();
        goToLogin();
        return this;
    }

    private void isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(LOGO_OZ));
        } catch (TimeoutException ex) {
            AllureUtils.takeScreenshot(driver);
            throw new TimeoutException("Страница не загрузилась");
        }
    }

    private void goToLogin() {
        driver.findElement(LOGIN_PANEL).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_FORM));
        driver.findElement(EMAIL_BUTTON).click();
        AllureUtils.takeScreenshot(driver);
    }

    public LoginPage inputEmailAndPassword(String email, String password) {
        driver.findElement(EMAIL_FIELD).sendKeys(email);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(USERBAR_PANEL));
        AllureUtils.takeScreenshot(driver);
        return this;
    }
}