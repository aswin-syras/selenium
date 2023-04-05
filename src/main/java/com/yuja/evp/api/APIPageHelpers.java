package com.yuja.evp.api;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.yuja.evp.utilities.Helpers;

public class APIPageHelpers extends Helpers {
	
	public void generateToken(int tokenNumber) {
		WebElement generateButton = waitForElement(By.cssSelector("[onclick=\"institutionCommon.generateAPIToken('APIToken"+tokenNumber+"')\"][title=\"Generate API Token\"]"), 10);
		clickElement("Generate button Token #"+tokenNumber, generateButton);
	}
	
	public String getAuthToken(int tokenNumber) throws UnsupportedFlavorException, IOException, InterruptedException {
		WebElement showTokenIcon = waitForElement(By.cssSelector("[id=\"APIToken"+tokenNumber+"_toggle\"]"), 10);
		clickElement("Show icon Token #"+tokenNumber, showTokenIcon);
		Thread.sleep(500);
		
		WebElement copyButton = waitForElement(By.cssSelector("[id=\"copyToken"+tokenNumber+"\"][title=\"Copy Token\"]"), 10);
		clickElement("Copy button Token #"+tokenNumber, copyButton);
		Thread.sleep(500);
		
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);
		String authToken = (String) contents.getTransferData(DataFlavor.stringFlavor);
		
		return authToken;
	}
	
	public void revokeToken(int tokenNumber) {
		WebElement revokeButton = waitForElement(By.cssSelector("[onclick=\"institutionCommon.revokeToken('APIToken"+tokenNumber+"')\"][title=\"Revoke\"]"), 10);
		clickElement("Revoke button Token #"+tokenNumber, revokeButton);
	}
	

}
