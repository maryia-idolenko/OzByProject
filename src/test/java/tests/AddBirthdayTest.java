package tests;

import models.Birthday;
import org.testng.annotations.Test;

public class AddBirthdayTest extends BaseTest {

    @Test(description = "Add New Birthday Card and Delete Birthday Card")
    public void addAndDeleteBirthday() {

        Birthday user = new Birthday("Мария", "+375297499730", "20", "июля", "1990", "Женский", "comment");
        personalSteps.toBirthdaysPage();
        int count = birthday.countBirthdayBeforeChanges();
        birthday.addBirthday(user);
        birthday.deleteCard(user.getName());
    }
}