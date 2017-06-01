/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qait.automation.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


//import javax.xml.parsers.SAXParser;

/**
 *
 * @author prashantshukla
 */
public class XmlParser {

	String xmlDirPath = "./src/test/resources/AA-Artifacts/questions-xmls";
	String xmlPath;

	public XmlParser(String xmlName){
		this.xmlPath = this.xmlDirPath + xmlName;
	}

	//    Reading File using BufferReader:

	/**
	 * readXmlFromFile
	 *
	 * @param fileName
	 * @return text
	 */
	public static String readXmlFromFile(String fileName) {

		File file = new File(fileName);
		StringBuilder contents = new StringBuilder();
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String text;

			// repeat until all lines is read
			while ((text = reader.readLine()) != null) {
				contents.append(text).append(
						System.getProperty("line.separator"));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return (contents.toString());
	}
}
