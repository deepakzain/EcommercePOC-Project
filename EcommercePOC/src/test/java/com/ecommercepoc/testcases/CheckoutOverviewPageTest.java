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
public class CheckoutOverviewPageTest extends BaseClass{
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
	public void verifyTotalPrice() {
		Log.startTestCase("verifyTotalPrice");
		lgnPage=new loginPage();
		inventoryPage=lgnPage.login(prop.getProperty("username"), prop.getProperty("password"));
		itemDetailedPage=inventoryPage.clickOnProductItem();
		itemDetailedPage.clickonAddToCartBtn();
		cartPage=itemDetailedPage.clickOnCartBtn();
		checkoutPage=cartPage.clickOnCheckoutBtn();
		String firstName=prop.getProperty("firstname");
		String lastName=prop.getProperty("lastname");
		String postalCode=prop.getProperty("postalcode");
		Log.info("User is going to enter firstname, lastname, postal code and will click on Continue button");
		checkoutOverviewpage=checkoutPage.ClickOnContinueBtn(firstName, lastName, postalCode);
		
		double unitPrice=checkoutOverviewpage.getUnitPrice();
		double totalPrice=checkoutOverviewpage.getTotalPrice();
		double tax=checkoutOverviewpage.getTax();
		double totalExpectedPrice=unitPrice+tax;
		System.out.println(unitPrice+" + "+tax+" = "+totalPrice);
		Log.info("Verifying the unit price, tax, toal price");
		Assert.assertEquals(totalExpectedPrice, totalPrice);
		Log.info("User is going to click on Finish button");
		checkoutCompletePage=checkoutOverviewpage.clickOnFinish();
		Log.endTestCase("verifyTotalPrice is Sucess");
	}
}
