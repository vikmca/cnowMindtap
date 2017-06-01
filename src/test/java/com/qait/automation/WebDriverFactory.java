/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qait.automation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverFactory {

	private static String browser,seleniumserver;
	private static DesiredCapabilities capabilities = new DesiredCapabilities();



	public WebDriver getDriver(Map<String, String> seleniumconfig) {
		seleniumserver = System.getProperty("seleniumserver", seleniumconfig.get("seleniumserver"));
		browser = System.getProperty("browser", seleniumconfig.get("browser"));

		//		  if (browser == null)
		//			   browser = seleniumconfig.get("browser").toString();
		//		  if(seleniumserver==null)
		//			   seleniumserver = seleniumconfig.get("seleniumserver").toString();

		if (seleniumserver.equalsIgnoreCase("local")) {
			if (browser.equalsIgnoreCase("firefox")) {
				return getFirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				return getChromeDriver(seleniumconfig.get("driverpath")
						.toString().trim());
			} else if (browser.equalsIgnoreCase("Safari")) {
				return getSafariDriver();
			} else if ((browser.equalsIgnoreCase("ie"))
					|| (browser.equalsIgnoreCase("internetexplorer"))
					|| (browser.equalsIgnoreCase("internet explorer"))) {
				return getInternetExplorerDriver(seleniumconfig.get(
						"driverpath").toString());
			}
		}
		if (seleniumserver.equalsIgnoreCase("remote")) {
			return setRemoteDriver(seleniumconfig);
		}
		return new FirefoxDriver();
	}

	private WebDriver setRemoteDriver(Map<String, String> selConfig) {
		DesiredCapabilities cap = null;
		browser = selConfig.get("browser").toString();
		if (browser.equalsIgnoreCase("firefox")) {
			cap = DesiredCapabilities.firefox();
		} else if (browser.equalsIgnoreCase("chrome")) {
			cap = DesiredCapabilities.chrome();
		} else if (browser.equalsIgnoreCase("Safari")) {
			cap = DesiredCapabilities.safari();
		} else if ((browser.equalsIgnoreCase("ie"))
				|| (browser.equalsIgnoreCase("internetexplorer"))
				|| (browser.equalsIgnoreCase("internet explorer"))) {
			cap = DesiredCapabilities.internetExplorer();
		}
		String seleniuhubaddress = selConfig.get("seleniumserverhost");
		URL selserverhost = null;
		try {
			selserverhost = new URL(seleniuhubaddress);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		cap.setJavascriptEnabled(true);
		return new RemoteWebDriver(selserverhost, cap);
	}


	private static WebDriver getChromeDriver(String driverpath) {
		if(System.getProperty("os.name").contains("Windows")){			
			System.setProperty("webdriver.chrome.driver", driverpath+".exe");
			System.out.println(driverpath+".exe");
		}if(System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.chrome.driver", driverpath);
		}if(System.getProperty("os.name").contains("mac")) {
			System.setProperty("webdriver.chrome.driver", driverpath+"_mac");
		}
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability("nativeEvents", true);
		capabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
		capabilities.setCapability(CapabilityType.ENABLE_PROFILING_CAPABILITY, true);
		capabilities.setCapability(CapabilityType.PAGE_LOADING_STRATEGY, true);
		capabilities.setCapability("enablePage", true);
		capabilities.setCapability("bufferUsageReportingInterval", true);
		capabilities.setCapability("locationContextEnabled", true);	
		capabilities.setCapability("chrome.switches","--disable-extensions");
		return new ChromeDriver(capabilities);
	}

	private static WebDriver getInternetExplorerDriver(String driverpath) {
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		System.setProperty("webdriver.ie.driver", "src/test/resources/drivers/IEDriverServer.exe");
		capabilities.setBrowserName("iexplore");
		capabilities.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		capabilities.setCapability("ignoreZoomSetting", true);
		capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability("ignoreProtectedModeSettings", true);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability("requireWindowFocus", true);
		capabilities.setCapability("enablePersistentHover", false);
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		capabilities.setCapability("ie.ensureCleanSession", true);
		return new InternetExplorerDriver(capabilities);
	}

	private static WebDriver getSafariDriver() {
		return new SafariDriver();
	}

	private static WebDriver getFirefoxDriver() {
		//		File pathToBinary = new File("C:\\Users\\umangtiwari\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		//		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);

		FirefoxProfile profile = new FirefoxProfile();
		System.out.println(profile.areNativeEventsEnabled());
		profile.setEnableNativeEvents(true);
		System.out.println("areNativeEventsEnabled : "+profile.areNativeEventsEnabled());
		profile.setPreference("browser.cache.disk.enable", false);
		profile.setPreference("dom.max_chrome_script_run_time", 0);
		profile.setPreference("dom.max_script_run_time", 0);
		profile.setPreference("network.automatic-ntlm-auth.allow-non-fqdn", "true");

		return new FirefoxDriver(profile);

	}
}