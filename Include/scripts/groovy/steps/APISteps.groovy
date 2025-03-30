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
import methods.APIMethods
import methods.CombinedMethods
import ipsKeywords.IPSKeywords

public class APISteps {

	@Before
	def void before(Scenario scenario) {
		IPSUtils.setupSheetName(scenario);
	}

	@Given("user send web service request {string} in {string}")
	def user_send_web_service_request(String objName, String pageName){
		APIMethods.sendWSRequest(objName, pageName);
	}

	@When("user set access token jsonpath {string}")
	def user_sets_access_token(String jsonPath){
		APIMethods.setAccessToken(jsonPath);
	}

	@When("user send web service request {string} with value {string} in {string}")
	def user_send_web_service_request_with_value(String objName, String strValue, String pageName){
		APIMethods.sendWSRequestWithValue(objName, strValue, pageName);
	}

	//needs to revisit this after test data management was finalized
	@When("user verify request expected status code {int}")
	def user_verify_request_status_code(int statusCode){
		APIMethods.verifyStatusCode(statusCode);
	}

	//needs to revisit this after test data management was finalized
	@When("user verify request jsonPath {string} value {string}")
	def user_verify_request_node_value(String jsonPath, String expectedValue){
		APIMethods.verifyNodeValue(jsonPath, expectedValue);
	}
}
