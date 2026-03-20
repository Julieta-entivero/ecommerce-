package com.shopzone.tests;

import com.shopzone.base.BaseTest;
import com.shopzone.pages.CartPage;
import com.shopzone.pages.LoginPage;
import com.shopzone.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    private ProductsPage productsPage;

    @BeforeMethod
    public void loginAndInit() {
        LoginPage loginPage = new LoginPage(driver);
        productsPage = loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void testCarritoVacio() {
        CartPage cartPage = productsPage.goToCart();
        Assert.assertEquals(cartPage.getItemCount(), 0);
    }

    @Test
    public void testAgregarYVerEnCarrito() {
        productsPage.addProductByName("Sauce Labs Backpack");
        CartPage cartPage = productsPage.goToCart();

        Assert.assertEquals(cartPage.getItemCount(), 1);
        Assert.assertEquals(cartPage.getItemName(0), "Sauce Labs Backpack");
    }

    @Test
    public void testEliminarDelCarrito() {
        productsPage.addProductByName("Sauce Labs Backpack");
        productsPage.addProductByName("Sauce Labs Bike Light");

        CartPage cartPage = productsPage.goToCart();
        Assert.assertEquals(cartPage.getItemCount(), 2);

        cartPage.removeItem(0);
        Assert.assertEquals(cartPage.getItemCount(), 1);
    }

    @Test
    public void testVolverAProductos() {
        CartPage cartPage = productsPage.goToCart();
        ProductsPage backToProducts = cartPage.continueShopping();
        Assert.assertTrue(backToProducts.isPageLoaded());
    }
}
