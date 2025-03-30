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

public class IPSPageAssertion {

	@Keyword
	def static assertPlatformPage(String pageName) {
		try {
			switch (GlobalVariable.DEFAULT_PLATFORM) {
				case IPSUtils.WEB:
					assertWebPage(pageName);
					break
				case IPSUtils.DESKTOP:
					assertDesktopScreen(pageName);
					break
				case IPSUtils.ANDROID:
				case IPSUtils.IOS:
				//assertMobileScreen(pageName);
					break
				default:
					throw new IllegalArgumentException("Unsupported platform: ${GlobalVariable.DEFAULT_PLATFORM}")
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on IPSPageAssertion.assertPlatformPage() with exception: "+e);
		}
	}

	// ========================= WEB PAGE ========================= //
	def static assertWebPage(String pageName) {
		try {
			switch (pageName) {
				case "Appointment":
					assertWebAppointmentPage();
					break;
				case "History":
					assertWebHistoryPage();
					break;
				case "Profile":
					assertWebProfilePage();
					break;
				default:
					throw new IllegalArgumentException("Unsupported web page: ${pageName}")
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on IPSPageAssertion.assertWebPage() with exception: "+e);
		}
	}

	def static assertWebAppointmentPage() {
		WebUI.verifyElementPresent(findTestObject('Web/CuraAppointment/listFacility'), GlobalVariable.IMPLICIT_WAIT)
		WebUI.verifyElementPresent(findTestObject('Web/AppointmentConfirmation/btnBookAppointment'), GlobalVariable.IMPLICIT_WAIT)
		WebUI.verifyTextPresent("Make Appointment", false)
	}

	def static assertWebHistoryPage() {
		WebUI.verifyTextPresent("Go to Homepage", false)
	}

	def static assertWebProfilePage() {
		WebUI.verifyTextPresent("Under construction", false)
	}
	// ====================== END OF WEB PAGE ====================== //

	// ====================== DESKTOP ====================== //
	def static assertDesktopScreen(String pageScreen) {
		try {
			switch (pageScreen) {
				case "AvaloqHome":
					assertAvaloqHomeScreen();
					break;
				default:
					throw new IllegalArgumentException("Unsupported web page: ${pageScreen}")
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on IPSPageAssertion.assertDesktopScreen() with exception: "+e);
		}
	}

	def static assertAvaloqHomeScreen() {
		Windows.verifyElementPresent(findWindowsObject('DSK/HomeScreen/tabItemClient'), GlobalVariable.IMPLICIT_WAIT)
		Windows.verifyElementPresent(findWindowsObject('DSK/HomeScreen/tabItemCRM'), GlobalVariable.IMPLICIT_WAIT)
		Windows.verifyElementPresent(findWindowsObject('DSK/HomeScreen/tabItemMonetaryTrx'), GlobalVariable.IMPLICIT_WAIT)
		Windows.verifyElementPresent(findWindowsObject('DSK/HomeScreen/tabItemSecurityTransaction'), GlobalVariable.IMPLICIT_WAIT)
		Windows.verifyElementPresent(findWindowsObject('DSK/HomeScreen/tabItemStaticData'), GlobalVariable.IMPLICIT_WAIT)
	}
	// ====================== END OF DESKTOP ====================== //
}
