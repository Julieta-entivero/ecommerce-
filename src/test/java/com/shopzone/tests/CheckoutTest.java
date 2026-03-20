package com.shopzone.tests;

import com.shopzone.base.BaseTest;
import com.shopzone.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    private ProductsPage productsPage;

    @BeforeMethod
    public void loginAndInit() {
        LoginPage loginPage = new LoginPage(driver);
        productsPage = loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void testCheckoutCompleto() {
        productsPage.addProductByName("Sauce Labs Backpack");

        CartPage cartPage = productsPage.goToCart();
        CheckoutPage checkoutPage = cartPage.goToCheckout();

        checkoutPage.fillInfo("Julieta", "Entivero", "5000");
        checkoutPage.continueCheckout();
        checkoutPage.finish();

        Assert.assertTrue(checkoutPage.isSuccessDisplayed());
        Assert.assertEquals(checkoutPage.getSuccessMessage(), "Thank you for your order!");
    }

    @Test
    public void testCheckoutSinNombre() {
        productsPage.addProductByName("Sauce Labs Backpack");

        CartPage cartPage = productsPage.goToCart();
        CheckoutPage checkoutPage = cartPage.goToCheckout();

        checkoutPage.fillInfo("", "Entivero", "5000");
        checkoutPage.continueCheckout();

        Assert.assertTrue(checkoutPage.isErrorDisplayed());
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("First Name is required"));
    }

    @Test
    public void testCheckoutSinApellido() {
        productsPage.addProductByName("Sauce Labs Backpack");

        CartPage cartPage = productsPage.goToCart();
        CheckoutPage checkoutPage = cartPage.goToCheckout();

        checkoutPage.fillInfo("Julieta", "", "5000");
        checkoutPage.continueCheckout();

        Assert.assertTrue(checkoutPage.isErrorDisplayed());
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Last Name is required"));
    }

    @Test
    public void testCheckoutSinCodigoPostal() {
        productsPage.addProductByName("Sauce Labs Backpack");

        CartPage cartPage = productsPage.goToCart();
        CheckoutPage checkoutPage = cartPage.goToCheckout();

        checkoutPage.fillInfo("Julieta", "Entivero", "");
        checkoutPage.continueCheckout();

        Assert.assertTrue(checkoutPage.isErrorDisplayed());
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Postal Code is required"));
    }

    @Test
    public void testCheckoutVariosProductos() {
        productsPage.addProductByName("Sauce Labs Backpack");
        productsPage.addProductByName("Sauce Labs Bike Light");
        productsPage.addProductByName("Sauce Labs Bolt T-Shirt");

        CartPage cartPage = productsPage.goToCart();
        Assert.assertEquals(cartPage.getItemCount(), 3);

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.fillInfo("Julieta", "Entivero", "5000");
        checkoutPage.continueCheckout();

        // verificar que el total no sea vacio
        String total = checkoutPage.getTotal();
        Assert.assertNotNull(total);
        Assert.assertTrue(total.contains("$"));

        checkoutPage.finish();
        Assert.assertTrue(checkoutPage.isSuccessDisplayed());
    }
}
