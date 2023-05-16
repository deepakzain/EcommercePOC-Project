/**
 * 
 */
package com.ecommercepoc.pageobjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.ecommercepoc.actiondriver.Action;
import com.ecommercepoc.base.BaseClass;

/**
 * @author deepak.j
 *
 */
public class CheckoutPage extends BaseClass {
	
	@FindBy(xpath="//input[@id='first-name']")
	WebElement firstName;
	
	@FindBy(xpath="//input[@id='last-name']")
	WebElement lastName;
	
	@FindBy(xpath="//input[@id='postal-code']")
	WebElement postalCode;
	
	@FindBy(id="continue")
	WebElement continueBtn;
	
	@FindBy(id="cancel")
	WebElement cancelBtn;
	
	public CheckoutPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	
	public CheckoutOverviewPage ClickOnContinueBtn(String fname, String lastname, String code) {
		Action.type(firstName, fname);
		Action.type(lastName, lastname);
		Action.type(postalCode, code);
		Action.click(getDriver(), continueBtn);
		return new CheckoutOverviewPage();
	}
//	
//	public void AddFirstName(String fname) {
//		Action.type(firstName, fname);
//	}
//	public void AddlastName(String lname) {
//		Action.type(lastName, lname);
//	}
//	public void AddPostalCode(String pcode) {
//		Action.type(postalCode, pcode);
//	}
	
	public CartPage ClickOnCancelBtn() {
		Action.click(getDriver(), cancelBtn);
		return new CartPage();
	}
	
//	public CheckoutOverviewPage ClickonContinueBtn() {
//		Action.click(driver, continueBtn);
//		return new CheckoutOverviewPage();
//	}
	
	public String getCurrentUrl() {
		String checkoutPageURL=getDriver().getCurrentUrl();
		return checkoutPageURL;
	}
}
