package com.shopzone.tests;

import com.shopzone.base.BaseTest;
import com.shopzone.pages.LoginPage;
import com.shopzone.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void initPages() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginExitoso() {
        ProductsPage productsPage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(productsPage.isPageLoaded(), "No se cargo la pagina de productos");
    }

    @Test
    public void testLoginUsuarioInvalido() {
        loginPage.loginExpectingError("usuario_invalido", "secret_sauce");
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().contains("do not match"));
    }

    @Test
    public void testLoginPasswordVacio() {
        loginPage.loginExpectingError("standard_user", "");
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().contains("Password is required"));
    }

    @Test
    public void testLoginUsuarioVacio() {
        loginPage.loginExpectingError("", "secret_sauce");
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"));
    }

    @Test
    public void testLoginUsuarioBloqueado() {
        loginPage.loginExpectingError("locked_out_user", "secret_sauce");
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"));
    }
}
