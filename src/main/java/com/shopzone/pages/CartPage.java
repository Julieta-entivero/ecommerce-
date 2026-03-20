package com.shopzone.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "checkout")
    private WebElement checkoutBtn;

    @FindBy(id = "continue-shopping")
    private WebElement continueShopping;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getItemCount() {
        return cartItems.size();
    }

    public String getItemName(int index) {
        return cartItems.get(index).findElement(By.className("inventory_item_name")).getText();
    }

    public String getItemPrice(int index) {
        return cartItems.get(index).findElement(By.className("inventory_item_price")).getText();
    }

    public void removeItem(int index) {
        WebElement removeBtn = cartItems.get(index).findElement(By.cssSelector("button[id^='remove']"));
        click(removeBtn);
    }

    public CheckoutPage goToCheckout() {
        click(checkoutBtn);
        return new CheckoutPage(driver);
    }

    public ProductsPage continueShopping() {
        click(continueShopping);
        return new ProductsPage(driver);
    }
}
