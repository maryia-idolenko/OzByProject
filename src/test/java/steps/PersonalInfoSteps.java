package steps;

import io.qameta.allure.Step;
import models.Address;
import org.openqa.selenium.WebDriver;
import pages.ChangePersonalDataPage;


public class PersonalInfoSteps {
    private ChangePersonalDataPage infoPage;


    public PersonalInfoSteps(WebDriver driver) {
        infoPage = new ChangePersonalDataPage(driver);
    }

    @Step("Change User Name")
    public void changeUserName(String newName) {
        infoPage.openPage();
        infoPage.changePersonalNameAndSave(newName);
    }

    @Step("Upload User Photo")
    public void uploadImg(String path) {
        infoPage.uploadImage(path);
    }

    @Step("Check User Name After Updating")
    public void checkUserName(String userName) {
        infoPage.checkPersonalName(userName);
    }

    @Step("Add Address")
    public void addAddress(Address address) {
        int sizeAddress;
        infoPage.goToAddressInfo();
        sizeAddress = infoPage.checkAddressBeforeAdding();
        infoPage.addShippingAddress(address);
        infoPage.checkAddressAfterAdding(sizeAddress);
    }
}