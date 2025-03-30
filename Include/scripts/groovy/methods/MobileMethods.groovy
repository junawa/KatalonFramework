package methods

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
import com.kms.katalon.core.util.KeywordUtil
import utils.IPSUtils

public class MobileMethods {

	def static swipeScreen(String direction) {
		int swipeXstart = IPSUtils.getSwipeData(direction,"xStart");
		int swipeXend = IPSUtils.getSwipeData(direction,"xEnd");
		int swipeYstart = IPSUtils.getSwipeData(direction,"yStart");
		int swipeYend = IPSUtils.getSwipeData(direction,"yEnd");
		Mobile.swipe(swipeXstart, swipeYstart, swipeXend, swipeYend)

		KeywordUtil.logInfo("Swiping Screen: " + direction)
	}

	def static swipeUntilObjectVisible(String direction, String objName, String pageName) {
		String myTestObject = IPSUtils.getObjectRepoPath(objName, pageName)
		int counter = 0

		while (Mobile.verifyElementNotVisible(findTestObject(myTestObject), 2, FailureHandling.OPTIONAL) && counter < GlobalVariable.MAX_SWIPE){
			swipeScreen(direction)
			counter++
		}

		if (Mobile.verifyElementNotVisible(findTestObject(myTestObject), 2, FailureHandling.OPTIONAL)) {
			KeywordUtil.logInfo("Direction Swipe: " + direction + "Object: " + objName + " is NOT VISIBLE in screen")
		} else{
			KeywordUtil.markPassed("Direction Swipe: " + direction + "Object: " + objName + " is VISIBLE in screen")
		}
	}

	def static getSectionCoordinates(String objName, String pageName) {
		String myTestObject = IPSUtils.getObjectRepoPath(objName, pageName)
		GlobalVariable.OBJECT_HEIGHT = Mobile.getElementHeight(findTestObject(myTestObject), 0)
		GlobalVariable.OBJECT_WIDTH = Mobile.getElementWidth(findTestObject(myTestObject), 0)
		GlobalVariable.OBJECT_Y_POSITION = Mobile.getElementTopPosition(findTestObject(myTestObject), 0)
		GlobalVariable.OBJECT_X_POSITION = Mobile.getElementLeftPosition(findTestObject(myTestObject), 0)

		KeywordUtil.logInfo("objHeight: " + GlobalVariable.OBJECT_HEIGHT + ", objWidth: " + GlobalVariable.OBJECT_WIDTH + ", objYPosition: " + GlobalVariable.OBJECT_Y_POSITION + ", objXPosition: " + GlobalVariable.OBJECT_X_POSITION)
	}

	def static swipeSectionUntilObjectVisible(String direction, String sectionObjName, String toFindObjName, String pageName) {
		String myTestObject = IPSUtils.getObjectRepoPath(toFindObjName, pageName)
		getSectionCoordinates(sectionObjName, pageName)

		int counter = 0

		int swipeXstart =  GlobalVariable.OBJECT_X_POSITION + IPSUtils.getSwipeData(direction,"xStart");
		int swipeXend =   GlobalVariable.OBJECT_X_POSITION + IPSUtils.getSwipeData(direction,"xEnd");
		int swipeYstart = GlobalVariable.OBJECT_Y_POSITION + IPSUtils.getSwipeData(direction,"yStart");
		int swipeYend = GlobalVariable.OBJECT_Y_POSITION + IPSUtils.getSwipeData(direction,"yEnd");

		while (Mobile.verifyElementNotVisible(findTestObject(myTestObject), 2, FailureHandling.OPTIONAL) && counter < GlobalVariable.MAX_SWIPE) {
			Mobile.swipe(swipeXstart, swipeYstart, swipeXend, swipeYend)
			KeywordUtil.logInfo("Swiping Section: " + sectionObjName + " " + direction)
			counter++
		}

		if (Mobile.verifyElementNotVisible(toFindObjName, 2, FailureHandling.OPTIONAL)) {
			KeywordUtil.markFailed("Object: " + toFindObjName + " is NOT VISIBLE in screen")
		} else{
			KeywordUtil.markPassed("Object: " + toFindObjName + " is VISIBLE in screen")
		}
	}

	def static swipeUpUntilObjectAtCenter(String objName, String pageName) {
		getSectionCoordinates(objName, pageName)

		int swipeXstart = (GlobalVariable.DEVICE_WIDTH * 0.5)
		int swipeXend = (GlobalVariable.DEVICE_WIDTH * 0.5)
		int swipeYstart = (GlobalVariable.OBJECT_Y_POSITION - (GlobalVariable.DEVICE_HEIGHT * 0.15))
		int swipeYend = (GlobalVariable.DEVICE_HEIGHT * 0.35)
		int counter = 0

		// If object is close to center, swipe will not be initiated
		KeywordUtil.logInfo('Swiping to Center = Xstart: '+swipeXstart+' '+'Ystart: '+swipeYstart+' '+'Xend: '+swipeXend+' '+'Yend: '+swipeYend)
		int posDiff = Math.abs(Math.max(swipeYstart, swipeYend) - Math.min(swipeYstart, swipeYend))
		if(posDiff < 100) {
			KeywordUtil.logInfo("Object is already at/near center")
		} else {
			Mobile.swipe(swipeXstart, swipeYstart, swipeXend, swipeYend)
		}
	}
}
