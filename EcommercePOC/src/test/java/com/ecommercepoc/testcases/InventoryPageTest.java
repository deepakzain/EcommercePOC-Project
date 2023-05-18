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
import com.ecommercepoc.utility.Log;

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
		Log.startTestCase("verifyProductprsence");
		lgnPage=new loginPage();
		String username=prop.getProperty("username");
		String pass=prop.getProperty("password");
		inventoryPage=lgnPage.login(username, pass);
		boolean result =inventoryPage.isProductAvailable();
		Log.info("Verifying if the desired item is present");
		Assert.assertTrue(result);
		Log.endTestCase("verifyProductprsence is Sucess");
	}
	
	@Test
	public void selectAnItem() {
		Log.startTestCase("selectAnItem");
		lgnPage=new loginPage();
		String username=prop.getProperty("username");
		String pass=prop.getProperty("password");
		inventoryPage=lgnPage.login(username, pass);
		Log.info("User is going to click on product");
		itemDetailedpage=inventoryPage.clickOnProductItem();
		String actualUrl=itemDetailedpage.getCurrentUrl();
		String expectedUrl="https://www.saucedemo.com/inventory-item.html?id=4";
		Log.info("Verifying if user redirected to detailed page");
		Assert.assertEquals(actualUrl, expectedUrl);
		Log.endTestCase("selectAnItem is Sucess");
	}
	
	@AfterMethod(groups = {"Smoke"})
	public void tearDown() {
		getDriver().quit();
	}
}
