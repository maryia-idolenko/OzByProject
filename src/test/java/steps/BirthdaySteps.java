package steps;

import io.qameta.allure.Step;
import models.Birthday;
import org.openqa.selenium.WebDriver;
import pages.BirthdayPage;

public class BirthdaySteps {
    private BirthdayPage birthday;


    public BirthdaySteps(WebDriver driver) {
        birthday = new BirthdayPage(driver);
    }

    @Step("Open Birthday Page")
    public int countBirthdayBeforeChanges() {
        birthday.openPage();
        return birthday.countBirthdayCards();
    }

    @Step("Add Birthday")
    public BirthdaySteps addBirthday(Birthday user) {
        birthday.addBirthday(user);
        return this;
    }

    @Step("Delete Birthday Card")
    public BirthdaySteps deleteCard(String name) {
        birthday.deleteBirthdayCard(name);
        return this;
    }
}