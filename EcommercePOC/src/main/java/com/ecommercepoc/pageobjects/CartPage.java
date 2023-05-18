/**
 * 
 */
package com.ecommercepoc.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.ecommercepoc.actiondriver.Action;
import com.ecommercepoc.base.BaseClass;

/**
 * @author deepak.j
 *
 */
public class CartPage extends BaseClass{

	@FindBy(id="continue-shopping")
	WebElement continueShoppingBtn;
	
	@FindBy(id="checkout")
	WebElement checkoutBtn;
	
	public CartPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public CheckoutPage clickOnCheckoutBtn() {
		Action.click(getDriver(), checkoutBtn);
		return new CheckoutPage();
	}
	
	public InventoryPage clickOnContinueShoppingBtn() {
		Action.click(getDriver(), continueShoppingBtn);
		return new InventoryPage();
	}
	
	public String getCurrentUrl() {
		String cartPageURL=getDriver().getCurrentUrl();
		return cartPageURL;
	}
}
