package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import steps.*;
import utils.PropertyManager;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    public LoginSteps login;
    public PersonalInfoSteps infoSteps;
    public PersonalPageSteps personalSteps;
    public PropertyManager creds;
    public BirthdaySteps birthday;
    public ProductSteps product;
    public BasketSteps basket;
    private WebDriver driver;

    @BeforeMethod(description = "Opening Chrome Driver")
    public void setDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        login = new LoginSteps(driver);
        infoSteps = new PersonalInfoSteps(driver);
        personalSteps = new PersonalPageSteps(driver);
        birthday = new BirthdaySteps(driver);
        product = new ProductSteps(driver);
        basket = new BasketSteps(driver);
    }

    @BeforeMethod(dependsOnMethods = "setDriver")
    public void credentials() {
        creds = new PropertyManager();
    }

    @BeforeMethod(description = "Login", dependsOnMethods = "credentials")
    public void login() {
        login.loginPageOpened();
        login.login(creds.get("email"), creds.get("password"));

    }

    @AfterMethod(alwaysRun = true, description = "Closing Chrome Driver")
    public void closeDriver() {
        driver.quit();
    }
}