package com.shopzone.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(className = "inventory_item")
    private List<WebElement> productItems;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        try {
            return getText(pageTitle).equals("Products");
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public int getProductCount() {
        return productItems.size();
    }

    public void addProductToCart(int index) {
        WebElement product = productItems.get(index);
        WebElement addBtn = product.findElement(By.cssSelector("button[id^='add-to-cart']"));
        click(addBtn);
    }

    public void addProductByName(String productName) {
        for (WebElement item : productItems) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            if (name.equals(productName)) {
                WebElement addBtn = item.findElement(By.cssSelector("button[id^='add-to-cart']"));
                click(addBtn);
                return;
            }
        }
        throw new NoSuchElementException("Producto no encontrado: " + productName);
    }

    public void removeProductByName(String productName) {
        for (WebElement item : productItems) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            if (name.equals(productName)) {
                WebElement removeBtn = item.findElement(By.cssSelector("button[id^='remove']"));
                click(removeBtn);
                return;
            }
        }
        throw new NoSuchElementException("Producto no encontrado para remover: " + productName);
    }

    public String getCartBadgeCount() {
        try {
            return cartBadge.getText();
        } catch (NoSuchElementException e) {
            return "0";
        }
    }

    public CartPage goToCart() {
        click(cartLink);
        return new CartPage(driver);
    }

    public void sortBy(String option) {
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(option);
    }

    public List<String> getProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement item : productItems) {
            names.add(item.findElement(By.className("inventory_item_name")).getText());
        }
        return names;
    }

    public List<Double> getProductPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement item : productItems) {
            String priceText = item.findElement(By.className("inventory_item_price")).getText();
            prices.add(Double.parseDouble(priceText.replace("$", "")));
        }
        return prices;
    }
}
