package com.shopzone.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement continueBtn;

    @FindBy(id = "finish")
    private WebElement finishBtn;

    @FindBy(id = "cancel")
    private WebElement cancelBtn;

    @FindBy(className = "complete-header")
    private WebElement successMessage;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMsg;

    @FindBy(className = "summary_total_label")
    private WebElement totalLabel;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillInfo(String firstName, String lastName, String zip) {
        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
        type(postalCodeInput, zip);
    }

    public void continueCheckout() {
        click(continueBtn);
    }

    public void finish() {
        click(finishBtn);
    }

    public void cancel() {
        click(cancelBtn);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public boolean isSuccessDisplayed() {
        return isDisplayed(successMessage);
    }

    public String getErrorMessage() {
        return getText(errorMsg);
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMsg);
    }

    public String getTotal() {
        return getText(totalLabel);
    }
}
