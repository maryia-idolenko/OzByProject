package tests;

import org.testng.annotations.Test;

public class ChangeUserNameTest extends BaseTest {

    @Test(description = "Change User Name")
    public void goToPersonalData() {
        String name = "Masha";
        String newName = "Maryia";
        personalSteps.goToPersonalData();
        infoSteps.changeUserName(name);
        infoSteps.checkUserName(name);
        infoSteps.changeUserName(newName);
        infoSteps.checkUserName(newName);
    }
}