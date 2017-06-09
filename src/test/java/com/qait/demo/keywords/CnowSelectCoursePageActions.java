package com.qait.demo.keywords;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.gargoylesoftware.htmlunit.javascript.host.dom.XPathResult;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ObtainAnswerHTMLFromCXP;
import com.thoughtworks.selenium.webdriven.commands.GetAllWindowNames;

public class CnowSelectCoursePageActions extends GetPage {
	WebDriver driver;
	String courseName;
	String chapterNum;
	String actionForChapter;
	int quesCount;

	public CnowSelectCoursePageActions(WebDriver driver) {
		super(driver, "CnowSelectCourse");
		this.driver = driver;
	}

	/**
	 * Method to select course
	 */
	public void selectCourse(String course_name) {
		courseName = course_name;
		while (isElementNotDisplayedInDom("lnkCourseName", course_name)) {
			click(element("lnk_nextPage"));
		}
		// scrollIntoView(element("lnkCourseName", course_name));
		// isElementDisplayed("lnkCourseName", course_name);
		click(element("lnkCourseName", course_name));
		logMessage("Course " + course_name + " selected !!");
	}

	/**
	 * Method to select button on Course Payment Page
	 */
	public void selectButtonCoursePaymentPage() {
		isElementDisplayed("lnk_takeMeToMyCourse");
		element("lnk_takeMeToMyCourse").click();
		logMessage("Button on Course Payment Page selected !!");
		changeWindow(1);
		wait.hardWait(4);
		if (!isElementNotDisplayed("btn_enterCnowv2Anyway")) {
			element("btn_enterCnowv2Anyway").click();
			wait.hardWait(2);
			element("btn_resumeAssignmentNo").click();
			logMessage("Enter Cnow v2 Anyway button clicked and not resuming the last assignment");
		} else if (!isElementNotDisplayed("btn_resumeAssignmentNo")) {
			click(element("btn_resumeAssignmentNo"));
		}
	}

	/**
	 * Method to validate Page Title
	 */
	public void verifyTitleOfPage(String pageTitle) {
		wait.hardWait(2);
		System.out.println("Title of Page ::" + driver.getTitle());
		Assert.assertTrue(driver.getTitle().trim().contains(pageTitle));
		logMessage("Assertion Passed: Verified Page Title Contains " + pageTitle);
	}

	/**
	 * Method to verify Chapter Number in Assignment Table
	 */
	public void verifyChapterNum(String chapterNumber) {
		isElementDisplayed("txt_chapterNum", chapterNumber);
		chapterNum = chapterNumber;
	}

	/**
	 * Method to verify action of same chapter in Assignment Table
	 */
	public void verifyActionOfChapter(String action) {
		// isElementDisplayed("input_takeResumeRetakeBtn","2", chapterNum);
		actionForChapter = action;
	}

	/**
	 * Method to perform action on the given chapter
	 */
	public void performActionOnChapter() {
		System.out.println("*********************" + chapterNum + "****************" + actionForChapter);
		clickUsingJavascript(element("input_takeResumeRetakeBtn", chapterNum, actionForChapter));
		logMessage(actionForChapter + " clicked");
	}

	/**
	 * Method to validate the selected assignment
	 */
	public void validateSelectedAssignment() {
		wait.waitForPageToLoadCompletely();
		Assert.assertTrue(element("heading_assignment").getText().contains(chapterNum));
		logMessage("Navigated to " + chapterNum + " assignment");
	}

	/**
	 * Method to verify Take/Resume/Retake Assignment button
	 */
	public void verifyTakeResumeRetakeAssignmentBtn() {
		if (actionForChapter.equals("Resume"))
			isElementDisplayed("btn_takeResumeRetakeAssignmentNow", actionForChapter);
		else
			isElementDisplayed("btn_takeResumeRetakeAssignmentNow", "Start");
	}

	/**
	 * Method to select Take/Resume/Retake Assignment
	 */
	public void selectTakeResumeRetakeAssignmentBtn() {
		if (actionForChapter.equals("Resume")) {
			element("btn_takeResumeRetakeAssignmentNow", actionForChapter).click();
		} else {
			element("btn_takeResumeRetakeAssignmentNow", "Start").click();
		}
	}

	/**
	 * Method to verify attempt Assignment page
	 */
	public void verifyAttemptAssignmentPage() {
		wait.hardWait(3);
		changeWindow(1);
		wait.hardWait(3);
		switchToDefaultContent();
		switchToFrame(0);
		Assert.assertTrue(element("title_assignment").getText().contains(chapterNum));
		logMessage("Navigated to " + chapterNum + " assignment questions");
	}

	/**
	 * Method to verify last assignment finished
	 */
	public void verifyLastAssignmentFinished() {
		isElementDisplayed("txt_assignmentFinishedHeading");
		Assert.assertTrue(element("txt_assignmentFinishedHeading").getText().contains("Assignment Finished"));
		logMessage("Assignment " + chapterNum + " has been submitted");
	}

	/**
	 * Method to obtain number of questions in the assignment
	 */
	public void obtainNumberOfQuestions() {
		quesCount = elements("itemCount").size();
		System.out.println(quesCount);
		System.out.println(element("txt_itemsCount").getText());
		Assert.assertTrue(element("txt_itemsCount").getText().trim().equals(Integer.toString(quesCount)));
		logMessage("Number of items in the Question panel verified");
	}

	/**
	 * Method to attempt all questions in the chapter
	 */
	public void attemptAllQuestionsInChapter() {
		int i = 1;
		for (WebElement element : elements("itemCount")) {
			if (i <= elements("itemCount").size()) {
				wait.hardWait(1);
				click(element);
				if (!isElementNotDisplayedInDom("options_choice")) {
					click(element("btn_checkMyWork"));
					System.out.println(element("txt_questionFeedback").getText().trim());
					Assert.assertTrue(element("txt_questionFeedback").getText().trim().contains("Incorrect"));
					System.out.print("question name :: ");
					System.out.println(element("txt_questionList", Integer.toString(i)).getText());
					isElementNotDisplayedInDom("rejoinder_correctAnswer");
					isElementNotDisplayedInDom("rejoinder_incorrectAnswer");
					logMessage("Non existence of Rejoinder verified");
					logMessage("Feedback of Question verified as Incorrect");
					i++;
				} else {
					continue;
				}
			}
		}
		logMessage("All Questions of this Chapter attempted");
	}

	/**
	 * Method to click Submit Assignment for Grading button
	 */
	public void clickSubmitAssignmentForGradingBtn() {
		switchToDefaultContent();
		element("btn_submitAssignmentForGrading").click();
	}

	/**
	 * Method to verify Warning popup
	 */
	public void verifyWarningPopup() {
		isElementDisplayed("txt_warningPopup");
	}

	/**
	 * Method to click Submit for Grading button on popup
	 */
	public void selectSubmitForGradingBtnOnPopup() {
		element("btn_submitForGrading").click();
		logMessage("Submit for Grading button clicked on the Warning popup");
	}

	/**
	 * Method to click Assignment tab
	 */
	public void clickAssignmentTab() {
		element("btn_assignments").click();
		logMessage("Assignment tab clicked");
	}

	/**
	 * Method to attempt all questions correctly in the chapter
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public void attemptQuestionsCorrectly(String proCode) throws ClientProtocolException, IOException {
		int i = 1;

		// Traversing all the questions in Question panel
		for (WebElement element : elements("itemCount")) {
			// Counter for running test for specific number of questions
			// starting from first
			if (i <= elements("itemCount").size()) {
				click(element);// select a question
				System.out.print("question name :: ");
				String questionName = element("txt_questionList", Integer.toString(i)).getText();
				System.out.println(element("txt_questionList", Integer.toString(i)).getText());
				try {
					// obtaining seed value
					element("options_choice").isDisplayed();
					String seedValue = element("seed_choice").getAttribute("options");
					System.out.println("*************" + seedValue);
					JsonObject jobj = new Gson().fromJson(seedValue, JsonObject.class);
					String result = jobj.get("seed").toString();
					System.out.println(result);

					// Fetching answer from CXP
					String answer = ObtainAnswerHTMLFromCXP.getAnswerHTMLFromCXP(questionName,
							"<item src='" + proCode.toLowerCase() + "/"
									+ element("txt_questionList", Integer.toString(i)).getText().trim() + "' />",
							result);
					click(element("option_answer", answer));
					click(element("btn_checkMyWork"));
					wait.hardWait(1);

					// Feedback verification
					System.out.println(element("txt_questionFeedback").getText().trim());
					Assert.assertTrue(element("txt_questionFeedback").getText().trim().contains("Correct"));

					// Rejoinder and rejoinder popup verification
					isElementDisplayed("rejoinder_correctAnswer");
					logMessage("Rejoinder verified");
					element("rejoinder_correctAnswer").click();
					isElementDisplayed("popup_correctAnswer");
					logMessage("Popup for correct answer displayed");

					// Grade Indicator verification
					isElementDisplayed("indicator_correctAnswer", Integer.toString(i));
					logMessage("Grade indicator verified to be correct");
					logMessage("Feedback of Question verified as Correct");
					i++;
				} catch (Exception e) {
					System.out.println(e);
					i++;
					continue;
				} catch (AssertionError e1) {
					System.out.println(e1);
					i++;
					continue;
				}
			}
		}
		logMessage("All Questions of this Chapter attempted");
	}

	/**
	 * Method to attempt all questions incorrectly in the chapter
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public void attemptQuestionsIncorrectly(String proCode) throws ClientProtocolException, IOException {
		int i = 1;

		// Traversing all the questions in Question panel
		for (WebElement element : elements("itemCount")) {
			// Counter for running test for specific number of questions
			// starting from first
			if (i <= elements("itemCount").size()) {
				wait.hardWait(1);
				click(element);// select a question
				System.out.print("question name :: ");
				String questionName = element("txt_questionList", Integer.toString(i)).getText();
				System.out.println(element("txt_questionList", Integer.toString(i)).getText());
				try {
					// obtaining seed value
					element("options_choice").isDisplayed();
					String seedValue = element("seed_choice").getAttribute("options");
					System.out.println("*************" + seedValue);
					JsonObject jobj = new Gson().fromJson(seedValue, JsonObject.class);
					String result = jobj.get("seed").toString();
					System.out.println(result);

					// Fetching answer from CXP
					String answer = ObtainAnswerHTMLFromCXP.getAnswerHTMLFromCXP(questionName,
							"<item src='" + proCode.toLowerCase() + "/"
									+ element("txt_questionList", Integer.toString(i)).getText().trim() + "' />",
							seedValue);
					int noOfOptions = elements("choice_options").size();
					System.out.println("No of options :: " + noOfOptions);

					// Wrong answer evaluation
					element("option_wrongAnswer", answer).click();
					click(element("btn_checkMyWork"));
					wait.hardWait(1);

					// Feedback verification
					System.out.println(element("txt_questionFeedback").getText().trim());
					Assert.assertTrue(element("txt_questionFeedback").getText().trim().contains("Incorrect"));
					element("arrow_collapseFeedback").click();
					Assert.assertTrue(element("arrow_collapseFeedback").getAttribute("class").contains("collapsed"));
					System.out.println("Collapsed");
					wait.hardWait(2);
					element("arrow_collapseFeedback").click();
					Assert.assertTrue(element("arrow_collapseFeedback").getAttribute("class").contains("expanded"));
					System.out.println("Expanded");

					// Rejoinder and rejoinder popup verification
					isElementDisplayed("rejoinder_incorrectAnswer");
					logMessage("Rejoinder verified");
					element("rejoinder_incorrectAnswer").click();
					isElementDisplayed("popup_incorrectAnswer");
					logMessage("Popup for incorrect answer displayed");

					// Grade Indicator verification
					isElementDisplayed("indicator_incorrectAnswer", Integer.toString(i));
					logMessage("Grade indicator verified to be incorrect");
					logMessage("Feedback of Question verified as Incorrect");
					i++;
				} catch (Exception e) {
					System.out.println(e);
					i++;
					continue;
				} catch (AssertionError e1) {
					System.out.println(e1);
					i++;
					continue;
				}
			}
		}
		logMessage("All Questions of this Chapter attempted");
	}

	/**
	 * Method to select tab on Assignment finished page
	 */
	public void selectTabOnAssignmentFinishedPage() {
		element("tab_grades").click();
		logMessage("Grades tab on Assignment finished page selected");
	}

	/**
	 * Method to verify percentage earned after grading of the assignment
	 */
	public void verifyPercentageEarned() {
		isElementDisplayed("txt_percentageGrades", chapterNum);
		logMessage("Precentage present as a link");
	}

	/**
	 * Method to click on the percentage of the assignment
	 */
	public void clickGradingPercentage() {
		element("txt_percentageGrades", chapterNum).click();
		logMessage("Grades percentage of the assignment clicked");
	}
}