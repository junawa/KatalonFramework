package ipsKeywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.util.KeywordUtil
import methods.CombinedMethods
import utils.IPSUtils

import internal.GlobalVariable

public class IPSNavigation {

	@Keyword
	def static navigateToPlatformPage(String pageName) {
		try {
			switch (GlobalVariable.DEFAULT_PLATFORM) {
				case IPSUtils.WEB:
					navigateToWebPage(pageName);
					break
				case IPSUtils.DESKTOP:
					navigateToDesktopScreen(pageName);
					break
				case IPSUtils.ANDROID:
				case IPSUtils.IOS:
					navigateToMobileScreen(pageName);
					break
				default:
					throw new IllegalArgumentException("Unsupported platform: ${GlobalVariable.DEFAULT_PLATFORM}")
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on IPSNavigation.navigateToPlatformPage() with exception: "+e);
		}
	}

	// ========================= WEB PAGE ========================= //
	def static navigateToWebPage(String pageName) {
		try {
			switch (pageName) {
				case "Home":
					navWebHomePage();
					break;
				case "History":
					navWebHistoryPage();
					break;
				case "Profile":
					navWebProfilePage();
					break;
				default:
					throw new IllegalArgumentException("Unsupported web page: ${pageName}")
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on IPSNavigation.navigateToWebPage() with exception: "+e);
		}
	}

	def static navWebHomePage(String pageName) {
		WebUI.click(findTestObject('Web/CuraHomePage/menuHambuger'));
		CombinedMethods.clickElementWithText("a", "Home");
	}

	def static navWebHistoryPage(String pageName) {
		WebUI.click(findTestObject('Web/CuraHomePage/menuHambuger'));
		CombinedMethods.clickElementWithText("a", "History");
	}

	def static navWebProfilePage(String pageName) {
		WebUI.click(findTestObject('Web/CuraHomePage/menuHambuger'));
		CombinedMethods.clickElementWithText("a", "Profile");
	}
	// ====================== END OF WEB PAGE ====================== //

	// ======================= DESKTOP SCREEN ========================= //
	def static navigateToDesktopScreen(String pageName) {
		try {
			switch (pageName) {
				case "Profile":
					navDesktopProfileScreen();
					break;
				default:
					throw new IllegalArgumentException("Unsupported desktop screen: ${pageName}")
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on IPSNavigation.navigateToDesktopScreen() with exception: "+e);
		}
	}

	def static navDesktopProfileScreen(String pageName) {
		// insert desktop navigation commands
	}
	// ==================== END OF DESKTOP SCREEN ====================== //

	// ======================= MOBILE SCREEN ========================= //
	def static navigateToMobileScreen(String pageName) {
		try {
			switch (pageName) {
				case "Settings":
					navMobileSettingScreen();
					break;
				default:
					throw new IllegalArgumentException("Unsupported mobile screen: ${pageName}")
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on IPSNavigation.navigateToMobileScreen() with exception: "+e);
		}
	}

	def static navMobileSettingScreen(String pageName) {
		// insert mobile navigation commands
	}
	// =================== END OF MOBILE SCREEN ===================== //
}
