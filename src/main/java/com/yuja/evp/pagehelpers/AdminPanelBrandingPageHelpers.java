package com.yuja.evp.pagehelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.yuja.evp.pagetestmethods.MediaLibraryPageTestMethods;
import helperinterfaces.UICheck;

public class AdminPanelBrandingPageHelpers extends AdminPanelGeneralPageHelpers implements UICheck{
	
	MediaLibraryPageTestMethods medialibrary = new MediaLibraryPageTestMethods();	
	NavigationBarHelpers navigationBar = new NavigationBarHelpers();
	
	public void CheckPageUI() {
		URL = "https://staging-demo.yuja.com/P/Institution/InstitutionBranding/";
		launchUrl(URL, "Xavier University Enterprise Video Platform");
		String sectionTitle = driver.findElement(By.id("secondPartText")).getText();
		boolean check  = waitForElement(By.id("institutionBranding"),10).isDisplayed();
		if(check && sectionTitle.equals("Branding")) {
			reportStep(sectionTitle + " Page loaded successfully", "PASS", false);
			System.out.println(sectionTitle);
		} else reportStep(sectionTitle + "failed to load", "fail", true);	
	}
	
	public void setTheme(String theme) throws InterruptedException {
		CheckPageUI();
		WebElement themeName = driver.findElement(By.id("html5ThemeSelect"));
		Select se = new Select(themeName);
		se.selectByValue(theme);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,2500)");
		clickElement("click theme",By.xpath("//*[@id=\"institutionBranding\"]/div[5]/div/button"));
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,-2500)");
		WebElement element = driver.findElement(By.id("defaultHtml5Theme"));
		clickElement("Set default one",element);
		Thread.sleep(1000);
		navigationBar.userLogOut();	
	}
	
	public void checkTheme(String theme) throws InterruptedException {
		CheckPageUI();
		String checktheme = driver.findElement(By.id("html5ThemeSelect")).getText();
		if(checktheme.equals(theme)) {
			reportStep("Automation theme ", "PASS", false);
		}
		else { setTheme(theme); }
	}
}
