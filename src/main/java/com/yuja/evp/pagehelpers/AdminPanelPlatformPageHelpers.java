package com.yuja.evp.pagehelpers;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;
import com.yuja.evp.utilities.Helpers;

import helperinterfaces.UICheck;

public class AdminPanelPlatformPageHelpers extends Helpers implements UICheck {

	private SignInPageHelpers signInPage = new SignInPageHelpers();

	public void navigateToAdminPanelPlatformPageUserLogin(String userName, String password) {
		signInPage.navigateToLoginPage();
		Driver.getDriver().manage().window().maximize();
		signInPage.loginFast(userName, password);
		waitForElement(By.id("navbar-header"), 10);
		URL = prop.getProperty("URL")+"P/Institution/MenuManagement/";
		launchUrl(URL, "Test Automation Enterprise Video Platform");
	}

	public void navigateToMetadata() {
		try {
			List<WebElement> navbarlist = Driver.getDriver().findElements(By.className("navbar"));
			WebElement navBar = navbarlist.get(navbarlist.size() - 1);
			Thread.sleep(3000);
			clickElement(navBar, "Click MetaData Tab", By.id("MetadataTab"), 3);
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void fillMetadataInfo(String metadataName, String metadataType, Boolean requirement) {
		try {
			HashMap<String, Integer> metadataTypeValue = new HashMap<String, Integer>();
			metadataTypeValue.put("String", 1);
			metadataTypeValue.put("List", 2);
			metadataTypeValue.put("Date", 3);
			metadataTypeValue.put("Time", 4);
			WebElement metadataContainer = Driver.getDriver().findElement(By.id("yuja_diMeta_container"));
			clickElement(metadataContainer, "Add Row button",By.xpath("//*[@id=\"yuja_diMeta_container\"]/div/div/div/div[3]/button"), 3);
			List<WebElement> tableRows = Driver.getDriver().findElements(By.cssSelector("table>tbody>tr"));
			int lastRowIndex = tableRows.size() - 1;
			sendKeysModal(metadataContainer, "Adding MetaData Name", By.id("colName" + lastRowIndex), metadataName);
			WebElement mdataType = Driver.getDriver().findElement(By.cssSelector("#colType" + lastRowIndex + "> option:nth-child(" + metadataTypeValue.get(metadataType) + ")"));
			clickElement("Select MetaData type", mdataType);
			if (requirement == true) {
				WebElement mdataRequire = Driver.getDriver().findElement(By.cssSelector("#colRequired" + lastRowIndex));
				clickElement("Select MetaData type", mdataRequire);
			}
			if (metadataType == "List") {
				addDefaultListValue(lastRowIndex + 1);
			}
			clickElement("Save button",By.cssSelector("#yuja_diMeta_container > div > div > div > div:nth-child(1) > div > button"));
			URL = "https://staging-demo.yuja.com/P/Institution/MenuManagement/";
			launchUrl(URL, "Xavier University Enterprise Video Platform");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void addDefaultListValue(int lastRowIndex) {
		WebElement metadataEntry= Driver.getDriver().findElement(By.cssSelector("table>tbody>tr:nth-child("+ lastRowIndex +")"));
		clickElement(metadataEntry,"Click More Options for Metadata", By.cssSelector("table>tbody>tr:nth-child("+ lastRowIndex +")>td:nth-child(4)"),3);
		clickElement(metadataEntry,"Click Edit option", By.cssSelector("table>tbody>tr:nth-child("+ lastRowIndex  +")>td:nth-child(4)>div>div>div>ul>li:nth-child(1)>a"),3);
		List <WebElement> modalList = Driver.getDriver().findElements(By.className("modal-content"));
		WebElement editModal = modalList.get(2);
		sendKeysModal(editModal,"Adding List Item" , By.id("colValue0"), "A");
		clickElement(editModal, "Save button", By.cssSelector("#yuja_diMeta_container > div > div > div > div.static-modal > div > div > div > div.modal-footer > button.btn.defaultcolor"),3);
	}

	public void verifyMetadataAddedToAdminPanel(Boolean requirement) {
		try {
			List<WebElement> tableRows = Driver.getDriver().findElements(By.cssSelector("table>tbody>tr"));
			int lastRow = tableRows.size();
			verifyElementExist("Added Metadata", By.cssSelector("table>tbody>tr:nth-child(" + lastRow + ")"));
			if (requirement == true) {
				WebElement requiredStatus = Driver.getDriver().findElement(
						By.cssSelector("table>tbody>tr:nth-child(" + lastRow + ")>td:nth-child(3)>div>div>p"));
				System.out.println(requiredStatus.getText());
				if ((requiredStatus.getText()).equals("Yes")) {
					Report.reportStep(Driver.getDriver(), "Requirement Set to Yes", "PASS", false);
				} else {Report.reportStep(Driver.getDriver(), "Requirement Set to No", "FAIL", true);}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void deleteMetadata(String metadataName, String metadataType) throws InterruptedException {
		List<WebElement> tableRows = Driver.getDriver().findElements(By.cssSelector("table>tbody>tr"));
		for (int i = 1; i < tableRows.size() + 1; i++) {
			WebElement metadataEntry = Driver.getDriver().findElement(By.cssSelector("table>tbody>tr:nth-child(" + i + ")"));
			if (metadataEntry.getText().contains(metadataName) == true) {
				clickElement(metadataEntry, "Click More Options for Metadata",By.cssSelector("table>tbody>tr:nth-child(" + i + ")>td:nth-child(4)"), 3);
				if (metadataType == "List") {
					clickElement(metadataEntry, "Click Delete option", By.cssSelector("table>tbody>tr:nth-child(" + i + ")>td:nth-child(4)>div>div>div>ul>li:nth-child(2)>a"), 3);
				} else {
					clickElement(metadataEntry, "Click Delete option",By.cssSelector("table>tbody>tr:nth-child(" + i + ")>td:nth-child(4)>div>div>div>ul>li>a"),3);
				}
				List<WebElement> modalList = Driver.getDriver().findElements(By.className("modal-content"));
				WebElement deleteModal = modalList.get(2);
				clickElement(deleteModal, "Yes Button", By.xpath("//*[@id=\"yuja_diMeta_container\"]/div/div/div/div[4]/div/div/div/div/div[3]/button[2]"), 3);
				clickElement("Save button",By.cssSelector("#yuja_diMeta_container > div > div > div > div:nth-child(1) > div > button"));
				Thread.sleep(1000);
			}
		}
	}
	
	public void CheckPageUI() {
		URL = prop.getProperty("URL")+"P/Institution/MenuManagement/";
		launchUrl(URL, "Test Automation Enterprise Video Platform");
		String sectionTitle = Driver.getDriver().findElement(By.id("secondPartText")).getText();
		boolean check  = waitForElement(By.id("di_masterContainer"),10).isDisplayed();
		if(check && sectionTitle.equals("Platform")) {
			Report.reportStep(Driver.getDriver(), sectionTitle + " Page loaded successfully", "PASS", false);
			System.out.println(sectionTitle);
		}
		else Report.reportStep(Driver.getDriver(), sectionTitle + "failed to load", "fail", true);	
	}
}

