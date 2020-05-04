package tests;

import org.testng.annotations.Test;

public class AddProductToFavoriteListTest extends BaseTest {

    @Test(description = "Add Product to FavoriteList and Delete This Product")
    public void addToWishListAndDelete() {
        int count;
        int countFavoriteProduct;

        String nameProductFavorite = "Тонкое искусство пофигизма. Парадоксальный способ жить счастливо";
        personalSteps.searchProduct(nameProductFavorite);
        count = product.countBeforeAddToFavoriteList();
        product.choiceProduct(nameProductFavorite);
        product.validationProductCountInFavoriteList(count);
        personalSteps.goToFavoriteList();
        countFavoriteProduct = personalSteps.saveCount();
        personalSteps.deleteProductFromFavoriteList(nameProductFavorite);
        personalSteps.checkAfterDelete(countFavoriteProduct);
    }
}