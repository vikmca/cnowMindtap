package com.qait.automation.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.apache.http.client.methods.HttpGet;

public class HttpClient {

	HttpGet httpget;
	URI uri;



	public static final String getPostString(String url, String param) {
		String res = "";
		BufferedReader in = null;
		try {
			URL U = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) U
					.openConnection();
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestMethod("POST");
			connection.setRequestProperty("contentType", "UTF-8");
			connection.setDoOutput(true);
			connection.setRequestProperty("Accept-Charset", "utf-8");
			byte[] bypes = param.getBytes();
			connection.getOutputStream().write(bypes);
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				res += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = "";
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return res;
	}

	public static final String postData(String url, String param) {
		String res = "";
		BufferedReader in = null;
		try {
			URL U = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) U
					.openConnection();
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestMethod("POST");
			connection.setRequestProperty("contentType", "UTF-8");
			connection.setDoOutput(true);
			connection.setRequestProperty("Accept-Charset", "utf-8");
			byte[] bypes = param.getBytes();
			connection.getOutputStream().write(bypes);
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				res += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = "";
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return res;
	}
}
