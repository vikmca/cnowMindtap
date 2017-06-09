package com.qait.automation.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.ContextClickAction;
import org.openqa.selenium.interactions.touch.ScrollAction;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import com.google.common.io.Files;

public class ObtainAnswerHTMLFromCXP {
	public static String getAnswerHTMLFromCXP(String question, String xml, String seed)
			throws ClientProtocolException, IOException {
		String downloadLink = "http://dev-trunk-cxp.cengage.info/itemservice/sample/start.jsp";
		HttpGet httpget = new HttpGet(downloadLink);
		HttpPost httpPost = new HttpPost(downloadLink);
		StringBuilder builder = new StringBuilder();

		HttpContext localContext = new BasicHttpContext();
		CookieStore cookieStore = new BasicCookieStore();
		HttpResponse postResponse = null;

		httpget.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpget.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0");
		httpget.addHeader("Accept-Encoding", "gzip,deflate");
		httpget.addHeader("Accept-Language", "en-US,en;q=0.5");
		httpget.addHeader("Referer", "http://dev-trunk-cxp.cengage.info/itemservice/sample/start.jsp");
		httpget.addHeader("Host", "dev-trunk-cxp.cengage.info");
		httpget.addHeader("DNT", "1");
		httpget.addHeader("Connection", "keep-alive");
		CloseableHttpClient httpclient = HttpClientBuilder.create()
				.setRetryHandler(new DefaultHttpRequestRetryHandler(3, false)).build();
		localContext.setAttribute(HttpClientContext.COOKIE_SPEC, cookieStore);
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("showGradeIndicators", "true"));
		urlParameters.add(new BasicNameValuePair("showCorrectAnswer", "true"));
		urlParameters.add(new BasicNameValuePair("showCorrectAnswerInitially", "true"));
		urlParameters.add(new BasicNameValuePair("enableRerenderShowCorrect", "true"));
		urlParameters.add(new BasicNameValuePair("enableRerenderSpecifySeed", "true"));
		urlParameters.add(new BasicNameValuePair("enableRerenderResetAttempts", "true")); // 1748-3190
		urlParameters.add(new BasicNameValuePair("showRejoinders", "true")); // 8
		urlParameters.add(new BasicNameValuePair("showSolution", "true")); // 4
		urlParameters.add(new BasicNameValuePair("showPostSubmissionFeedback", "true"));
		urlParameters.add(new BasicNameValuePair("showCorrectChoiceWhenIncorrect", "true")); // 1748-3190
		urlParameters.add(new BasicNameValuePair("showCorrectChoiceWhenSkipped", "true")); // 8
		urlParameters.add(new BasicNameValuePair("showAuthorDebugInfo", "true")); // 4
		urlParameters.add(new BasicNameValuePair("showHints", "true"));
		urlParameters.add(new BasicNameValuePair("showVideoHelp", "true"));
		urlParameters.add(new BasicNameValuePair("showOutcomes", "true"));
		urlParameters.add(new BasicNameValuePair("showAdditionalResources", "true"));
		urlParameters.add(new BasicNameValuePair("enableStepByStepTutorial", "true"));
		urlParameters.add(new BasicNameValuePair("maxCheckMyWorkAttempts", "false"));
		urlParameters.add(new BasicNameValuePair("disableRemoteItemCaching", "true"));
		urlParameters.add(new BasicNameValuePair("customStyle", "Default"));
		urlParameters.add(new BasicNameValuePair("problemTypeSkin", "Release1"));
		urlParameters.add(new BasicNameValuePair("mathPaletteTransport", "mathml"));
		urlParameters.add(new BasicNameValuePair("initialAnswer", ""));
		urlParameters.add(new BasicNameValuePair("contentRetrievalTimestamp", ""));
		urlParameters.add(new BasicNameValuePair("submitMode", "per-item"));
		urlParameters.add(new BasicNameValuePair("seed", seed));
		urlParameters.add(new BasicNameValuePair("assetURL", ""));
		urlParameters.add(new BasicNameValuePair("itemLocatorReference", ""));
		urlParameters.add(new BasicNameValuePair("q4ItemLocatorSource", ""));
		urlParameters.add(new BasicNameValuePair("resourceURL", ""));
		urlParameters.add(new BasicNameValuePair("autoSaveInterval", ""));
		urlParameters.add(new BasicNameValuePair("incorrectAnswerWarnings", ""));
		urlParameters.add(new BasicNameValuePair("incorrectAnswerMessage", ""));

		urlParameters.add(new BasicNameValuePair("maxAttempts", ""));
		urlParameters.add(new BasicNameValuePair("olrUserName", ""));
		urlParameters.add(new BasicNameValuePair("olrPassword", ""));
		urlParameters.add(new BasicNameValuePair("olrToken", ""));
		urlParameters.add(new BasicNameValuePair("userName", ""));
		urlParameters.add(new BasicNameValuePair("pluginURL", ""));
		urlParameters.add(new BasicNameValuePair("locale", ""));
		xml = StringUtils.replace(xml, "\"", "'");
		// System.out.println("^^^^^^^^^^^^^^^^^^^" + xml);
		urlParameters.add(new BasicNameValuePair("items", xml));
		urlParameters.add(new BasicNameValuePair("version", "2"));
		urlParameters.add(new BasicNameValuePair("itemsFileUrl", ""));

		urlParameters.add(new BasicNameValuePair("pullItemXml", "xml"));
		urlParameters.add(new BasicNameValuePair("userName", ""));

		HttpClientContext context = HttpClientContext.create();
		httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));

		httpPost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0");
		httpPost.addHeader("Accept-Encoding", "gzip,deflate");
		httpPost.addHeader("Accept-Language", "en-US,en;q=0.5");
		httpPost.addHeader("Referer", "http://dev-trunk-cxp.cengage.info/itemservice/sample/start.jsp");
		httpPost.addHeader("Host", "dev-trunk-cxp.cengage.info");
		httpPost.addHeader("DNT", "1");
		httpPost.addHeader("Connection", "keep-alive");

		postResponse = httpclient.execute(httpPost, context);
		String respones = StringUtils.substringBetween(postResponse.toString(), "HttpResponseProxy", "[Date");
		// System.out.println("(((((((((((((((((" + respones);
		String takeId = StringUtils.substringBetween(postResponse.toString(), "Location:", ", Content");
		String sample = "http://dev-trunk-cxp.cengage.info/itemservice/sample/";
		takeId = StringUtils.deleteWhitespace(takeId);
		// System.out.println("#########################################" +
		// takeId);
		httpget = new HttpGet(sample + takeId);
		// System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%" + httpget);
		postResponse = httpclient.execute(httpget);

		BufferedReader rd = new BufferedReader(new InputStreamReader(postResponse.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		Document doc = Jsoup.parse(result.toString());
		String grade = doc.getElementsByClass("ci-grade").get(0).attr("value");
		//
		// System.out.println("**************Grading Information: " +
		// grade);
		String index = "";
		// index = StringUtils.substringBetween(grade,
		// "user-raw-answer\":[\"c=", "&");
		index = StringUtils.substringBetween(grade, "correct-answer\":[\"", "\"]");
		// System.out.println("--------index----------" + index);
		index = StringUtils.remove(index, "]").trim();
		index = StringUtils.remove(index, "\"").trim();
		// System.out.println("index: " + index);
		if (index.contains("post-submission-feedback")) {
			index = StringUtils.substringBefore(index, ",");
		}

		String answer = index;
		if (answer.length() == 1)
			answer = answer.concat(".");
		System.out.println("Answer from CXP: " + answer);
		if (question.contains("TF")) {
			// System.out.println("Answer HTML from Geyser: " + doc);

			return answer;
		} else if (question.contains("MC")) {
			String answerText = doc.select("span:containsOwn(" + answer + ")").first().nextSibling().toString();
			answerText = StringUtils.substringBetween(answerText, ">", "<");
			System.out.println("Answer to MCQ from CXP: " + answerText);
			return answerText;
		} else
			return "";
	}

}
