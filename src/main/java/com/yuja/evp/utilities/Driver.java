package com.yuja.evp.utilities;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Driver {
	
	private static final ThreadLocal<RemoteWebDriver> drivers = new ThreadLocal<RemoteWebDriver>();
	
	private Driver() {};
	
	public static void setDriver(Properties config) {
		try {
			System.out.println("Launching Chrome Browser");
			String chrome = "./" + config.getProperty("Browser_Drivers_Path") + "/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chrome);
			System.out.println("Setting up driver...");
			
			// Setting up Chrome options
			ChromeOptions chromeOptions = new ChromeOptions();
		    chromeOptions.addArguments("start-maximized");
			chromeOptions.addArguments("--remote-allow-origins=*");
			DesiredCapabilities chromeCapabilities = new DesiredCapabilities();
			chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			chromeOptions.merge(chromeCapabilities);
			
			// Creating the driver variable
			Thread.sleep(2000);
			Map<String, Object> prefs = new HashMap<String, Object>();
			String userDirectoryPath = Paths.get("").toAbsolutePath().toString();
			String defaultDownloadDirectoryPath = userDirectoryPath + "\\src\\fileResources\\downloads";
			prefs.put("download.default_directory", defaultDownloadDirectoryPath);
			chromeOptions.setExperimentalOption("prefs", prefs);
			
			RemoteWebDriver webDriver = new ChromeDriver(chromeOptions);
			drivers.set(webDriver);
			
		} catch(Exception e){
			System.err.println("Browser driver initiation failed - Exception");
			e.printStackTrace();
		}
	}
	
	public static RemoteWebDriver getDriver()
	{
		return Objects.requireNonNull(drivers.get());
	}
	
	public static void closeBrowser()
	{
		getDriver().quit();
		drivers.remove();
	}

}
