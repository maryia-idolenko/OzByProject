package tests;

import models.Address;
import org.testng.annotations.Test;

public class AddShippingAddressTest extends BaseTest {

    @Test(description = "Add Shipping Address")
    public void addNewAddress() {
        Address address = new Address("220018", "Пономаренко", "75", "1", "1", "1");
        personalSteps.goToPersonalData();
        infoSteps.addAddress(address);
    }
}