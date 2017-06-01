package com.qait.demo.keywords;



import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;



public class CnowLoginPageActions extends GetPage {

	WebDriver driver;

	public CnowLoginPageActions(WebDriver driver) {
		super(driver, "CnowLoginPage");
		this.driver = driver;
	}
	public void loginWithUserNameAndPassword(String emailId, String password) {
		_enterEmailId(emailId);
		_enterPassword(password);
		element("btn_logonButton").click();
	}

	private void _enterEmailId(String userId) {
		element("input_user_id").click();
		element("input_user_id").clear();
		element("input_user_id").sendKeys(userId);
	}

	private void _enterPassword(String password) {
		element("input_password").click();
		element("input_password").clear();
		element("input_password").sendKeys(password);
	}

}
