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
import com.ecommercepoc.pageobjects.CheckoutCompletePage;
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
public class CheckoutCompletePageTest extends BaseClass {

	loginPage lgnPage;
	InventoryPage inventoryPage;
	ItemDetailedPage itemDetailedPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	CheckoutOverviewPage checkoutOverviewpage;
	CheckoutCompletePage checkoutCompletePage;
	
	@BeforeMethod(groups = {"Smoke"})
	public void setup() {
		launchApp();
	}
	
	@AfterMethod(groups = {"Smoke"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test
	public void verifyCOnfifrmOrder() {
		Log.startTestCase("verifyCOnfifrmOrder");
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
		checkoutCompletePage=checkoutOverviewpage.clickOnFinish();
		Log.info("Verify the confirm message");
		String actualMessage=checkoutCompletePage.validateConfirmMsg();
		Assert.assertEquals(actualMessage, "Thank you for your order!");
		Log.info("User is going to click on Back button and verify the redirected page");
		inventoryPage=checkoutCompletePage.clickOnBackBtn();
		Assert.assertEquals(inventoryPage.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
		Log.endTestCase("verifyCOnfifrmOrder is Sucess");
	}
	
}
