import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.annotation.TearDown
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.cucumber.keyword.internal.CucumberGlueGenerator
import com.kms.katalon.core.configuration.RunConfiguration

import com.kms.katalon.core.util.KeywordUtil
import groovy.time.*
import utils.IPSUtils

class Listener {
	int passed, failed, error, skipped, total
	def startDate = new Date()
	/**
	 * Executes after every test case ends.
	 * @param testCaseContext related information of the executed test case.
	 */
	@AfterTestCase
	def sampleAfterTestCase(TestCaseContext testCaseContext) {
		WebUI.closeBrowser();
		switch (testCaseContext.testCaseStatus){
			case "PASSED":
				passed = passed + 1
				break;
			case "FAILED":
				failed = failed + 1
				break;
			case "ERROR":
				error = error + 1
				break;
			case "skipped":
				skipped = skipped + 1
				break;
		}
		
	}

	/**
	 * Add the GLUE option for Cucumber to locate the step definition files.
	 * @param testCaseContext related information of the executed test case.
	 */
	@BeforeTestCase
	def beforeTestCase(TestCaseContext testCaseContext) {	
		String platform = testCaseContext.getTestCaseId();
		CucumberGlueGenerator.addDefaultPackages();	
		IPSUtils.setKeyEventMap();
		GlobalVariable.DEFAULT_PLATFORM = platform.substring(11, 14);		
	}
	
	@AfterTestSuite
	def webhookAfterTestSuite(TestCaseContext testCaseContext, TestSuiteContext testSuiteContext) {
		Date endDate = new Date()
		TimeDuration duration = TimeCategory.minus(endDate, startDate)
		println("Duration: " + duration)
		total = passed + failed + error + skipped
		WS.sendRequest(findTestObject('API/Webhook/MSTeamsReporting', [('testSuite'): testSuiteContext.getTestSuiteId(), 
			('timeStart'): startDate,
			('timeEnd'): endDate,
			('totalDuration'): duration,
			('countPassed'): passed, 
			('countFailed'): failed,
			('countError'): error,
			('countSkipped'): skipped,
			('countTotal'): total
			]))
	}
}