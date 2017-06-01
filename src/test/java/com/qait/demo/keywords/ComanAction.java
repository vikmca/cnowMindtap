package com.qait.demo.keywords;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;

/**
 * @author qainfotech
 *
 */
/**
 * @author qainfotech
 *
 */
public class ComanAction extends GetPage {

	public ComanAction(WebDriver driver, String pageName) {
		super(driver, pageName);
	}

	public void clickOnTextDisplayed(String expatedText) {
		clickOnElementFromText(element("textOnPage", expatedText));
	}

	public void selectTermTypeList(String id, String option) {
		selectProvidedTextFromDropDown(element("buttonIDClick", id), option);
		logMessage("Select from " + option + " DropDown Menu " + id);
	}

	public void verifyTextIsDisplayed(String expatedText) {
		wait.waitForLoadPage(wait.getTimeOut()*1000);
		try {	scrollMid(element("textOnPage", expatedText));
		isElementDisplayed("textOnPage", expatedText);
		}catch(StaleElementReferenceException e) {
			scrollMid(element("textOnPage", expatedText));
			isElementDisplayed("textOnPage", expatedText);
		}
	}

	public int verifyTextSizeIsDisplayed(String expectedText,int expectedint) {
		wait.waitForLoadPage(wait.getTimeOut()*1000);
		int actual	=	elements("textOnPage", expectedText).size();
		Assert.assertTrue(actual>=expectedint);
		return actual;
	}

	public void verifyTextIsNotDisplayed(String expatedText) {
		wait.hardWait(2);
		wait.waitForLoadPage(wait.getTimeOut()*1000);
		int size = elementsWithOutWait("textOnPage", expatedText).size();
		Assert.assertEquals(size,0);
	}

	public void typeInInputTage(WebElement webElement, String text) {
		WebElement el = webElement;
		el.click();
		el.clear();
		el.sendKeys(text);
		logMessage("Send Key on : " + text + " on Page " + getPageTitle());
	}

	public void clickOnButtonTag(WebElement webElement) {
		WebElement el = webElement;
		String name = getPageTitle();
		el.click();
		logMessage("Click on element on Page : " + name);
		wait.hardWait(1);
		handleAlert();
		wait.waitForLoadPage(wait.getTimeOut() * 1000);
		
	}

	public void clickOnButtonTag1(WebElement webElement) {
		WebElement el = webElement;
		String name = getPageTitle();
		el.click();
		logMessage("Click on element on Page : " + name);
		wait.hardWait(1);
	}

	public void frameLoadingImg() {
		wait.waitForLoadPage(wait.getTimeOut());
		wait.waitForElementToDisappear(element("pmtFrameLoading"));
		wait.waitForLoadPage(wait.getTimeOut());
	}

	public void clickOnElementFromText(WebElement webElement) {
		WebElement el = webElement;
		String name = getPageTitle();
		el.click();
		logMessage("Click on element on Page : " + name);
		wait.waitForLoadPage(wait.getTimeOut() * 1000);
	}

	public int getCountOfthaHTMLTag(List<WebElement> eles, String attribute,
			String text) {
		int returnValue = 0;

		int count = eles.size();
		for (int i = 0; i < count; i++) {
			String value = eles.get(i).getAttribute(attribute);
			if (value.equalsIgnoreCase(text)) {
				returnValue = i;
				break;
			}
		}
		return returnValue;
	}

	public void verifyDefaultGetWorkSelected() {
		String buttonID = "GetWork";
		// element("lnk_GetWork").isSelected();
		String expectedText = "images/GetWork_Over.gif";

		String src = element("buttonIDClick", buttonID).getAttribute("src");

		
		Assert.assertTrue(src.contains(expectedText));
	}

	public void preTestClassRun() {
		driver.manage().deleteAllCookies();
		try {
			Process p = Runtime.getRuntime().exec(
					"RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
			p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void typeTextOnIDinput(String name, String Text) {
		String val = element("buttonIDClick", name).getText();
		logMessage("current title name is " + val);
		element("buttonIDClick", name).clear();
		element("buttonIDClick", name).sendKeys(Text);
		logMessage("Updated new Title name : " + Text);
	}
	

	public void typeTextOnIDinput1(String name, String Text) {
		String val = element("buttonIDClick", name).getText();
		logMessage("current title name is " + val);
		element("buttonIDClick", name).sendKeys(Text);
		logMessage("Updated new Title name : " + Text);
	}
	public void typeTextOnIDinput1(String name, Keys chord) {
		String val = element("buttonIDClick", name).getText();
		logMessage("current title name is " + val);
		element("buttonIDClick", name).sendKeys(chord);
		logMessage("Updated new Title name : " + chord);
	}


	public void clickOnButton(String buttonID) {
		wait.waitForLoadPage(wait.getTimeOut() * 1000);
		clickOnButtonTag(element("buttonIDClick", buttonID));
		logMessage("Click On menu : " + buttonID + " on " + getPageTitle());
		wait.hardWait(1);
		wait.waitForLoadPage(wait.getTimeOut() * 1000);
		handleAlert();
	}
	
	public void verifyButtonIsEnable(String buttonID,boolean isEnable) {
		wait.waitForLoadPage(wait.getTimeOut() * 1000);
		if(element("buttonIDClick", buttonID).isEnabled()==isEnable) {
		logMessage("Button is Enable : " + buttonID + " on " + getPageTitle());
		Assert.assertEquals(element("buttonIDClick", buttonID).isEnabled(), isEnable);
		}else {
			logMessage("Button is not enalbe : " + buttonID + " on " + getPageTitle());
			Assert.assertEquals(element("buttonIDClick", buttonID).isEnabled(), isEnable);
		}
		
	}
	
	public void switchFrameAndClickElement(String frameID,String buttonID) {
		switchToDefaultContent();
		switchToFrame(element("buttonIDClick", frameID));
		clickOnButton(buttonID);
		switchToDefaultContent();	
	}

	public void clickOnButtonWithoutWait(String buttonID) {	
		clickOnButtonTag(element("buttonIDClick", buttonID));
		logMessage("Click On menu : " + buttonID + " on " + getPageTitle());
		wait.hardWait(1);
	}


	public void clickOnButton(String buttonID, String logMessage) {
		wait.waitForLoadPage(wait.getTimeOut() * 1000);
		element("buttonIDClick", buttonID).click();
		logMessage("Click On menu : " + buttonID + " on " + logMessage);
		wait.hardWait(1);
	}

	public void verifyAlertText(String text) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert result = driver.switchTo().alert();
		String  alertText	=	result.getText();
		Assert.assertTrue(alertText.contains(text),alertText+" 'Alert text is not matched with expected text :' "+text);
	}
	public void verifyAlertTextAndHandleIt(String text) {
		try { 
			verifyAlertText(text);  
			handleAlert();
		}catch(TimeoutException e) {
		}
	}

	public void verifyStylesOfElement(String element, String styleName,
			String expectedValue) {
		wait.hardWait(2);
		String val = elementWithOuthighlight("buttonIDClick", element).getCssValue(styleName);
		logMessage("Current CSS " + styleName + " value is " + val);
		Assert.assertEquals(val, expectedValue);
		logMessage("Assert Pass verify styleName as " + val + " and "
				+ expectedValue);
	}

	public void verifySelectOption(String el, String expectedValue) {
		wait.hardWait(2);
		wait.waitForElementToBeVisible(element("buttonIDClick", el));
		scrollDown(element("buttonIDClick", el));
		Select sel = new Select(element("buttonIDClick", el));
		String val 	=	sel.getFirstSelectedOption().getText();
		logMessage("Current Selected option " + val );
		Assert.assertEquals(val, expectedValue);
		logMessage("Assert Pass : verify Selected option " + val + " and "+ expectedValue);
	}

	public void verifyElementAttribute(String ele, String Attribute ,String expected) {
		String actual =	getAttributeOfElement(ele,Attribute);
		Assert.assertEquals(actual, expected);
		logMessage("Assert Pass : Verify Attribute value " + actual + " and "+ expected);

	}

	public String getAttributeOfElement(String ele, String Attribute) {
		wait.waitForLoadPage(wait.getTimeOut() * 1000);
		WebElement element;
		element	=	element("buttonIDClick", ele);
		wait.waitForElementToBeVisible(element);
		String actual	=	element.getAttribute(Attribute);
		logMessage("Assert Pass : Verify Attribute value " + actual);
		return actual;
	}	
	
	public String getAttributeOfElement(WebElement ele, String Attribute) {
		wait.waitForLoadPage(wait.getTimeOut() * 1000);
		WebElement element	=	ele;
		wait.waitForElementToBeVisible(element);
		String actual	=	element.getAttribute(Attribute);
		logMessage("Assert Pass : Verify Attribute value " + actual);
		return actual;
	}

	public void verifyCheckBoxValue(WebElement ele, boolean checked) {
		wait.waitForLoadPage(wait.getTimeOut()*1000);
		wait.waitForElementToBeVisible(ele);
		boolean		val	=	ele.isSelected();
		if(checked == true) {
			if(val	==	true) {
				logMessage("Element Already Selected ");
			}else {
				ele.click();
				logMessage("Selected checkbox ");
			}
		}else {
			if(val	==	false) {
				logMessage("Element is Already not Selected ");
			}else {
				ele.click();
				logMessage("Selected checkbox ");
			}
		}
	}

	public void refreshPage() {
		driver.navigate().refresh();
		wait.waitForLoadPage(wait.getTimeOut()*1000);
	}

	public void verifyCheckBoxValue(String checkBoxID, boolean checked) {
		wait.waitForLoadPage(wait.getTimeOut()*1000);
		wait.waitForElementToBeVisible(element("buttonIDClick", checkBoxID));
		WebElement ele	=	element("buttonIDClick", checkBoxID);
		boolean		val	=	ele.isSelected();
		if(checked == true) {
			if(val	==	true) {
				logMessage("Element Already Selected ");
			}else {
				ele.click();
				logMessage("Selected checkbox ");
			}
		}else {
			if(val	==	false) {
				logMessage("Element is Already not Selected ");
			}else {
				ele.click();
				logMessage("Selected checkbox ");
			}
		}
	}

	public void typeFromActionClass(String string) {
		Actions 	act	=	new Actions(driver);
		act.build();
		act.sendKeys(string).build().perform();
	}
	

	public void DeselecteDropDown(WebElement el) {
		wait.waitForElementToBeVisible(el);
		scrollDown(el);
		Select sel = new Select(el);
		sel.selectByIndex(0);
	}

	public void verifyhandleNewWindowOpenDialog(String windowName) {
		changeWindow(1);
		wait.waitForLoadPage(wait.getTimeOut() * 1000);
		Assert.assertEquals(getPageTitle(),windowName);
		logMessage(""+getPageTitle()+" window page is Displayed" );
		driver.close();
		changeWindow(0); 
	}
	
	public void typeFromActionClass(Keys string) {
		Actions 	act	=	new Actions(driver);
		act.build();
		act.sendKeys(string).build().perform();
	}
}
