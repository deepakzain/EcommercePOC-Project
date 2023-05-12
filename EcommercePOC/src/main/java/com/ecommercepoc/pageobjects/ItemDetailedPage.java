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
public class ItemDetailedPage extends BaseClass{

	@FindBy(xpath="//button[contains(@id,'add-to-cart-')]")
	WebElement addtoCartbtn;
	
	@FindBy(xpath="//button[contains(@id,'remove-')]")
	WebElement removeBtn;
	
	@FindBy(xpath="//a[@class='shopping_cart_link']")
	WebElement cartBtn;
	
	
	public ItemDetailedPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public void clickonAddToCartBtn() {
		Action.click(getDriver(), addtoCartbtn);
	}
	
	public boolean validateAddtoCart() {
		return Action.isDisplayed(getDriver(), removeBtn);
	}
	
	public CartPage clickOnCartBtn() {
		Action.click(getDriver(), cartBtn);
		return new CartPage();
	}
	public String getCurrentUrl() {
		String itemDetailedPageURL=getDriver().getCurrentUrl();
		return itemDetailedPageURL;
	}
}
