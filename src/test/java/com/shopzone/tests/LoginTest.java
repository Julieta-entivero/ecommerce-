package com.shopzone.tests;

import com.shopzone.base.BaseTest;
import com.shopzone.pages.LoginPage;
import com.shopzone.pages.ProductsPage;
import com.shopzone.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private final String VALID_USER = ConfigReader.get("standard.user");
    private final String VALID_PASS = ConfigReader.get("standard.password");

    @BeforeMethod
    public void initPages() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginExitoso() {
        ProductsPage productsPage = loginPage.login(VALID_USER, VALID_PASS);
        Assert.assertTrue(productsPage.isPageLoaded(), "No se cargo la pagina de productos");
    }

    @Test
    public void testLoginUsuarioInvalido() {
        loginPage.loginExpectingError("usuario_invalido", VALID_PASS);
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().contains("do not match"));
    }

    @Test
    public void testLoginPasswordVacio() {
        loginPage.loginExpectingError(VALID_USER, "");
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().contains("Password is required"));
    }

    @Test
    public void testLoginUsuarioVacio() {
        loginPage.loginExpectingError("", VALID_PASS);
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"));
    }

    @Test
    public void testLoginUsuarioBloqueado() {
        loginPage.loginExpectingError(ConfigReader.get("locked.user"), VALID_PASS);
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"));
    }
}
