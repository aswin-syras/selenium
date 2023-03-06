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
	public static WebDriver driver;

	public Wrapper() {
		
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
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./userDetails.properties")));
			prop.load(new FileInputStream(new File("./applicationURL.properties")));
			prop.load(new FileInputStream(new File("./pageObjects.properties")));
			prop.load(new FileInputStream(new File("./config.properties")));

		} catch (FileNotFoundException e) {
			System.err.println("'*.properties' multiple file load Error. Please check the file exist/name of the file");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("'*.properties' multiple file load Error. Please check the Input data of the file");
			e.printStackTrace();
		}
	}

	public void suiteVariables() {
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

	public boolean launchBrowser(String browserName) {
		boolean bReturn = false;

		try {

			switch (browserName.toUpperCase()) {
			case "CHROME":
				
				System.out.println("Launching Chrome Browser");
				String chrome = "./" + config.getProperty("Browser_Drivers_Path") + "/chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", chrome);

				// Cleaning the Chrome Memory
				Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");

				// Setting up IE chrome options
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("start-maximized");
				
				// Creating the driver variable
				Thread.sleep(2000);
				Map<String, Object> prefs = new HashMap<String, Object>();
				
				String userDirectoryPath = Paths.get("").toAbsolutePath().toString();
				String defaultDownloadDirectoryPath = userDirectoryPath + "\\src\\fileResources\\downloads";
				
				prefs.put("download.default_directory", defaultDownloadDirectoryPath);
				chromeOptions.setExperimentalOption("prefs", prefs);
				driver = new ChromeDriver(chromeOptions);
				Thread.sleep(2000);
				driver.manage().timeouts().implicitlyWait(Default_Wait_For_Page, TimeUnit.SECONDS);
				bReturn = true;
				break;

			case "IE":

				System.out.println("Launching IE Browser");
				String IE = "./" + config.getProperty("Browser_Drivers_Path") + "/IEDriverServer.exe";
				System.setProperty("webdriver.ie.driver", IE);

				// Cleaning the iexplore memory
				Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
				Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");

				// Setting up IE browser options
				InternetExplorerOptions ieOptions = new InternetExplorerOptions();
				ieOptions.introduceFlakinessByIgnoringSecurityDomains();
				ieOptions.requireWindowFocus();
				ieOptions.ignoreZoomSettings();

				Thread.sleep(2000);
				// Creating the driver variable
				driver = new InternetExplorerDriver(ieOptions);
				Thread.sleep(2000);

				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Default_Wait_For_Page, TimeUnit.SECONDS);

				System.out.println(driver.getTitle());

				WebElement zoomSettings = driver.findElement(By.tagName("html"));
				zoomSettings.sendKeys(Keys.chord(Keys.CONTROL, "0"));

				bReturn = true;
				break;

			case "FIREFOX":

				System.out.println("Launching FireFox Browser");
				String fireFox = "./" + config.getProperty("Browser_Drivers_Path") + "/geckodriver.exe";
				System.setProperty("webdriver.gecko.driver", fireFox);

				FirefoxOptions firefoxOptions = new FirefoxOptions();

				// Creating the driver variable
				driver = new FirefoxDriver(firefoxOptions);
				driver.manage().timeouts().implicitlyWait(Default_Wait_For_Page, TimeUnit.SECONDS);

				bReturn = true;
				break;
			}
		} catch (Exception e) {
			System.err.println("Browser driver initiation failed - Exception");
			e.printStackTrace();
		}
		return bReturn;
	}


	// Clearing variables memory
	public void clearingMemory() {
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
