package methods

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static org.mockito.ArgumentMatchers.anyString

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
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil
import utils.IPSUtils
import com.kms.katalon.core.testobject.ObjectRepository
import org.testng.Assert
import org.openqa.selenium.Keys
import com.kms.katalon.core.testobject.WindowsTestObject
public class CombinedMethods {

	// ========================= CLICK ACTIONS ========================= //
	def static clickElement(String objName, String pageName) {
		try {
			String myTestObject = IPSUtils.getObjectRepoPath(objName, pageName)
			switch (GlobalVariable.DEFAULT_PLATFORM) {
				case IPSUtils.WEB:
					WebUI.waitForElementVisible(findTestObject(myTestObject), GlobalVariable.IMPLICIT_WAIT);
					WebUI.click(findTestObject(myTestObject))
					break
				case IPSUtils.DESKTOP:					
					Windows.waitForElementPresent(findWindowsObject(myTestObject), GlobalVariable.IMPLICIT_WAIT)
					Windows.click(findWindowsObject(myTestObject))
					break
				case IPSUtils.ANDROID:
				case IPSUtils.IOS:								
					Mobile.waitForElementPresent(findTestObject(myTestObject), GlobalVariable.IMPLICIT_WAIT);
					Mobile.tap(findTestObject(myTestObject), GlobalVariable.IMPLICIT_WAIT);
					break
				default:
					throw new IllegalArgumentException("Unsupported platform: ${GlobalVariable.DEFAULT_PLATFORM}")
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on CombinedMethods.clickElement() with exception: "+e);
		}
	}

	def static clickElementWithName(String txtVal) {
		try {
			String myTestObject = IPSUtils.getObjectRepoPath("objectName", IPSUtils.COMMON)
			switch (GlobalVariable.DEFAULT_PLATFORM) {
				case IPSUtils.WEB:
					WebUI.waitForElementVisible(findTestObject(myTestObject, [('value1'): txtVal]), GlobalVariable.IMPLICIT_WAIT);
					WebUI.click(findTestObject(myTestObject, [('value1'): txtVal]))
					break
				case IPSUtils.DESKTOP:
					Windows.waitForElementPresent(findWindowsObject(myTestObject, [('value1'): txtVal]), GlobalVariable.IMPLICIT_WAIT)
					Windows.click(findWindowsObject(myTestObject, [('value1'): txtVal]))
					break
				case IPSUtils.ANDROID:
				case IPSUtils.IOS:
					Mobile.waitForElementPresent(findTestObject(myTestObject, [('value1'): txtVal]), GlobalVariable.IMPLICIT_WAIT);
					Mobile.tap(findTestObject(myTestObject, [('value1'): txtVal]), GlobalVariable.IMPLICIT_WAIT)
					break
				default:
					throw new IllegalArgumentException("Unsupported platform: ${GlobalVariable.DEFAULT_PLATFORM}")
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on CombinedMethods.clickElementWithName() with exception: "+e);
		}
	}

	// uses Object Repository/Web/Common/elementWithAttribute
	def static clickElementWithAttribute(String elName, String attrName, String attrVal) {
		try {
			String myTestObject = IPSUtils.getObjectRepoPath(IPSUtils.ELEMENT_WITH_ATTR, IPSUtils.COMMON)
			switch (GlobalVariable.DEFAULT_PLATFORM) {
				case IPSUtils.WEB:
					WebUI.click(findTestObject(myTestObject, [('value1'): elName, ('value2'): attrName, ('value3'): attrVal]))
					break
				case IPSUtils.DESKTOP:
					Windows.click(findWindowsObject(myTestObject, [('value1'): elName, ('value2'): attrName, ('value3'): attrVal]))
					break
				case IPSUtils.ANDROID:
					Mobile.tap(findTestObject(myTestObject, [('value1'): elName, ('value2'): attrName, ('value3'): attrVal]), GlobalVariable.IMPLICIT_WAIT)
					break
				case IPSUtils.IOS:
					Mobile.tap(findTestObject(myTestObject, [('value1'): elName, ('value2'): attrName, ('value3'): attrVal]), GlobalVariable.IMPLICIT_WAIT)
					break
				default:
					throw new IllegalArgumentException("Unsupported platform: ${GlobalVariable.DEFAULT_PLATFORM}")
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on CombinedMethods.clickObjWithText() with exception: "+e);
		}
	}

	// uses Object Repository/Web/Common/elementWithText
	def static clickElementWithText(String elemName, String txtVal) {
		try {
			String myTestObject = IPSUtils.getObjectRepoPath(IPSUtils.ELEMENT_WITH_TEXT, IPSUtils.COMMON)
			switch (GlobalVariable.DEFAULT_PLATFORM) {
				case IPSUtils.WEB:
					WebUI.click(findTestObject(myTestObject, [('value1'): elemName, ('value2'):txtVal]))
					break
				case IPSUtils.DESKTOP:
					Windows.click(findWindowsObject(myTestObject, [('value1'): elemName, ('value2'):txtVal]))
					break
				case IPSUtils.ANDROID:
					Mobile.tap(findTestObject(myTestObject, [('value1'): elemName, ('value2'): txtVal]), GlobalVariable.IMPLICIT_WAIT)
					break
				case IPSUtils.IOS:
					Mobile.tap(findTestObject(myTestObject, [('value1'): elemName, ('value2'): txtVal]), GlobalVariable.IMPLICIT_WAIT)
					break
				default:
					throw new IllegalArgumentException("Unsupported platform: ${GlobalVariable.DEFAULT_PLATFORM}")
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on CombinedMethods.clickElementWithValue() with exception: "+e);
		}
	}

	// uses Object Repository/Web/Common/attrWithText
	def static clickDynamicElementWithValue(String attrName, String attrVal, String txtVal) {
		try {
			String myTestObject = IPSUtils.getObjectRepoPath(IPSUtils.ATTR_WITH_TEXT, IPSUtils.COMMON)
			switch (GlobalVariable.DEFAULT_PLATFORM) {
				case IPSUtils.WEB:
					WebUI.click(findTestObject(myTestObject, [('value1'): attrName, ('value2'): attrVal, ('value3'): txtVal]))
					break
				case IPSUtils.DESKTOP:
					Windows.click(findWindowsObject(myTestObject, [('value1'): attrName, ('value2'): attrVal, ('value3'): txtVal]))
					break
				case IPSUtils.ANDROID:
					Mobile.tap(findTestObject(myTestObject, [('value1'): attrName, ('value2'): attrVal, ('value3'): txtVal]), GlobalVariable.IMPLICIT_WAIT)
					break
				case IPSUtils.IOS:
					Mobile.tap(findTestObject(myTestObject, [('value1'): attrName, ('value2'): attrVal, ('value3'): txtVal]), GlobalVariable.IMPLICIT_WAIT)
					break
				default:
					throw new IllegalArgumentException("Unsupported platform: ${GlobalVariable.DEFAULT_PLATFORM}")
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on CombinedMethods.clickDynamicElementWithValue() with exception: "+e);
		}
	}
	// ====================== END OF CLICK ACTIONS ====================== //

	// ========================= SET TEXT ========================= //

	def static setTextToElement(String dataSource, String txtVal, String objName, String pageName) {
		try {
			String myTestObject = IPSUtils.getObjectRepoPath(objName, pageName);
			String dataSourceVal = IPSUtils.getSourceDataValue(dataSource, txtVal);
			switch (GlobalVariable.DEFAULT_PLATFORM) {
				case IPSUtils.WEB:
					WebUI.setText(findTestObject(myTestObject), dataSourceVal)
					break
				case IPSUtils.DESKTOP:
					Windows.setText(findWindowsObject(myTestObject), dataSourceVal)
					break
				case IPSUtils.ANDROID:
				case IPSUtils.IOS:
					Mobile.setText(findTestObject(myTestObject), dataSourceVal, GlobalVariable.IMPLICIT_WAIT)
					KeywordUtil.logInfo("Closing " + GlobalVariable.DEFAULT_PLATFORM + " Keyboard")
					try {
						Mobile.hideKeyboard()
					} catch (Exception e) {
						KeywordUtil.logInfo("Error: $e")
					}
					break
				default:
					throw new IllegalArgumentException("Unsupported platform: ${GlobalVariable.DEFAULT_PLATFORM}")
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on CombinedMethods.setTextToElement() with exception: "+e);
		}
	}

	def static setTextToCurrentElement(String dataSource, String txtVal) {
		try {
			String dataSourceVal = IPSUtils.getSourceDataValue(dataSource, txtVal);
			IPSUtils.typeText(dataSourceVal);
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on CombinedMethods.setTextToCurrentElement() with exception: "+e);
		}
	}

	// uses Object Repository/Web/Common/elementWithAttribute
	def static setTextToElementWithAttribute(String dataSource, String txtVal, String elName, String attrName, String attrVal) {
		try {
			String myTestObject = IPSUtils.getObjectRepoPath(IPSUtils.ELEMENT_WITH_ATTR, IPSUtils.COMMON);
			String dataSourceVal = IPSUtils.getSourceDataValue(dataSource, txtVal);
			switch (GlobalVariable.DEFAULT_PLATFORM) {
				case IPSUtils.WEB:
					WebUI.setText(findTestObject(myTestObject, [('value1'): elName, ('value2'): attrName, ('value3'): attrVal]), dataSourceVal)
					break
				case IPSUtils.DESKTOP:
					Windows.setText(findTestObject(myTestObject, [('value1'): elName, ('value2'): attrName, ('value3'): attrVal]), dataSourceVal)
					break
				case IPSUtils.ANDROID:
				case IPSUtils.IOS:
					Mobile.setText(findTestObject(myTestObject, [('value1'): elName, ('value2'): attrName, ('value3'): attrVal]), dataSourceVal, GlobalVariable.IMPLICIT_WAIT)
					break
				default:
					throw new IllegalArgumentException("Unsupported platform: ${GlobalVariable.DEFAULT_PLATFORM}")
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on CombinedMethods.setTextToElementWithAttribute() with exception: "+e);
		}
	}
	// ======================= END OF SET TEXT ======================= //

	// ======================= GET TEXT ======================= //
	def static getTextUsingObjectRepo(String objName, String pageName, String tempName) {
		String val = "";
		try {
			String myTestObject = IPSUtils.getObjectRepoPath(objName, pageName)
			switch (GlobalVariable.DEFAULT_PLATFORM) {
				case IPSUtils.WEB:
					val = WebUI.getText(findTestObject(myTestObject))
					break
				case IPSUtils.DESKTOP:
					val = Windows.getText(findWindowsObject(myTestObject))
					break
				case IPSUtils.ANDROID:
				case IPSUtils.IOS:
					val = Mobile.getText(findTestObject(myTestObject), GlobalVariable.IMPLICIT_WAIT)
					break
				default:
					throw new IllegalArgumentException("Unsupported platform: ${GlobalVariable.DEFAULT_PLATFORM}")
			}
			IPSUtils.setValue(tempName, val)
			println("Set value is: ${IPSUtils.getValue(tempName)}")
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on CombinedMethods.getTextUsingObjectRepo() with exception: "+e);
		}
	}

	def static getAttributeValueUsingObjectRepo(String objName, String pageName, String tempName) {
		String val = "";
		try {
			String myTestObject = IPSUtils.getObjectRepoPath(objName, pageName)
			switch (GlobalVariable.DEFAULT_PLATFORM) {
				case IPSUtils.WEB:
					val = WebUI.getAttribute(findTestObject(myTestObject), IPSUtils.ATTRIBUTE_VALUE)
					break
				case IPSUtils.DESKTOP:
					val = Windows.getAttribute(findWindowsObject(myTestObject), IPSUtils.ATTRIBUTE_VALUE)
					break
				case IPSUtils.ANDROID:
				case IPSUtils.IOS:
					val = Mobile.getAttribute(findTestObject(myTestObject), IPSUtils.ATTRIBUTE_VALUE, GlobalVariable.IMPLICIT_WAIT)
					break
				default:
					throw new IllegalArgumentException("Unsupported platform: ${GlobalVariable.DEFAULT_PLATFORM}")
			}
			IPSUtils.setValue(tempName, val)
			println("Set value is: ${IPSUtils.getValue(tempName)}")
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on CombinedMethods.getAttributeValueUsingObjectRepo() with exception: "+e);
		}
	}

	def static getTextUsingElementWithAttribute(String elName, String attrName, String attrVal, String tempName) {
		String val = "";
		try {
			String myTestObject = IPSUtils.getObjectRepoPath(IPSUtils.ELEMENT_WITH_ATTR, IPSUtils.COMMON)
			switch (GlobalVariable.DEFAULT_PLATFORM) {
				case IPSUtils.WEB:
					val = WebUI.getText(findTestObject(myTestObject, [('value1'): elName, ('value2'): attrName, ('value3'): attrVal]))
					break
				case IPSUtils.DESKTOP:
					val = Windows.getText(findWindowsObject(myTestObject, [('value1'): elName, ('value2'): attrName, ('value3'): attrVal]))
					break
				case IPSUtils.ANDROID:
				case IPSUtils.IOS:
					val = Mobile.getText(findWindowsObject(myTestObject, [('value1'): elName, ('value2'): attrName, ('value3'): attrVal]), GlobalVariable.IMPLICIT_WAIT)
					break
				default:
					throw new IllegalArgumentException("Unsupported platform: ${GlobalVariable.DEFAULT_PLATFORM}")
			}
			IPSUtils.setValue(tempName, val)
			println("Set value is: ${IPSUtils.getValue(tempName)}")
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on CombinedMethods.getTextUsingElementWithAttribute() with exception: "+e);
		}
	}
	// ==================== END OF GET TEXT ==================== //

	// ========================= SELECT DROPDOWN LIST ========================= //
	def static selectDropdownListItemUsingObjectName(String listItemVal, String listItemObj, String pageName) {
		try {
			String myTestObject = IPSUtils.getObjectRepoPath(listItemObj, pageName);
			String excelVal = IPSUtils.getRowData(listItemVal);
			switch (GlobalVariable.DEFAULT_PLATFORM) {
				case IPSUtils.WEB:
					WebUI.click(findTestObject(myTestObject, [('value1'): excelVal]))
					break
				case IPSUtils.DESKTOP:
					Windows.click(findWindowsObject(myTestObject, [('value1'): excelVal]))
					break
				case IPSUtils.ANDROID:
					Mobile.tap(findTestObject(myTestObject, [('value1'): excelVal]), GlobalVariable.IMPLICIT_WAIT)
					break
				case IPSUtils.IOS:
					Mobile.tap(findTestObject(myTestObject, [('value1'): excelVal]), GlobalVariable.IMPLICIT_WAIT)
					break
				default:
					throw new IllegalArgumentException("Unsupported platform: ${GlobalVariable.DEFAULT_PLATFORM}")
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on CombinedMethods.selectDropdownListItemUsingObjectName() with exception: "+e);
		}
	}

	// uses Object Repository/Web/Common/elementWithText
	def static selectListItemUsingLabel(String elName, String txtVal) {
		try {
			String excelVal = IPSUtils.getRowData(txtVal);
			CombinedMethods.clickElementWithText(elName, excelVal);
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on CombinedMethods.selectListItemUsingLabel() with exception: "+e);
		}
	}

	// uses Object Repository/Web/Common/elementWithAttribute
	def static selectListitemUsingAttribute(String elName, String attrName, String attrVal) {
		try {
			String excelVal = IPSUtils.getRowData(attrVal);
			CombinedMethods.clickElementWithAttribute(elName, attrName, excelVal)
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on CombinedMethods.selectListitemUsingAttribute() with exception: "+e);
		}
	}
	// ======================= END OF SELECT DROPDOWN LIST ======================= //


	// ========================= ASSERTION ========================= //
	def static verifyElementPresent(String objName, String pageName) {
		String myTestObject = IPSUtils.getObjectRepoPath(objName, pageName)
		try {
			switch (GlobalVariable.DEFAULT_PLATFORM) {
				case IPSUtils.WEB:
					WebUI.verifyElementPresent(findTestObject(myTestObject), GlobalVariable.IMPLICIT_WAIT)
					break
				case IPSUtils.DESKTOP:
					Windows.verifyElementPresent(findWindowsObject(myTestObject), GlobalVariable.IMPLICIT_WAIT);
					break
				case IPSUtils.ANDROID:
				case IPSUtils.IOS:
					Mobile.verifyElementExist(findTestObject(myTestObject), GlobalVariable.IMPLICIT_WAIT);
				default:
					throw new IllegalArgumentException("Unsupported platform: ${GlobalVariable.DEFAULT_PLATFORM}")
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on CombinedMethods.verifyElementPresent() with exception: "+e);
		}
	}

	def static verifyDynamicElementPresent(String elName, String attrName, String attrVal) {
		String myTestObject = IPSUtils.getObjectRepoPath(IPSUtils.ELEMENT_WITH_ATTR, IPSUtils.COMMON)
		try {
			switch (GlobalVariable.DEFAULT_PLATFORM) {
				case IPSUtils.WEB:
					WebUI.verifyElementPresent(findTestObject(myTestObject, [('value1'): elName, ('value2'): attrName, ('value3'): attrVal]), GlobalVariable.IMPLICIT_WAIT)
					break
				case IPSUtils.DESKTOP:
					Windows.verifyElementPresent(findWindowsObject(myTestObject, [('value1'): elName, ('value2'): attrName, ('value3'): attrVal]), GlobalVariable.IMPLICIT_WAIT);
					break
				case IPSUtils.ANDROID:
				case IPSUtils.IOS:
					Mobile.verifyElementExist(findWindowsObject(myTestObject, [('value1'): elName, ('value2'): attrName, ('value3'): attrVal]), GlobalVariable.IMPLICIT_WAIT);
				default:
					throw new IllegalArgumentException("Unsupported platform: ${GlobalVariable.DEFAULT_PLATFORM}")
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on CombinedMethods.verifyDynamicElementPresent() with exception: "+e);
		}
	}

	def static verifyElementVisible(String objName, String pageName) {
		String myTestObject = IPSUtils.getObjectRepoPath(objName, pageName)
		try {
			switch (GlobalVariable.DEFAULT_PLATFORM) {
				case IPSUtils.WEB:
					WebUI.verifyElementVisible(findTestObject(myTestObject));
					break
				case IPSUtils.DESKTOP:
				//Windows.verifyElementVisible(findWindowsObject(myTestObject), 10);
					break
				case IPSUtils.ANDROID:
				case IPSUtils.IOS:
					Mobile.verifyElementVisible(findTestObject(myTestObject), GlobalVariable.IMPLICIT_WAIT);
				default:
					throw new IllegalArgumentException("Unsupported platform: ${GlobalVariable.DEFAULT_PLATFORM}")
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on CombinedMethods.verifyElementVisible() with exception: "+e);
		}
	}

	def static verifyDynamicElementVisible(String elName, String attrName, String attrVal) {
		String myTestObject = IPSUtils.getObjectRepoPath(IPSUtils.ELEMENT_WITH_ATTR, IPSUtils.COMMON)
		try {
			switch (GlobalVariable.DEFAULT_PLATFORM) {
				case IPSUtils.WEB:
					WebUI.verifyElementVisible(findTestObject(myTestObject, [('value1'): elName, ('value2'): attrName, ('value3'): attrVal]))
					break
				case IPSUtils.DESKTOP:
				//Windows.verifyElementVisible(findWindowsObject(myTestObject, [('value1'): elName, ('value2'): attrName, ('value3'): attrVal]));
					break
				case IPSUtils.ANDROID:
				case IPSUtils.IOS:
					Mobile.verifyElementVisible(findWindowsObject(myTestObject, [('value1'): elName, ('value2'): attrName, ('value3'): attrVal]), GlobalVariable.IMPLICIT_WAIT);
				default:
					throw new IllegalArgumentException("Unsupported platform: ${GlobalVariable.DEFAULT_PLATFORM}")
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on CombinedMethods.verifyDynamicElementVisible() with exception: "+e);
		}
	}

	def static verifyElementMatchesValue(String dataSource1, String dataSourceVal1, dataSource2, String dataSourceVal2) {
		String expectedValue = IPSUtils.getSourceDataValue(dataSource1, dataSourceVal1)
		String actualValue = IPSUtils.getSourceDataValue(dataSource2, dataSourceVal2)
		try {
			Assert.assertEquals(actualValue, expectedValue, IPSUtils.VALUES_DONT_MATCH_ERROR)
		} catch (Exception e) {
			KeywordUtil.markFailed("Error on CombinedMethods.verifyElementMatchesValue() with exception: "+e);
		}
	}

	// ====================== END OF ASSERTION ===================== //

	// ========================= STORED VALUES ========================= //


	// ===================== END OF STORED VALUES ====================== //
	def static closeApplication() {
		switch (GlobalVariable.DEFAULT_PLATFORM) {
			case IPSUtils.WEB:
				WebUI.closeBrowser()
				break
			case IPSUtils.DESKTOP:
				Windows.closeApplication() // for checking
				Windows.waitForElementPresent(findWindowsObject("DSK/HomeScreen/btnClose"), GlobalVariable.IMPLICIT_WAIT)
			//Windows.click(findWindowsObject("DSK/HomeScreen/btnClose"))
				Windows.waitForElementPresent(findWindowsObject("DSK/Login/btnYes"), GlobalVariable.IMPLICIT_WAIT)
				Windows.click(findWindowsObject("DSK/Login/btnYes"))
				break
			case IPSUtils.ANDROID:
				Mobile.closeApplication()
				break
			case IPSUtils.IOS:
				Mobile.closeApplication()
				break
			default:
				throw new IllegalArgumentException("Unsupported platform: ${GlobalVariable.DEFAULT_PLATFORM}")
		}
	}

	def static addDelay(int x) {
		switch (GlobalVariable.DEFAULT_PLATFORM) {
			case IPSUtils.WEB:
				WebUI.delay(x)
				break
			case IPSUtils.DESKTOP:
				Windows.delay(x)
				break
			case IPSUtils.ANDROID:
			case IPSUtils.IOS:
				Mobile.delay(x)
				break
			default:
				throw new IllegalArgumentException("Unsupported platform: ${GlobalVariable.DEFAULT_PLATFORM}")
		}
	}
}
