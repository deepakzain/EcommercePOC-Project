/**
 * 
 */
package com.ecommercepoc.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ecommercepoc.base.BaseClass;
import com.ecommercepoc.pageobjects.InventoryPage;
import com.ecommercepoc.pageobjects.ItemDetailedPage;
import com.ecommercepoc.pageobjects.loginPage;

/**
 * @author deepak.j
 *
 */
public class InventoryPageTest extends BaseClass{
	
	InventoryPage inventoryPage;
	ItemDetailedPage itemDetailedpage;
	loginPage lgnPage;
	
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup() {
		launchApp();
	}
	
	@Test
	public void verifyProductprsence() {
		lgnPage=new loginPage();
		String username=prop.getProperty("username");
		String pass=prop.getProperty("password");
		inventoryPage=lgnPage.login(username, pass);
		boolean result =inventoryPage.isProductAvailable();
		Assert.assertTrue(result);
	}
	
	@Test
	public void selectAnItem() {
		lgnPage=new loginPage();
		String username=prop.getProperty("username");
		String pass=prop.getProperty("password");
		inventoryPage=lgnPage.login(username, pass);
		itemDetailedpage=inventoryPage.clickOnProductItem();
		String actualUrl=itemDetailedpage.getCurrentUrl();
		String expectedUrl="https://www.saucedemo.com/inventory-item.html?id=4";
		Assert.assertEquals(actualUrl, expectedUrl);
	}
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
}
