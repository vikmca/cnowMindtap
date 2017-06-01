package com.qait.automation.getpageobjects;

import static com.qait.automation.getpageobjects.ObjectFileReader.getELementFromFile;
import static com.qait.automation.utils.DataReadWrite.getProperty;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Set;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import com.qait.automation.utils.LayoutValidation;

public class GetPage extends BaseUi {

	protected WebDriver driver;
	String pageName;
	LayoutValidation layouttest;

	public GetPage(WebDriver driver, String pageName) {
		super(driver, pageName);
		this.driver = driver;
		this.pageName = pageName;
		layouttest = new LayoutValidation(driver, pageName);

	}

	public void scrollIntoView(WebElement element){
		  ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		  wait.waitForElementToBeVisible(element);
//		  element.click();
	}
	public void testStepName(String testStepName) {
		layouttest.testStepName(testStepName);
	}

	public void stepStartMessage(String testStepName) {
		Reporter.log(" ", true);
		Reporter.log(" STARTING TEST STEP:- " + testStepName.toUpperCase() + " ", true);
		testStepName(testStepName);
		Reporter.log(" ", true);
	}

	public void testPageLayout(List<String> tagsToBeTested){
		layouttest.checklayout(tagsToBeTested);
	}
	
	protected boolean isElementNotDisplayedInDom(String elementName) {
		boolean result;
		try {
			wait.resetImplicitTimeout(2);
			driver.findElement(getLocator(elementName));
			result = false;
		} catch (NoSuchElementException excp) {
			result = true;
		}
	// assertTrue(result, "ASSERTION Failed: element '" + elementName
	// + "' is displayed.");
	wait.resetImplicitTimeout(wait.getTimeOut());
	logMessage("ASSERTION Passed: element " + elementName + " is not displayed as expected!!!");
	return result;
}

	
	protected boolean isElementNotDisplayed(String elementName) {
		boolean result;
		try {
			wait.waitForPageToLoadCompletely();
			driver.findElement(getLocator(elementName));
			result = false;
		} catch (NoSuchElementException excp) {
			result = true;
		}
		logMessage("ASSERTION Passed: element " + elementName + " is not displayed as expected!!!");
		return result;
	}

	public void testPageLayout(String tagToBeTested){
		wait.hardWait(1);
		scrollTop();
		testPageLayout(Arrays.asList(tagToBeTested));
	}

	public void testPageLayout(){
		testPageLayout(Arrays.asList(getProperty("browser")));
	}

	// TODO: put this in right place, create dedicated class for frame and
	// window handlers
	protected void switchToNestedFrames(String frameNames) {
		switchToDefaultContent();
		String[] frameIdentifiers = frameNames.split(":");
		for (String frameId : frameIdentifiers) {
			wait.waitForFrameToBeAvailableAndSwitchToIt(getLocator(frameId
					.trim()));
		}
	}

	protected WebElement element(String elementToken) {
		highlight(element(elementToken, ""));
		return element(elementToken, "");
	}

	protected WebElement element(String elementToken, String replacement1, String replacement2)
			throws NoSuchElementException {
		WebElement elem = null;
		try {
			elem = wait.waitForElementToBeVisible(driver
					.findElement(getLocator(elementToken, replacement1, replacement2)));

		} catch (NoSuchElementException excp) {
			fail("FAILED: Element "+ elementToken + " with the Locator "+getLocator(elementToken, replacement1, replacement2)+" not found on the " + this.pageName + " !!!");
		}
		return elem;
	}
	
	protected WebElement elementWithOuthighlight(String elementToken, String replacement) throws NoSuchElementException {
		WebElement elem = null;
		try {
			elem = wait.waitForElementToBeVisible(driver.findElement(getLocator(elementToken, replacement)));
		} catch (NoSuchElementException excp) {
			fail("FAILED: Element "+ elementToken + " with the Locator "+getLocator(elementToken, replacement)+"  not found on the " + this.pageName + " !!!");
		}
		return elem;
	}

	protected WebElement element(String elementToken, String replacement) throws NoSuchElementException {
		WebElement elem = null;
		try {
			elem = wait.waitForElementToBeVisible(driver.findElement(getLocator(elementToken, replacement)));
			highlight(driver.findElement(getLocator(elementToken, replacement)));
		} catch (NoSuchElementException excp) {
			fail("FAILED: Element "+ elementToken + " with the Locator "+getLocator(elementToken, replacement)+"  not found on the " + this.pageName + " !!!");
		}
		return elem;
	}

	protected WebElement elementSimple(String elementToken) {
		WebElement elem = null;
		try {
			elem	=	driver.findElement(getLocator(elementToken));
			highlight(driver.findElement(getLocator(elementToken)));
		} catch (NoSuchElementException excp) {

		}
		return elem;
	}
	
	protected List<WebElement> elementsWithWait(String elementToken, String replacement) {
		return driver.findElements(getLocator(elementToken, replacement));
	}

	protected List<WebElement> elements(String elementToken, String replacement) {
		return wait.waitForElementsToBeVisible(driver.findElements(getLocator(elementToken, replacement)));
	}
	
	protected List<WebElement> elementsWithOutWait(String elementToken, String replacement) {
		return driver.findElements(getLocator(elementToken, replacement));
	}

	protected List<WebElement> elements(String elementToken, String replacement1, String replacement2) {
		return driver.findElements(getLocator(elementToken, replacement1, replacement2));
	}

	protected List<WebElement> elements(String elementToken) {
		return elements(elementToken, "");
	}

	protected void isStringMatching(String actual, String expected) {
		Assert.assertEquals(actual, expected);
		logMessage("ACTUAL STRING : " + actual);
		logMessage("EXPECTED STRING :" + expected);
		logMessage("String compare Assertion passed.");
	}

	protected boolean isElementDisplayed(String elementName,String elementTextReplace) {
		wait.waitForElementToBeVisible(element(elementName, elementTextReplace));
		boolean result = element(elementName, elementTextReplace).isDisplayed();
		highlight(element(elementName, elementTextReplace));
		assertTrue(result, "Assertion Failed: element '" + elementName
				+ "with text " + elementTextReplace + "' is not displayed.");
		logMessage("Assertion Passed: element " + elementName + " with text "
				+ elementTextReplace + " is displayed.");
		return result;
	}

	protected boolean isElementDisplayed(String elementName, String elementTextReplace, String elementTextReplace2) {
		wait.waitForElementToBeVisible(element(elementName, elementTextReplace,elementTextReplace2));
		boolean result = element(elementName, elementTextReplace,elementTextReplace2).isDisplayed();
		highlight(element(elementName, elementTextReplace,elementTextReplace2));
		assertTrue(result, "Assertion Failed: element '" + elementName
				+ "with text " + elementTextReplace + "' is not displayed.");
		logMessage("Assertion Passed: element " + elementName + " with text "
				+ elementTextReplace + " is displayed.");
		return result;
	}

	protected void verifyElementText(String elementName, String expectedText) {
		wait.waitForElementToBeVisible(element(elementName));
		assertEquals(element(elementName).getText(), expectedText,
				"Assertion Failed: element '" + elementName
				+ "' Text is not as expected: ");
		logMessage("Assertion Passed: element " + elementName
				+ " is visible and Text is " + expectedText);
	}

	protected boolean isElementDisplayed(String elementName) {
		wait.waitForElementToBeVisible(element(elementName));
		boolean result = element(elementName).isDisplayed();
		highlight(element(elementName));
		assertTrue(result, "Assertion Failed: element '" + elementName
				+ "' is not displayed.");
		logMessage("Assertion Passed: element " + elementName
				+ " is displayed.");
		return result;
	}

	protected By getLocator(String elementToken) {
		return getLocator(elementToken, "");
	}

	protected By getLocator(String elementToken, String replacement) {
		String[] locator = getELementFromFile(this.pageName, elementToken);
		locator[2] = locator[2].replaceAll("\\#\\{.+\\}", replacement);
		return getBy(locator[1].trim(), locator[2].trim());
	}

	protected By getLocator(String elementToken, String replacement1, String replacement2) {
		String[] locator = getELementFromFile(this.pageName, elementToken);
		locator[2] = locator[2].replaceAll("\\%\\{.+\\}", replacement2);
		locator[2] = locator[2].replaceAll("\\#\\{.+\\}", replacement1);
		return getBy(locator[1].trim(), locator[2].trim());
	}

	public void changeWindow(String windowName) {
		String window = null;
		for (String handle1 : driver.getWindowHandles()) {
			driver.switchTo().window(handle1);
			if (driver.getTitle().equalsIgnoreCase(windowName)) {
				window = driver.getWindowHandle();
			}
		}
		driver.switchTo().defaultContent();
		driver.switchTo().window(window);
		logMessage("User is on the "+windowName);
	}

	public void changeWindow(int i) {
		//String currentWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		if (i > 0) {
			for (int j = 0; j < 5; j++) {
				if (windows.size() < 2) {
					try {
						Thread.sleep(2000);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else {
					break;
				}
			}
			windows = driver.getWindowHandles();
		}
		String wins[] = windows.toArray(new String[windows.size()]);
		String browser = getProperty("./Config.properties", "browser");
		if (browser.equalsIgnoreCase("ie")
				|| browser.equalsIgnoreCase("internetexplorer")
				|| browser.equalsIgnoreCase("internet explorer")) {
			driver.switchTo().window(wins[i]);
			driver.switchTo().window(wins[i]);
			driver.switchTo().window(wins[i]);
			System.out.println("Switch window three time for browser :"
					+ browser);
			
		} else
			driver.switchTo().window(wins[i]);

		while (driver.getTitle().equalsIgnoreCase("about:blank")
				|| driver.getTitle().equalsIgnoreCase("")) {
			wait.hardWait(2);
		}
		driver.manage().window().maximize();
	}

	private By getBy(String locatorType, String locatorValue) {
		switch (Locators.valueOf(locatorType)) {
		case id:
			return By.id(locatorValue);
		case xpath:
			return By.xpath(locatorValue);
		case css:
			return By.cssSelector(locatorValue);
		case name:
			return By.name(locatorValue);
		case classname:
			return By.className(locatorValue);
		case linktext:
			return By.linkText(locatorValue);
		default:
			return By.id(locatorValue);
		}
	}
}