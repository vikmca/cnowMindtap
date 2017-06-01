package com.qait.automation.utils;

import static com.qait.automation.utils.DataReadWrite.getProperty;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import net.mindengine.galen.api.Galen;
import net.mindengine.galen.reports.GalenTestInfo;
import net.mindengine.galen.reports.HtmlReportBuilder;
import net.mindengine.galen.reports.TestReport;
import net.mindengine.galen.reports.model.LayoutReport;
import net.mindengine.galen.validation.ValidationResult;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.qait.automation.getpageobjects.Tiers;

public class LayoutValidation {

	WebDriver driver;
	String PageName;
	String tier;
	private String filepath = "src/test/resources/PageObjectRepository/";
	String stepName;
	
	// Creating a list of tests
	LinkedList<GalenTestInfo> tests = new LinkedList<GalenTestInfo>();
	
		
	public TestReport registerTest(Method method) {
	GalenTestInfo testInfo = GalenTestInfo.fromMethod(method);
	tests.add(testInfo);
	return testInfo.getReport();
	}
	
	public List<GalenTestInfo> getAllTests() {
	return tests;
	}

	public LayoutValidation(WebDriver driver, String pageName) {
		this.driver = driver;
		this.PageName = pageName;
		setTier();
	}
	public void testStepName(String testStepName) {
		stepName = testStepName;
	}

	public void checklayout(List<String> tagsToBeTested) {
		try {
			// Galen.dumpPage(this.driver, this.PageName, this.filepath +
			// this.tier + this.PageName + ".spec", this.filepath + this.tier +
			// this.PageName + "dump");
//			Galen.dumpPage(this.driver, this.PageName, this.filepath
//					+ this.tier + this.PageName + ".spec", this.filepath
//					+ this.tier + this.PageName + "dump");
//		
			// Executing layout check and obtaining the layout report
			LayoutReport layoutReport = Galen.checkLayout(this.driver,
					this.filepath + this.tier + this.PageName + ".spec",
					tagsToBeTested, null, null, null);		

			// Creating an object that will contain the information about the
			// test
			GalenTestInfo test = GalenTestInfo.fromString(stepName+" - "+ tagsToBeTested+ " - "+ this.PageName);
	
			// Adding layout report to the test report
			test.getReport().layout(layoutReport,
					this.PageName + " - layout test");
			tests.add(test);

			// Exporting all test reports to html
			new HtmlReportBuilder().build(tests, "target/galen-reports");

			if (layoutReport.errors() > 0) {
				Reporter.log("There are Layout Errors on the page:- "
						+ this.PageName + "!!! The Errors are for ", true);
				for (ValidationResult error : layoutReport
						.getValidationErrorResults()) {
					// for (String errorMsg : error.) {
					Reporter.log(error.toString(), true);
					// }
					
				}
				 Assert.fail();
			}

		} catch (IOException ex) {
			Reporter.log(ex.getLocalizedMessage(), true);
		}
	}
	
	
	private void setTier() {
		switch (Tiers.valueOf(getProperty("Config.properties", "tier"))) {
		case production:
		case PROD:
		case PRODUCTION:
		case Production:
		case prod:
			this.tier = "PROD/";
			break;
		case pristine:
		case PR:
		case PRISTINE:
		case Pristine:
		case pr:
			this.tier = "PR/";
			break;
		case qa:
		case QA:
		case Qa:
			this.tier = "QA/";
			break;
		case Dev:
		case DEV:
		case dev:
			this.tier = "DEV/";
			break;
		case mice:
		case MICE:
		case Mice:
			this.tier = "MICE/";
			break;
		case ItemService:
		case ITEMSERVICE:
		case itemservice:
			this.tier = "QA/";
			break;
		case activityservice:
		case ACTIVITYSERVICE:
		case Activityservice:
		case ActivityService:
			tier = "QA/";
			break;
		default:
			tier = "QA/";
			break;
		}
	}



}
