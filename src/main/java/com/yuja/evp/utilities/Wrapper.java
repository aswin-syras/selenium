package com.yuja.evp.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class Wrapper {

	protected static Properties config, prop;
	public static int Default_Wait_For_Page;
	public static String Environment, Browser, Application_Name, URL, Scenario_Name, Test_Sheet_Path, refTestDataName;

	public void loadConfig() {
		System.out.println("Loading the configuration property file");
		//Loading the configuration properties file
		config = new Properties();
		try {
			config.load(new FileInputStream(new File("./config.properties")));
		}
		catch (FileNotFoundException e) {
			System.err.println("'config.properties' file load Error. Please check the file exist/name of the file");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("'config.properties' file load Error. Please check the Input data of the file");
			e.printStackTrace();
		}
	}

	public void loadObject() {
		System.out.println("Loading objects");
		prop = new Properties();
		System.out.println("loading properties");
		try {
			prop.load(new FileInputStream(new File("./credentials.properties")));
			System.out.println("user details loaded");
			prop.load(new FileInputStream(new File("./urls.properties")));
			System.out.println("urls loaded");
			prop.load(new FileInputStream(new File("./pageObjects.properties")));
			System.out.println("page objects loaded");
			prop.load(new FileInputStream(new File("./config.properties")));
			System.out.println("cofig properties loaded");
		} catch (FileNotFoundException e) {
			System.err.println("'*.properties' multiple file load Error. Please check the file exist/name of the file");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("'*.properties' multiple file load Error. Please check the Input data of the file");
			e.printStackTrace();
		}
	}

	public void suiteVariables() {
		System.out.println("Loading the suite variables");
		// Assigning time out values
		Default_Wait_For_Page = Integer.parseInt(config.getProperty("Default_Wait_For_Page"));
		// Application Name
		Application_Name = config.getProperty("Application_Name");
		// Environment
		Environment = config.getProperty("Environment");
		// Browser to be launched
		Browser = config.getProperty("Browser");
		// URL pick depends on Environment
		URL = prop.getProperty(Environment + ".URL." + Application_Name);
		// Test Case Sheet Path
		Test_Sheet_Path = config.getProperty("Test_Sheet_Path");
	}
	
	public void clearingMemory() {
		System.out.println("Clearing attributes from memory");
		prop.clear();
		prop = null;
		config.clear();
		config = null;
		Default_Wait_For_Page = 0;
		Application_Name = "";
		Environment = "";
		Browser = "";
		URL = "";
		Test_Sheet_Path = "";
		Scenario_Name = "";
	}
	
}
