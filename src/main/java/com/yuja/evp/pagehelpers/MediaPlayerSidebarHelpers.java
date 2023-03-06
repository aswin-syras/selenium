package com.yuja.evp.pagehelpers;
import java.io.File;
import java.nio.file.Paths;

import org.openqa.selenium.chrome.ChromeOptions;

import com.yuja.evp.modalhelpers.AddMediaModalHelperMethods;
import com.yuja.evp.pagetestmethods.MediaLibraryPageTestMethods;
import com.yuja.evp.utilities.Helpers;


public class MediaPlayerSidebarHelpers extends Helpers {
	NavigationBarHelpers navigationBar = new NavigationBarHelpers();
	AddMediaModalHelperMethods modal = new AddMediaModalHelperMethods();
	MediaLibraryPageTestMethods medialibrary = new MediaLibraryPageTestMethods();	
	ChromeOptions options = new ChromeOptions();
}

