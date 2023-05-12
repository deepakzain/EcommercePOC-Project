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
		lgnPage=new loginPage();
		inventoryPage=lgnPage.login(prop.getProperty("username"), prop.getProperty("password"));
		itemDetailedPage=inventoryPage.clickOnProductItem();
		itemDetailedPage.clickonAddToCartBtn();
		Assert.assertTrue(itemDetailedPage.validateAddtoCart());
		cartPage=itemDetailedPage.clickOnCartBtn();
		Assert.assertEquals(cartPage.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
	}
}
