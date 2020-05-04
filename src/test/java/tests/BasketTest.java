package tests;

import org.testng.annotations.Test;

public class BasketTest extends BaseTest {

    @Test(description = "Add Product in the Basket and Delete Product")
    public void addAndDeleteProduct() {
        int count;

        String nameProduct = "Agile-тестирование. Обучающий курс для всей команды";
        personalSteps.searchProduct(nameProduct);
        count = product.countProductBefore();
        product.choiceProduct(nameProduct);
        product.checkProductCount(count);
        personalSteps.goToBasket();
        basket.deleteProduct(nameProduct);
    }
}