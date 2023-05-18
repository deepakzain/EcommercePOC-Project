/**
 * 
 */
package com.ecommercepoc.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ecommercepoc.base.BaseClass;
import com.ecommercepoc.pageobjects.CartPage;
import com.ecommercepoc.pageobjects.CheckoutPage;
import com.ecommercepoc.pageobjects.InventoryPage;
import com.ecommercepoc.pageobjects.ItemDetailedPage;
import com.ecommercepoc.pageobjects.loginPage;
import com.ecommercepoc.utility.Log;

/**
 * @author deepak.j
 *
 */
public class CartPageTest extends BaseClass{
	loginPage lgnPage;
	InventoryPage inventoryPage;
	ItemDetailedPage itemDetailedPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	
	@BeforeMethod(groups = {"Smoke"})
	public void setup() {
		launchApp();
	}
	
	@AfterMethod(groups = {"Smoke"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test
	public void checkOutItem() {
		Log.startTestCase("checkOutItem");
		lgnPage=new loginPage();
		inventoryPage=lgnPage.login(prop.getProperty("username"), prop.getProperty("password"));
		itemDetailedPage=inventoryPage.clickOnProductItem();
		itemDetailedPage.clickonAddToCartBtn();
		cartPage=itemDetailedPage.clickOnCartBtn();
		Log.info("User is going to click on checkout button present on checkout page");
		checkoutPage=cartPage.clickOnCheckoutBtn();
		Log.info("Verify if user is redirect to the checkout step one page");
		Assert.assertEquals(checkoutPage.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
		Log.endTestCase("checkOutItem is Sucess");
	}
}
