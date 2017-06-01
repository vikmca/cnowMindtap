package com.qait.automation.getpageobjects;

import java.util.Iterator;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.qait.automation.utils.SeleniumWait;

public class WebController {
	WebDriver driver;
	protected SeleniumWait wait;
	private String pageName;

	private static final long THREAD_SLEEP = 100;

	public Object executeJavascript(String js, Object... args) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		return executor.executeScript(js, args);

	}

	
	public void switchToLatestWindow() {
		Iterator<String> itr = driver.getWindowHandles().iterator();
		String lastElement = null;
		while (itr.hasNext()) {
			lastElement = itr.next();
		}
		driver.switchTo().window(lastElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.persado.oss.quality.stevia.selenium.core.WebController#
	 * waitForAjaxComplete(long)
	 */

	public void waitForAjaxComplete(long milliseconds) {
		long endTime;
		boolean ajaxComplete = false;
		long startTime = System.currentTimeMillis();
		do {
			try {
				ajaxComplete = ((Boolean)executeJavascript("return jQuery.active == 0")).booleanValue();
				Thread.sleep(THREAD_SLEEP);
			} catch (InterruptedException e) {
				error(e.getMessage());
			}
			endTime = System.currentTimeMillis();
		} while (!ajaxComplete && endTime - startTime <= milliseconds);

		if (!ajaxComplete) {
			warn("The AJAX call was not completed with in " + milliseconds
					+ " ms");
		}
	}
	

	/**
	 * Warn.
	 * 
	 * @param message
	 *            the message
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


	/**
	 * Gets the full xpath.
	 * 
	 * @param locator
	 *            the locator
	 * @return the full xpath
	 */
	public String getFullXpath(String locator) {
//		WebElement element = waitForElement(locator);
		String js = "gPt=function(c){if(c.id!==''){return'id(\"'+c.id+'\")'}if(c===document.body){return c.tagName}var a=0;var e=c.parentNode.childNodes;for(var b=0;b<e.length;b++){var d=e[b];if(d===c){return gPt(c.parentNode)+'/'+c.tagName+'['+(a+1)+']'}if(d.nodeType===1&&d.tagName===c.tagName){a++}}};return gPt(arguments[0]).toLowerCase()";
//		return "//" + executeJavascript(js, element);
		return js;
	}

	/**
	 * Gets the full xpath.
	 * 
	 * @param element
	 *            the Webelement
	 * @return the full xpath
	 */
	public String getFullXpath(WebElement element) {
		String js = "gPt=function(c){if(c.id!==''){return'id(\"'+c.id+'\")'}if(c===document.body){return c.tagName}var a=0;var e=c.parentNode.childNodes;for(var b=0;b<e.length;b++){var d=e[b];if(d===c){return gPt(c.parentNode)+'/'+c.tagName+'['+(a+1)+']'}if(d.nodeType===1&&d.tagName===c.tagName){a++}}};return gPt(arguments[0]).toLowerCase()";
		return "//" + executeJavascript(js, element);
	}
	
	/**
	 * Inject sizzle 1.8.2
	 */
	private static final String HTTPS = "https://";
	private static final String HTTP = "http://";
	
	 private String getSizzleUrl() {
         return System.getProperty("scriptURL");
     }
	public void injectSizzle() {
		String sizzleUrl = getSizzleUrl();
		if (sizzleUrl.startsWith(HTTP)) {
			sizzleUrl = sizzleUrl.substring(HTTP.length());
		} else if  (sizzleUrl.startsWith(HTTPS)) {
			sizzleUrl = sizzleUrl.substring(HTTPS.length());
		}
		
		StringBuilder script = new StringBuilder()
			.append(" var bodyTag = document.getElementsByTagName('body')[0];")
			.append("if (bodyTag) {")
			.append("  var sizzl = document.createElement('script');")
			.append("  sizzl.type = 'text/javascript';")
			.append("  sizzl.src = document.location.protocol + '//").append(sizzleUrl).append("';")
			.append("  bodyTag.appendChild(sizzl);")
			.append("} else if (window.jQuery) { ")
			.append("	 $.getScript(document.location.protocol + '//").append(sizzleUrl).append("');")
			.append("}");
		
		final String stringified = script.toString();
		System.out.println("Executing injection script: {}"+stringified);
		executeJavascript(stringified);
	}
}
