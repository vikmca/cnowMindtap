package com.qait.automation.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class SeleniumWait{

	WebDriver driver;
	WebDriverWait wait;

	int timeout;

	public SeleniumWait(WebDriver driver, int timeout) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, timeout);
		this.timeout = timeout;
	}

	public int getTimeOut() {
		return timeout;
	}

	/**
	 * Returns webElement found by the locator if element is visible
	 *
	 * @param locator
	 * @return
	 */
	public WebElement getWhenVisible(By locator) {
		WebElement element;
		element = (WebElement) wait.until(ExpectedConditions
				.visibilityOfElementLocated(locator));
		return element;
	}

	public WebElement getWhenClickable(By locator) {
		WebElement element;
		element = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
		return element;
	}

	public boolean waitForPageTitleToBeExact(String expectedPagetitle) {
		return wait.until(ExpectedConditions.titleIs(expectedPagetitle)) != null;
	}

	public boolean waitForPageTitleToContain(String expectedPagetitle) {
		return wait.until(ExpectedConditions.titleContains(expectedPagetitle)) != null;
	}

	public WebElement waitForElementToBeVisible(WebElement element) {
		return (WebElement) wait.until(ExpectedConditions.visibilityOf(element));
	}

	public WebElement waitForElementToBePresent(By locator) {
		return (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public void waitForFrameToBeAvailableAndSwitchToIt(By locator) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public List<WebElement> waitForElementsToBeVisible(List<WebElement> elements) {
		return (List<WebElement>) wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public boolean waitForElementToBeInVisible(By locator) {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator)) != null;
	}

	public WebElement waitForElementToBeClickable(WebElement element) {
		return (WebElement) wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void clickWhenReady(By locator) {
		WebElement element = (WebElement) wait.until(ExpectedConditions
				.elementToBeClickable(locator));
		element.click();
	}


	public void waitForMsgToastToDisappear() {
		int i = 0;
		resetImplicitTimeout(1);
		try {
			while (driver.findElement(By.className("toast-message")).isDisplayed() && i <= timeout) {
				hardWait(1);                
				i++;
			}
		} catch (Exception e) {
		}
		resetImplicitTimeout(timeout);        
	}

	public void waitForElementToDisappear(WebElement elementSimple) {
		int i = 0;
		resetImplicitTimeout(2);
		try {
			while (elementSimple.isDisplayed() && i <= timeout) {
				hardWait(1);                
				i++;
			}
		} catch (Exception e) {
		}
		resetImplicitTimeout(timeout);        
	}

	public void resetImplicitTimeout(int newTimeOut) {
		try {
			driver.manage().timeouts().implicitlyWait(newTimeOut, TimeUnit.SECONDS);
		} catch (Exception e) {	
		}
	}

	// TODO Implement Wait for page load for page synchronizations
	public void waitForPageToLoadCompletely() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//*")));
	}

	public void hardWait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}


	public void waitForLoadPage(long milliseconds) {
		waitjsForPageLoad(milliseconds);
		waitForAjaxComplete(milliseconds);

	}


	public Object executeJavascript(String js, Object... args) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		return executor.executeScript(js, args);

	}
	private static final long THREAD_SLEEP = 200;
	public void waitForAjaxComplete(long milliseconds) {
		long endTime = 0;
		boolean ajaxComplete = false;
		long startTime = System.currentTimeMillis();
		do {
			try {
				ajaxComplete = ((Boolean)executeJavascript("return jQuery.active == 0")).booleanValue();
				Thread.sleep(THREAD_SLEEP);
			} catch (InterruptedException e) {
				warn(e.getMessage());
			}
			catch (Exception e) {
				break;
			}
			
			endTime = System.currentTimeMillis();
		} while (!ajaxComplete && endTime - startTime <= milliseconds);

		if (!ajaxComplete) {
			warn("The AJAX call was not completed with in " + milliseconds
					+ " ms");
		}
//		System.out.println(" Ajax Value : "+ajaxComplete+"	and time : "+(endTime - startTime));
	}

	public void waitjsForPageLoad (long milliseconds) {
		boolean waitJS = false;
		long endTime;
		long startTime	=	System.currentTimeMillis();

		do {
			try {
				waitJS	=	executeJavascript("return document.readyState").equals("complete");
				Thread.sleep(THREAD_SLEEP);
			} catch (InterruptedException e) {
				warn(e.getMessage());
			}

			endTime	=	System.currentTimeMillis();
		} while (!waitJS && endTime - startTime <= milliseconds);
		if (!waitJS) {
			warn("The AJAX call was not completed with in " + milliseconds+" ms");
		}
//		System.out.println(" JS readyState Value : "+waitJS+" and time : "+(endTime - startTime));

	}

	/**
	 * Warn.
	 * 
	 * @param message the message
	 */
	public void warn(String message) {
		Reporter.log("<p class=\"testOutput\" style=\"color:orange; font-size:1em;\">" + message + "</p>");
	}


	/**
	 * Error.
	 * 
	 * @param message
	 *            the message
	 */
	public void error(String message) {
		Reporter.log("<p class=\"testOutput\" style=\"color:red; font-size:1em;\">" + message + "</p>");
	}
}
