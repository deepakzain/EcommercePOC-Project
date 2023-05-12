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
public class CheckoutOverviewPage extends BaseClass{
	
	@FindBy(xpath="//div[@class='inventory_item_price']")
	WebElement unitPrice;
	
	@FindBy(css=".summary_total_label")
	WebElement totalPrice;
	
	@FindBy(id="finish")
	WebElement finishBtn;
	
	@FindBy(id="cancel")
	WebElement cancelBtn;
	
	@FindBy(css=".summary_tax_label")
	WebElement tax;
	
	public CheckoutOverviewPage(){
		PageFactory.initElements(getDriver(), this);
	}
	
	public double getUnitPrice() {
		String unitPrice1 = unitPrice.getText();
		String[] str=new String[2];
		str=unitPrice1.split("\\$");
		double unitPrice2=Double.parseDouble(str[1]);
		return unitPrice2;
	}
	
	public double getTotalPrice() {
		String totalPrice1 = totalPrice.getText();
		String[] str=new String[2];
		str=totalPrice1.split("\\$");
		double totalPrice2=Double.parseDouble(str[1]);
		return totalPrice2;
	}
	
	public CheckoutCompletePage clickOnFinish() {
		Action.click(getDriver(), finishBtn);
		return new CheckoutCompletePage();
	}
	
	public double getTax() {
		String tax11 = tax.getText();
		String[] str=new String[2];
		str=tax11.split("\\$");
		double tax2=Double.parseDouble(str[1]);
		return tax2;
	}
	
	public InventoryPage clickOnCancel() {
		Action.click(getDriver(), cancelBtn);
		return new InventoryPage();
	}
	
	public String getCurrentUrl() {
		String checkoutOverviewPageURL=getDriver().getCurrentUrl();
		return checkoutOverviewPageURL;
	}
	

}
