package com.yuja.evp.pagehelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.yuja.evp.pagetestmethods.MediaLibraryPageTestMethods;
import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;

import helperinterfaces.UICheck;

public class AdminPanelBrandingPageHelpers extends AdminPanelGeneralPageHelpers implements UICheck{
	
	MediaLibraryPageTestMethods medialibrary = new MediaLibraryPageTestMethods();	
	NavigationBarHelpers navigationBar = new NavigationBarHelpers();
	
	public void CheckPageUI() {
		URL = prop.getProperty("URL")+"P/Institution/InstitutionBranding/";
		launchUrl(URL, "Test Automation Enterprise Video Platform");
		String sectionTitle = Driver.getDriver().findElement(By.id("secondPartText")).getText();
		boolean check  = waitForElement(By.id("institutionBranding"),10).isDisplayed();
		if(check && sectionTitle.equals("Branding")) {
			Report.reportStep(Driver.getDriver(), sectionTitle + " Page loaded successfully", "PASS", false);
		} else Report.reportStep(Driver.getDriver(), sectionTitle + "failed to load", "fail", true);	
	}
	
	public void setTheme(String theme) throws InterruptedException {
		CheckPageUI();
		WebElement themeName = Driver.getDriver().findElement(By.id("html5ThemeSelect"));
		Select se = new Select(themeName);
		se.selectByValue(theme);
		JavascriptExecutor js = (JavascriptExecutor)Driver.getDriver();
		js.executeScript("window.scrollBy(0,2500)");
		clickElement("click theme",By.xpath("//*[@id=\"institutionBranding\"]/div[5]/div/button"));
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,-2500)");
		WebElement element = Driver.getDriver().findElement(By.id("defaultHtml5Theme"));
		clickElement("Set default one",element);
		Thread.sleep(1000);
		navigationBar.userLogOut();	
	}
	
	public void checkTheme(String theme) throws InterruptedException {
		CheckPageUI();
		String checktheme = Driver.getDriver().findElement(By.id("html5ThemeSelect")).getText();
		if(checktheme.equals(theme)) {
			Report.reportStep(Driver.getDriver(), "Automation theme ", "PASS", false);
		}
		else { setTheme(theme); }
	}
}
