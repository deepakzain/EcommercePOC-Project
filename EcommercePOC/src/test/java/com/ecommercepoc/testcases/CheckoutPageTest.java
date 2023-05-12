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
import com.ecommercepoc.pageobjects.CheckoutOverviewPage;
import com.ecommercepoc.pageobjects.CheckoutPage;
import com.ecommercepoc.pageobjects.InventoryPage;
import com.ecommercepoc.pageobjects.ItemDetailedPage;
import com.ecommercepoc.pageobjects.loginPage;
import com.ecommercepoc.utility.Log;

/**
 * @author deepak.j
 *
 */
public class CheckoutPageTest extends BaseClass {
	loginPage lgnPage;
	InventoryPage inventoryPage;
	ItemDetailedPage itemDetailedPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	CheckoutOverviewPage checkoutOverviewpage;
	
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup() {
		launchApp();
	}
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test
	public void checkoutInformation() {
		Log.startTestCase("checkoutInformation");
		lgnPage=new loginPage();
		inventoryPage=lgnPage.login(prop.getProperty("username"), prop.getProperty("password"));
		itemDetailedPage=inventoryPage.clickOnProductItem();
		itemDetailedPage.clickonAddToCartBtn();
		cartPage=itemDetailedPage.clickOnCartBtn();
		checkoutPage=cartPage.clickOnCheckoutBtn();
		String firstName=prop.getProperty("firstname");
		String lastName=prop.getProperty("lastname");
		String postalCode=prop.getProperty("postalcode");
		checkoutOverviewpage=checkoutPage.ClickOnContinueBtn(firstName, lastName, postalCode);
		Assert.assertEquals(checkoutOverviewpage.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
		Log.endTestCase("checkoutInformation is Sucess");
	}
	
}
