package com.yuja.evp.pagetestmethods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import com.yuja.evp.pagehelpers.AdminPanelAccessibilityPageHelpers;
import com.yuja.evp.pagehelpers.AdminPanelBrandingPageHelpers;
import com.yuja.evp.pagehelpers.AdminPanelDataManagementPageHelpers;
import com.yuja.evp.pagehelpers.AdminPanelDevicesPageHelpers;
import com.yuja.evp.pagehelpers.AdminPanelGeneralPageHelpers;
import com.yuja.evp.pagehelpers.AdminPanelIntegrationPageHelpers;
import com.yuja.evp.pagehelpers.AdminPanelNotificationPageHelpers;
import com.yuja.evp.pagehelpers.AdminPanelOrganizationPageHelpers;
import com.yuja.evp.pagehelpers.AdminPanelOverviewPageHelpers;
import com.yuja.evp.pagehelpers.AdminPanelPlatformPageHelpers;
import com.yuja.evp.pagehelpers.AdminPanelRosterPageHelpers;
import com.yuja.evp.pagehelpers.AdminPanelRtmpStreamPageHelpers;
import com.yuja.evp.pagehelpers.AdminPanelStorageQuotaPageHelpers;

import helperinterfaces.UICheck;

public class AdminPanelGeneralTestMethods extends AdminPanelGeneralPageHelpers{
	
	AdminPanelRosterPageHelpers rosterPage = new AdminPanelRosterPageHelpers();
	AdminPanelOverviewPageHelpers overview = new AdminPanelOverviewPageHelpers();
	AdminPanelNotificationPageHelpers notification = new AdminPanelNotificationPageHelpers();
	AdminPanelDevicesPageHelpers devices = new AdminPanelDevicesPageHelpers();
	AdminPanelIntegrationPageHelpers integration = new AdminPanelIntegrationPageHelpers();
	AdminPanelPlatformPageHelpers platform = new AdminPanelPlatformPageHelpers();
	AdminPanelBrandingPageHelpers branding = new AdminPanelBrandingPageHelpers();
	AdminPanelAccessibilityPageHelpers accessibility = new AdminPanelAccessibilityPageHelpers();
	AdminPanelStorageQuotaPageHelpers storagequota = new AdminPanelStorageQuotaPageHelpers();
	AdminPanelDataManagementPageHelpers datamanagement = new AdminPanelDataManagementPageHelpers();
	AdminPanelRtmpStreamPageHelpers rtmp = new AdminPanelRtmpStreamPageHelpers();
	AdminPanelOrganizationPageHelpers organization = new AdminPanelOrganizationPageHelpers();
	
	public void checkAdminPanelUI(String userName, String Password) throws InterruptedException {
		navigatetoAdminPanelUserLogin(userName,Password);
		List<WebElement> list = driver.findElements(By.xpath("//a[@class='alink border-color']"));
		WebElement eachElement;
		for(int i=0;i<list.size();i++) {
			eachElement = list.get(i);
			title = list.get(i).getText();
			clickElement(title,eachElement);	
			Thread.sleep(2000);
		}
	}
	
	public void AdminPanelCheckPageUI(String userName, String Password) throws InterruptedException {
		try {
			UICheck temp;
			navigatetoAdminPanelUserLogin(userName,Password);
			Map<Integer, UICheck> adminpanelPages = new HashMap<Integer, UICheck>();
			adminpanelPages.put(0, overview);
			adminpanelPages.put(1, notification);
			adminpanelPages.put(2, devices);
			adminpanelPages.put(3, rosterPage);
			adminpanelPages.put(4, integration);
			adminpanelPages.put(5, platform);
			adminpanelPages.put(6, branding);
			adminpanelPages.put(7, accessibility);
			adminpanelPages.put(8, storagequota);
			adminpanelPages.put(9, datamanagement);
			adminpanelPages.put(10, rtmp);
			adminpanelPages.put(11, organization);
			for (int i = 0; i < adminpanelPages.size(); i++) {			
				try {
					temp = adminpanelPages.get(i);
					temp.CheckPageUI();
				} catch(StaleElementReferenceException ex) {
					System.out.println(ex.toString());
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}	
}
	
	
