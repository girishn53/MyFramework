package com.girish.ddf.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;

import org.yaml.snakeyaml.Yaml;

public class Utilities {
	public static String yamlFilePath = "";

	// HSSFWorkbook workbook;
	public Utilities() {
	}

	public static String setYamlFilePath(String filePath) {
		yamlFilePath = "src/test/resources/" + filePath;
		return filePath;
	}

	// @SuppressWarnings({ "unchecked" })
	public static String getYamlValue(String yamlToken) {
		Reader reader = null;
		int tokenCount = 0, i = 0;
		Map map = null;
		StringTokenizer st = new java.util.StringTokenizer(yamlToken, ".");
		try {
			reader = new FileReader(yamlFilePath);
			String val = null;
			Yaml yaml = new Yaml();
			map = (Map) yaml.load(reader);
			tokenCount = st.countTokens();
			for (i = 1; i < tokenCount; i++) {
				String token = st.nextToken();
				map = (Map) map.get(token);
			}
			val = map.get(st.nextToken()).toString();
			return val;
		} catch (Exception e) {
			System.out.println("Yaml file not found!!!\n" + e);
			return "";
		}
	}

	// public static Map getYamlNodesArray(String yamlToken) {
	// Reader reader = null;
	// int tokenCount = 0, i = 0;
	// Map map = null;
	// StringTokenizer st = new java.util.StringTokenizer(yamlToken + ".x",
	// ".");
	// try {
	// reader = new FileReader(yamlFilePath);
	// Yaml yaml = new Yaml();
	// map = (Map) yaml.load(reader);
	// tokenCount = st.countTokens();
	// for (i = 1; i < tokenCount; i++) {
	// String token = st.nextToken();
	// map = (Map) map.get(token);
	// }
	// return map;
	// } catch (Exception e) {
	// System.out.println("Either Yaml file not found or there is problem with
	// token passed!!!\n" + e);
	// return null;
	// }
	// }

}