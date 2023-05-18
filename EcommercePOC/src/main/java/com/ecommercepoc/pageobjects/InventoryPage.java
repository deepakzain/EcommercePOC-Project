/**
 * 
 */
package com.ecommercepoc.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.ecommercepoc.actiondriver.Action;
import com.ecommercepoc.base.BaseClass;

/**
 * @author deepak.j
 *
 */
public class InventoryPage extends BaseClass {

	@FindBy(xpath="//a[@class='shopping_cart_link']")
	WebElement cartBtn;
	
	@FindBy(xpath="//div[@class='inventory_item_name']")
	List<WebElement> allItemList;
	
	@FindBy(xpath="//div[text()='Sauce Labs Backpack']")
	WebElement ProductItem;
	
	public InventoryPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public boolean isProductAvailable() {
		return Action.isDisplayed(getDriver(), ProductItem);
	}
	
	public ItemDetailedPage clickOnProductItem() {	
		Action.click(getDriver(), ProductItem);
		return new ItemDetailedPage();
	}
	
	public String getCurrentUrl() {
		String inventoryUrl=getDriver().getCurrentUrl();
		return inventoryUrl;
	}

	
}
