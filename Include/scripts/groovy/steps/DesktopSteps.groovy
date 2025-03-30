package steps

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

import internal.GlobalVariable
import cucumber.api.Scenario
import utils.IPSUtils
import cucumber.api.java.Before
import cucumber.api.java.en.Given
import cucumber.api.java.en.When
import cucumber.api.java.en.Then
import methods.DesktopMethods
import windows.WindowsImprovedKeywords as WinKeyword
public class DesktopSteps {
	@Before
	def void before(Scenario scenario) {
		IPSUtils.setupSheetName(scenario);
	}
	
	@When("user clicks element offset {string}")
	def user_clicks_element_offset(String txtVal){
		DesktopMethods.clickOffsetValues(txtVal);
	}
	
	@When("user switches to application with {string} - {string}")
	def user_switches_to_application_with_title(String windowAttr, String txtVal){		
		WinKeyword.switchToWindowAttrPattern(windowAttr, txtVal)				
	}
		
	@When("user switches to Avaloq Application")
	def user_switches_to_avaloq_application(){
		WinKeyword.switchToWindowAttrPattern("ClassName", IPSUtils.AVALOQ_CLASSNAME);
	}
}
