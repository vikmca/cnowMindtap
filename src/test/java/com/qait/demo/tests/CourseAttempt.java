package com.qait.demo.tests;

import static com.qait.automation.utils.YamlReader.getData;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qait.automation.TestSessionInitiator;

public class CourseAttempt {

	TestSessionInitiator test;
	private String appUrl;

	@BeforeClass
	public void start_test_session() {
		test = new TestSessionInitiator();
		appUrl = getData("app_url");
	}

	@Test
	public void Step01_Launch_Application() {
		test.launchApplication(appUrl);
		test.login.loginWithUserNameAndPassword(getData("user_name"), getData("password"));
		test.cnowSelectCourseAction.verifyTitleOfPage(getData("page_Title1"));
	}

	@Test
	public void Step02_NavigateToAssignment() {
		test.cnowSelectCourseAction.selectCourse(getData("course_Name"));
		test.cnowSelectCourseAction.verifyTitleOfPage(getData("page_Title2"));
		test.cnowSelectCourseAction.selectButtonCoursePaymentPage();
		test.cnowSelectCourseAction.changeWindow(1);
		test.cnowSelectCourseAction.verifyTitleOfPage(getData("page_Title3"));
		test.cnowSelectCourseAction.verifyChapterNum(getData("chapter_Num"));
		test.cnowSelectCourseAction.verifyActionOfChapter(getData("action"));
	}

	@Test
	public void Step03_TakeAssignment() {
		test.cnowSelectCourseAction.performActionOnChapter();
		test.cnowSelectCourseAction.validateSelectedAssignment();
		test.cnowSelectCourseAction.verifyTakeResumeRetakeAssignmentBtn();
		test.cnowSelectCourseAction.selectTakeResumeRetakeAssignmentBtn();
		test.cnowSelectCourseAction.changeWindow(1);
		test.cnowSelectCourseAction.verifyAttemptAssignmentPage();
		test.cnowSelectCourseAction.obtainNumberOfQuestions();

	}

//	@Test
	public void Step04_BlankSubmitQuestions() {
		test.cnowSelectCourseAction.attemptAllQuestionsInChapter();
		test.cnowSelectCourseAction.clickSubmitAssignmentForGradingBtn();
		test.cnowSelectCourseAction.verifyWarningPopup();
		test.cnowSelectCourseAction.selectSubmitForGradingBtnOnPopup();
		test.cnowSelectCourseAction.verifyLastAssignmentFinished();
		test.cnowSelectCourseAction.clickAssignmentTab();
		test.cnowSelectCourseAction.verifyActionOfChapter(getData("action2"));
		test.cnowSelectCourseAction.performActionOnChapter();
		test.cnowSelectCourseAction.selectTakeResumeRetakeAssignmentBtn();
		test.cnowSelectCourseAction.changeWindow(1);
		test.cnowSelectCourseAction.verifyAttemptAssignmentPage();
	}

	@Test
	public void Step05_CorrectSubmitQuestions() throws ClientProtocolException, IOException {
		test.cnowSelectCourseAction.attemptQuestionsCorrectly();
		test.cnowSelectCourseAction.clickSubmitAssignmentForGradingBtn();
		test.cnowSelectCourseAction.verifyWarningPopup();
		test.cnowSelectCourseAction.selectSubmitForGradingBtnOnPopup();
		test.cnowSelectCourseAction.verifyLastAssignmentFinished();
		test.cnowSelectCourseAction.clickAssignmentTab();
		test.cnowSelectCourseAction.verifyActionOfChapter(getData("action2"));
		test.cnowSelectCourseAction.performActionOnChapter();
		test.cnowSelectCourseAction.selectTakeResumeRetakeAssignmentBtn();
		test.cnowSelectCourseAction.changeWindow(1);
		test.cnowSelectCourseAction.verifyAttemptAssignmentPage();
		
	}

//	@Test
	public void Step06_IncorrectSubmitQuestions() throws ClientProtocolException, IOException {
		test.cnowSelectCourseAction.attemptQuestionsIncorrectly();
		test.cnowSelectCourseAction.clickSubmitAssignmentForGradingBtn();
		test.cnowSelectCourseAction.verifyWarningPopup();
		test.cnowSelectCourseAction.selectSubmitForGradingBtnOnPopup();
		test.cnowSelectCourseAction.verifyLastAssignmentFinished();
	}

	@AfterClass(alwaysRun = true)
	public void stop_test_session() {
		test.closeBrowserSession();
	}
}
