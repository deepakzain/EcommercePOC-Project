package com.ecommercepoc.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.ecommercepoc.actiondriver.Action;
import com.ecommercepoc.base.BaseClass;

public class loginPage extends BaseClass {
	
	@FindBy(id="user-name")
	WebElement usernameTextBox;
	
	@FindBy(id="password")
	WebElement passTextBox;
	
	@FindBy(id="login-button")
	WebElement loginBtn;
	
	@FindBy(xpath="//div[@class='login_logo']")
	WebElement SwagLogo;
	 
	public loginPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public InventoryPage login(String uname, String pass) {
		Action.type(usernameTextBox, uname);
		Action.type(passTextBox, pass);
		Action.click(getDriver(), loginBtn);
		return new InventoryPage();
	}
	
	public boolean validateLoginPageLogo() {
		return Action.isDisplayed(getDriver(), SwagLogo);
	}
	
	public String getSwagTitle() {
		return getDriver().getTitle();
	}
}
