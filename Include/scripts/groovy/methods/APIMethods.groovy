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

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty


public class APIMethods {
	static ResponseObject response

	def static setAccessToken(String jsonPath) {
		if(WS.getElementPropertyValue(response, jsonPath, FailureHandling.CONTINUE_ON_FAILURE) == null) {
			KeywordUtil.markFailed(jsonPath + " is not present in response body")
		}else {
			GlobalVariable.ACCESS_TOKEN = WS.getElementText(response, jsonPath)
			KeywordUtil.logInfo(GlobalVariable.ACCESS_TOKEN)
		}
	}

	def static sendWSRequest(String objName, String pageName) {
		String myTestObject = IPSUtils.getObjectRepoPath(objName, pageName)
		TestObject request = findTestObject(myTestObject)

		if (request instanceof RequestObject) {
			RequestObject requestObject = (RequestObject) request
			response = WS.sendRequest(requestObject)
		} else {
			KeywordUtil.markFailed(request.getObjectId() + " is not a RequestObject")
		}
	}

	def static sendWSRequestWithValue(String objName, String strValue, String pageName) {
		String myTestObject = IPSUtils.getObjectRepoPath(objName, pageName)
		TestObject request = findTestObject(myTestObject, [('value'): strValue])

		if (request instanceof RequestObject) {
			RequestObject requestObject = (RequestObject) request
			response = WS.sendRequest(requestObject)
		} else {
			KeywordUtil.markFailed(request.getObjectId() + " is not a RequestObject")
		}
	}

	def static verifyStatusCode(int expectedStatusCode) {
		if (response.getStatusCode() == expectedStatusCode) {
			KeywordUtil.markPassed("Expected response: " + expectedStatusCode + " is equal to reponse: " + response.getStatusCode())
		} else {
			KeywordUtil.markFailed("Expected response: " + expectedStatusCode + " is NOT equal to reponse: " + response.getStatusCode())
		}
	}

	def static verifyNodeValue(String jsonPath, String expectedValue) {
		if(WS.getElementPropertyValue(response, jsonPath, FailureHandling.CONTINUE_ON_FAILURE) == null) {
			KeywordUtil.markFailed(jsonPath + " is not present in response body")
		}else {
			if(WS.verifyElementPropertyValue(response, jsonPath, expectedValue, FailureHandling.CONTINUE_ON_FAILURE)) {
				KeywordUtil.markPassed("Expected value: " + expectedValue + " is equal to response")
			}else {
				KeywordUtil.markFailed("Expected value: " + expectedValue + " is NOT equal to response")
			}
		}
	}
}
