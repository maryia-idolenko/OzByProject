package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.PersonalPage;

public class PersonalPageSteps {
    private PersonalPage personalPage;

    public PersonalPageSteps(WebDriver driver) {
        personalPage = new PersonalPage(driver);
    }

    @Step("Open Personal Data")
    public void goToPersonalData() {
        personalPage.openPage();
        personalPage.goToPersonalData();
    }

    @Step("Open Birthday Page")
    public void toBirthdaysPage() {
        personalPage.openPage();
        personalPage.goToBirthdays();
    }

    @Step("Search Any Product")
    public void searchProduct(String name) {
        personalPage.searchProduct(name);
    }

    @Step("Open FavoriteList ")
    public void goToFavoriteList() {
        personalPage.goToFavoriteList();
    }

    @Step("Count of Product in FavoriteList")
    public int saveCount() {
        return personalPage.countFavoriteProduct();
    }

    @Step("Delete Product From FavoriteList")
    public PersonalPageSteps deleteProductFromFavoriteList(String name) {
        personalPage.deleteProductFromFavoriteList(name);
        return this;
    }

    @Step("Check Count Before and After deleting Product")
    public PersonalPageSteps checkAfterDelete(int count) {
        personalPage.countAfterDeleteProduct(count);
        return this;
    }

    @Step("Open Basket")
    public void goToBasket() {
        personalPage.goToBasket();
    }
}