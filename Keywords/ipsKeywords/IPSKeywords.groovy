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
import com.kms.katalon.core.configuration.RunConfiguration

import internal.GlobalVariable
import utils.IPSUtils
import com.kms.katalon.core.util.KeywordUtil
import windows.WindowsImprovedKeywords
public class IPSKeywords {
	/**
	 * Login using credentials from data file
	 * @param int platform - which platform (web/desktop) to use
	 * Ex:IPSKeywords.openApp("Desktop")
	 */
	@Keyword
	def static openApp(String platform) {
		String appPath
		try {
			switch (platform) {
				case IPSUtils.WEB:
					WebUI.openBrowser(GlobalVariable.WEB_URL)
					break
				case IPSUtils.DESKTOP:
					IPSUtils.ensureWinAppDriverIsRunning();
					WindowsImprovedKeywords.startApplicationWithExactTitle(GlobalVariable.APP_FILE_PATH, GlobalVariable.APP_NAME)
					break
				case IPSUtils.ANDROID:
					if(GlobalVariable.CLOUD_RUN) {
						appPath = RunConfiguration.getProjectDir() + File.separator + 'Resources' + File.separator + GlobalVariable.APK
						KeywordUtil.logInfo("Opening " + appPath)
						Mobile.startApplication(appPath, true)
					}else {
						Mobile.startExistingApplication(GlobalVariable.PACKAGE_ID)
					}
					IPSUtils.setDeviceDimension()
					break
				case IPSUtils.IOS:
					if(GlobalVariable.CLOUD_RUN) {
						appPath = RunConfiguration.getProjectDir() + File.separator + 'Resources' + File.separator + GlobalVariable.IPA
						KeywordUtil.logInfo("Opening " + appPath)
						Mobile.startApplication(appPath, true)
					}else {
						Mobile.startExistingApplication(GlobalVariable.BUNDLE_ID)
					}
					IPSUtils.setDeviceDimension()
					break
			}
			Windows.delay(1);
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on IPSKeywords.openApp() with exception: "+e);
		}
	}
}
