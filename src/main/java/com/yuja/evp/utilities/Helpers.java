package com.yuja.evp.utilities;

import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.yuja.evp.reports.Report;

import net.jodah.failsafe.internal.util.Assert;

public class Helpers extends Report {

/////navigation helper

	public void launchUrl(String url, String verifyTitle) {
		try {
			driver.get(url);

			if (!verifyTitle.equalsIgnoreCase("")) {
				if (!driver.getTitle().equalsIgnoreCase(verifyTitle)) {
					System.out.println("Browser Launch failed");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("Application Launch Failed", "Fail", true);
		}

	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//visual helpers

	public void flash(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 3; i++) {
			changeColor("rgb(0,200,0)", element, js);
			changeColor(bgcolor, element, js);
		}
	}

	public void changeColor(String color, WebElement element, JavascriptExecutor js) {
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/// UI interaction helpers

	public WebElement waitForElement(By by, int timeOutInSeconds) {
		WebElement element;
		try {

			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); // nullify the default timeout

			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);

			element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));

			driver.manage().timeouts().implicitlyWait(Default_Wait_For_Page, TimeUnit.SECONDS);
			return element;// return the element
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean verifyElementExist(String fieldName, By identifier, int timeout) {
		boolean bReturn = false;

		try {
			WebElement element = waitForElement(identifier, timeout);

			if (element.isDisplayed() == true && element.isEnabled() == true) {
				reportStep(fieldName + " element displayed as expected", "PASS", false);
				bReturn = true;
			} else {
				reportStep(fieldName + " element is not displayed", "FAIL", true);
			}

		} catch (Exception e) {
			System.out.println(fieldName + " Element not exist method - thrown Exception");
			//reportStep(fieldName + " element not exist method - thrown Exception", "FAIL", true);
		}

		return bReturn;
	}

	public boolean verifyElementExist(String fieldName, By identifier) {
		return verifyElementExist(fieldName, identifier, 10);
	}

//////////////////////////////////////////////////////////////////////
//unsure what this method does
	public boolean verifyElementExistReturn(By identifier) {
		boolean bReturn = false;
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // nullify the default timeout
			WebElement element = driver.findElement(identifier);

			if (element.isDisplayed() == true && element.isEnabled() == true) {
				bReturn = true;
			}
		} catch (Exception e) {
			System.out.println("Element not exist method - thrown Exception");
			return bReturn;
		}
		driver.manage().timeouts().implicitlyWait(Default_Wait_For_Page, TimeUnit.SECONDS);
		return bReturn;
	}
///////////////////////////////////////////////////////////////////////////////

	public void sendKeys(String fieldName, By identifier, String strValue) {
		System.out.println(identifier);
		WebElement element = waitForElement(identifier, 10);

		try {
			element.click();
			flash(element);
			element.clear();
			element.sendKeys(strValue);
			reportStep(strValue + " is entered in the field " + fieldName, "PASS", false);
		} catch (Exception e) {
			reportStep(strValue + " is not entered in the field " + fieldName, "FAIL", false);
		}
	}

	public void sendKeysModal(WebElement modal, String fieldName, By indentifier, String strValue) {
		try {
			System.out.println(indentifier);
			WebElement element = modal.findElement(indentifier);
			element.sendKeys(strValue);
			reportStep(strValue + " is entered in the field " + fieldName, "PASS", false);
		} catch (Exception e) {
			reportStep(strValue + " is not entered in the field " + fieldName, "FAIL", false);
		}

	}

	public void typeKeys(String keys) {
		try {
			Actions action = new Actions(driver);
			for (int i = 0; i < keys.length(); i++) {
				String c = keys.charAt(i) + "";
				action.sendKeys(c).build().perform();

			}
			reportStep(keys + " is typed", "PASS", false);
		} catch (Exception e) {
			reportStep(keys + " is not typed", "FAIL", false);
		}
	}

	public void keyboardEnter() {
		try {
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ENTER).build().perform();

			reportStep(" enter is clicked", "PASS", false);
		} catch (Exception e) {
			reportStep(" enter is not clicked", "FAIL", false);
		}
	}

	public void clickElement(String fieldName, WebElement element) {
		try {
			flash(element);
			element.click();
			reportStep(fieldName + " is clicked successfully", "PASS", false);
		} catch (Exception e) {
			reportStep(fieldName + " is not clicked successfully", "FAIL", true);
			e.printStackTrace();
		}

	}

	public void clickElement(WebElement el, String fieldName, By identifier, int timeout) {
		WebElement element = el.findElement(identifier);
		try {
			flash(element);
			element.click();
			reportStep(fieldName + " is clicked successfully", "PASS", false);
		} catch (Exception e) {
			reportStep(fieldName + " is not clicked successfully", "FAIL", true);
			e.printStackTrace();
		}

	}

	public void clickElement(String fieldName, By identifier, int timeout) {
		System.out.println(identifier);
		WebElement element = waitForElement(identifier, timeout);
		flash(element);
		try {
			element.click();
			Thread.sleep(500);
			reportStep(fieldName + " is clicked successfully", "PASS", false);
		} catch (Exception e) {
			reportStep(fieldName + " is not clicked successfully", "FAIL", true);
			e.printStackTrace();
		}
	}

	public void clickElement(String fieldName, By identifier) {
		clickElement(fieldName, identifier, 10);
	}

	public List<WebElement> getElementList(By identifier) {

		waitForElement(identifier, 10);
		List<WebElement> videoElementList;

		try {
			videoElementList = driver.findElements(identifier);
			return videoElementList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void doubleClick(String fieldName, WebElement element) {
		try {
			flash(element);
			Actions action = new Actions(driver);
			action.doubleClick(element).perform();
			reportStep(fieldName + " is doubleclicked successfully", "PASS", false);
		} catch (Exception e) {
			reportStep(fieldName + " is not doubleclicked successfully", "FAIL", true);
			e.printStackTrace();
		}

	}

	public void switchToIframe(String fieldName, By identifier, int timeout) {
		WebElement frame = waitForElement(identifier, timeout);
		try {
			if (frame != null) {
				driver.switchTo().frame(frame);
			}
			reportStep("Succesfully switched to iframe " + fieldName, "PASS", false);
		} catch (Exception e) {
			reportStep("Unable to switch to iframe " + fieldName, "FAIL", true);
			e.printStackTrace();
		}
	}

	public WebElement getMedia(String mediaTitle) {
		reportStep("Fetching the media with the name \"" + mediaTitle + "\"", "PASS", false);
		List<WebElement> mediaLibraryElementList = getElementList(By.className("videoWrapper"));
		int listSize = mediaLibraryElementList.size();
		if (listSize > 0) {
			WebElement mediaLibraryElement;
			String mediaLibraryElementName;
			int i = 0;
			while (i < listSize) {
				mediaLibraryElement = mediaLibraryElementList.get(i++);
				mediaLibraryElementName = mediaLibraryElement.findElement(By.className("titleText")).getText();
				if (mediaLibraryElementName.equals(mediaTitle)) {
					return mediaLibraryElement;
				}
			}
		}
		return null;
	}

	public boolean mediaExists(String mediaTitle) {
		WebElement mediaLibraryElement = getMedia(mediaTitle);
		if (mediaLibraryElement != null) {
			return true;
		} else {
			return false;
		}
	}

	public WebElement getFolder(String folderName) {
		reportStep("Fetching the folder with the name \"" + folderName + "\"", "PASS", false);
		List<WebElement> mediaLibraryElementList = getElementList(
				By.cssSelector("[data-automation=\"mediaLibraryFolder\"]"));
		int listSize = mediaLibraryElementList.size();
		if (listSize > 0) {
			WebElement mediaLibraryElement;
			String mediaLibraryElementName;
			;
			int i = 0;
			while (i < listSize) {
				mediaLibraryElement = mediaLibraryElementList.get(i++);
				mediaLibraryElementName = mediaLibraryElement
						.findElement(By.cssSelector("[data-automation=\"mediaLibraryElementTitle\"]")).getText();
				if (mediaLibraryElementName.equals(folderName)) {
					return mediaLibraryElement;
				}
			}
		}
		return null;
	}

	public Boolean folderExists(String folderTitle) {
		WebElement mediaLibraryElement = getFolder(folderTitle);
		if (mediaLibraryElement != null) {
			return true;
		} else {
			return false;
		}
	}

	public void hoverOverElement(WebElement element) {
		System.out.println("hovering over element...");
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	protected boolean captionFileExistsInSystem() throws InterruptedException {
		String downloadDirectoryPath = Paths.get("src//fileResources//downloads").toAbsolutePath().toString();
		File file = new File(downloadDirectoryPath + "\\274787.pdf");
		if (file.exists()) {
			file.delete();
			return true;
		} else {
			return false;
		}
	}

	public int getRandomInteger(int min, int max) {
		int randomInteger = (int) Math.floor(Math.random() * (max - min + 1) + min);
		return randomInteger;
	}

	public int getRandomInteger(int max) {
		return getRandomInteger(0, max);
	}
}
