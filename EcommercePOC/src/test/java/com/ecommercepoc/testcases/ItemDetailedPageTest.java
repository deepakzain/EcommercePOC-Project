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
import com.ecommercepoc.pageobjects.InventoryPage;
import com.ecommercepoc.pageobjects.ItemDetailedPage;
import com.ecommercepoc.pageobjects.loginPage;
import com.ecommercepoc.utility.Log;

/**
 * @author deepak.j
 *
 */
public class ItemDetailedPageTest extends BaseClass{
	loginPage lgnPage;
	InventoryPage inventoryPage;
	ItemDetailedPage itemDetailedPage;
	CartPage cartPage;
	
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup() {
		launchApp();
	}
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(groups={"Regression","Sanity"})
	public void addItemToCart() {
		Log.startTestCase("addItemToCart");
		lgnPage=new loginPage();
		inventoryPage=lgnPage.login(prop.getProperty("username"), prop.getProperty("password"));
		itemDetailedPage=inventoryPage.clickOnProductItem();
		Log.info("User is going to click on add item to cart button");
		itemDetailedPage.clickonAddToCartBtn();
		Log.info("Verifying if item is added to cart");
		Assert.assertTrue(itemDetailedPage.validateAddtoCart());
		Log.info("User is going to click on cart button");
		cartPage=itemDetailedPage.clickOnCartBtn();
		Log.info("Verify if user is redirected to the cart page");
		Assert.assertEquals(cartPage.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
		Log.endTestCase("addItemToCart is Sucess");
	}
}
