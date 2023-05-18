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
public class CheckoutCompletePage extends BaseClass {
	@FindBy(css=".complete-header")
	WebElement confirmationMessage;
	
	@FindBy(id="back-to-products")
	WebElement backHomeBtn;
	
	public CheckoutCompletePage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public String validateConfirmMsg() {
		String confirmMessage=confirmationMessage.getText();
		return confirmMessage;
	}	
		
	public InventoryPage clickOnBackBtn() {
		Action.click(getDriver(), backHomeBtn);
		return new InventoryPage();
	}
}
