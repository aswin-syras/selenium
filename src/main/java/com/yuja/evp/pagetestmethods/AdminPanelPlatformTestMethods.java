package com.yuja.evp.pagetestmethods;

import com.yuja.evp.pagehelpers.AdminPanelPlatformPageHelpers;

public class AdminPanelPlatformTestMethods extends AdminPanelPlatformPageHelpers {
	
	public void addandDeleteMetadata(String metadataType, Boolean requirement) throws InterruptedException {
		String metadataName = "Test" + metadataType;
		navigateToAdminPanelPlatformPageUserLogin("ankit_manager","Asdfghjkl12345!@#$%");
		navigateToMetadata();
		fillMetadataInfo(metadataName, metadataType,requirement);
		navigateToMetadata();
		verifyMetadataAddedToAdminPanel(requirement);
		deleteMetadata(metadataName,metadataType);
	}
	
	}
