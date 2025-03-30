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
import methods.CombinedMethods
import methods.MobileMethods

public class MobileSteps {
	@Before
	def void before(Scenario scenario) {
		IPSUtils.setupSheetName(scenario);
	}

	@When("user swipe screen {string}")
	def user_swipe_screen(String direction){
		MobileMethods.swipeScreen(direction)
	}

	@When("user swipe screen {string} until {string} is visible in {string}")
	def user_swipe_screen_up_until_object_is_visible(String direction, String objName, String pageName){
		MobileMethods.swipeUntilObjectVisible(direction, objName, pageName)
	}

	@When("user get {string} coordinates in {string}")
	def user_get_object_coordinates(String objName, String pageName){
		MobileMethods.getSectionCoordinates(objName, pageName)
	}

	@When("user swipe {string} section {string} until {string} is visible in {string}")
	def user_swipe_section_up_until_object_is_visible(String direction, String sectionObjName, String toFindObjName, String pageName){
		MobileMethods.swipeSectionUntilObjectVisible(direction, sectionObjName, toFindObjName, pageName)
	}

	@When("user swipe screen up until {string} is at center in {string}")
	def user_swipe_screen_up_until_object_is_at_center(String objName, String pageName){
		MobileMethods.swipeUpUntilObjectAtCenter(objName, pageName)
	}
}
