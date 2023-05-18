package com.ecommercepoc.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ecommercepoc.base.BaseClass;
import com.ecommercepoc.dataprovider.DataProviders;
import com.ecommercepoc.pageobjects.InventoryPage;
import com.ecommercepoc.pageobjects.loginPage;
import com.ecommercepoc.utility.Log;

/**
 * @author deepak.j
 *
 */
public class loginPageTest extends BaseClass {
	
	loginPage lgnPage;
	InventoryPage inventoryPage;
	
	@BeforeMethod
	public void setup() {
		launchApp();
	}
	
	@Test(groups="Smoke")
	public void verifyLogo() {
		Log.startTestCase("verifyLogo");
		lgnPage=new loginPage();
		boolean result=lgnPage.validateLoginPageLogo();
		Log.info("Verifying if logo is displayed");
		Assert.assertTrue(result);
		Log.endTestCase("verifyLogo is Sucess");
	}
	
	@Test(groups="Smoke")
	public void verifyTitle() {
		Log.startTestCase("verifyTitle");
		String title=lgnPage.getSwagTitle();
		Log.info("Verifying if title is displayed and correct");
		Assert.assertEquals(title, "Swag Labs");
		Log.endTestCase("verifyTitle is Sucess");
	}
	
	@Test(dataProvider = "credentials", dataProviderClass=DataProviders.class, groups= {"Smoke"})
	public void loginTest(String username, String password) {
		Log.startTestCase("loginTest");
		lgnPage = new loginPage();

		Log.info("User is going to entered username & password and clicked on SignIn");
		
		inventoryPage=lgnPage.login(username, password);
		String actualUrl=inventoryPage.getCurrentUrl();
		String expectedUrl="https://www.saucedemo.com/inventory.html";
		Log.info("Verifying if user is bale to login");
		Assert.assertEquals(actualUrl, expectedUrl);
		Log.info("LoggedIn successfully");
		Log.endTestCase("Login is Sucess");
	}
	
	@AfterMethod(groups = {"Smoke"})
	public void tearDown() {
		getDriver().quit();
	}
	
}
