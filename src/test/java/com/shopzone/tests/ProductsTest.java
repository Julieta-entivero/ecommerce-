package com.shopzone.tests;

import com.shopzone.base.BaseTest;
import com.shopzone.pages.LoginPage;
import com.shopzone.pages.ProductsPage;
import com.shopzone.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ProductsTest extends BaseTest {

    private ProductsPage productsPage;
    private final String USER = ConfigReader.get("standard.user");
    private final String PASS = ConfigReader.get("standard.password");

    @BeforeMethod
    public void loginAndInit() {
        LoginPage loginPage = new LoginPage(driver);
        productsPage = loginPage.login(USER, PASS);
    }

    @Test
    public void testProductosSeCargan() {
        Assert.assertTrue(productsPage.isPageLoaded());
        Assert.assertEquals(productsPage.getProductCount(), 6);
    }

    @Test
    public void testAgregarProductoAlCarrito() {
        productsPage.addProductByName("Sauce Labs Backpack");
        Assert.assertEquals(productsPage.getCartBadgeCount(), "1");
    }

    @Test
    public void testAgregarVariosProductos() {
        productsPage.addProductByName("Sauce Labs Backpack");
        productsPage.addProductByName("Sauce Labs Bike Light");
        Assert.assertEquals(productsPage.getCartBadgeCount(), "2");
    }

    @Test
    public void testRemoverProductoDelCarrito() {
        productsPage.addProductByName("Sauce Labs Backpack");
        Assert.assertEquals(productsPage.getCartBadgeCount(), "1");

        productsPage.removeProductByName("Sauce Labs Backpack");
        Assert.assertEquals(productsPage.getCartBadgeCount(), "0");
    }

    @Test
    public void testOrdenarPorPrecioMenorAMayor() {
        productsPage.sortBy("Price (low to high)");
        List<Double> prices = productsPage.getProductPrices();

        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(prices.get(i) <= prices.get(i + 1),
                "Los precios no estan ordenados de menor a mayor");
        }
    }

    @Test
    public void testOrdenarPorPrecioMayorAMenor() {
        productsPage.sortBy("Price (high to low)");
        List<Double> prices = productsPage.getProductPrices();

        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(prices.get(i) >= prices.get(i + 1),
                "Los precios no estan ordenados de mayor a menor");
        }
    }

    @Test
    public void testOrdenarPorNombreAZ() {
        productsPage.sortBy("Name (A to Z)");
        List<String> names = productsPage.getProductNames();

        for (int i = 0; i < names.size() - 1; i++) {
            Assert.assertTrue(names.get(i).compareTo(names.get(i + 1)) <= 0,
                "Los nombres no estan en orden A-Z");
        }
    }
}
