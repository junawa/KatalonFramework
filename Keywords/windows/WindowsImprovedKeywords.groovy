package windows

import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.regex.Pattern

import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.testobject.WindowsTestObject
import com.kms.katalon.core.testobject.WindowsTestObject.LocatorStrategy
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.windows.driver.WindowsDriverFactory
import com.kms.katalon.core.testobject.WindowsTestObject

public class WindowsImprovedKeywords {

	@Keyword
	def static startApplicationWithExactTitle(String application, String title) {
		Windows.startApplicationWithTitle(application, GlobalVariable.APP_NAME)
		KeywordUtil.logInfo("Application: " + application + "Title: " + title + " has been started")
		Windows.delay(10) // This is optional: Waiting for the main window to show up
		switchToWindowTitleNew(title);
	}

	@Keyword
	def static switchToWindowTitleNew(String title) {
		KeywordUtil.logInfo("Start switchToWindowTitle: " + title)
		WindowsTestObject windowObject = new WindowsTestObject()
		windowObject.setLocator(title)
		windowObject.setLocatorStrategy(LocatorStrategy.NAME)
		Windows.switchToDesktop()
		Windows.switchToWindow(windowObject)
		Windows.switchToApplication()
		KeywordUtil.logInfo("Done switchToWindowTitle: " + title)
	}

	@Keyword
	def static startApplicationWithTitlePattern(String application, String titleRegex) {
		Windows.startApplicationWithTitle(application, '')
		KeywordUtil.logInfo("Application has been started")
		Windows.delay(5) // This is optional: Waiting for the main window to show up
		switchToWindowTitlePattern(titleRegex);
	}

	@Keyword
	def static switchToWindowAttrPattern(String windowAttr, String titleRegex) {
		KeywordUtil.logInfo("Start switchToWindowTitlePattern: " + titleRegex)
		Windows.switchToDesktop()

		def allWindows = Windows.getDriver().findElementsByXPath("/*/*[name() = 'Window' or name() = 'Pane']")
		def targetWindow = findWindowWithTitle(allWindows, titleRegex, windowAttr)
		if (targetWindow == null) {
			return;
		}

		WindowsTestObject windowObject = new WindowsTestObject()
		def locatorStategy;
		windowObject.setLocator(targetWindow.getAttribute(windowAttr))
		switch (windowAttr) {
			case "Name":
				locatorStategy = LocatorStrategy.NAME;
				break;
			case "ClassName":
				locatorStategy = LocatorStrategy.CLASS_NAME;
				break;
			default:
				throw new IllegalArgumentException("Unsupported window attribute: ${windowAttr}")
		}
		
		windowObject.setLocatorStrategy(locatorStategy)
		Windows.switchToWindow(windowObject)

		Windows.switchToApplication()
		KeywordUtil.logInfo("Done switchToWindowTitlePattern: " + titleRegex)
	}

	@Keyword
	def static startApplicationWithTitleContains(String application, String aPartOfTitle) {
		Windows.startApplicationWithTitle(application, '')
		KeywordUtil.logInfo("Application: " + application + "Title contains: " + aPartOfTitle + " has been started")
		Windows.delay(5) // This is optional: Waiting for the main window to show up
		switchToWindowWithTitleContains(aPartOfTitle);
	}

	@Keyword
	def static switchToWindowWithTitleContains(String aPartOfTitle) {
		KeywordUtil.logInfo("Start switchToWindowTitlePattern: " + aPartOfTitle)
		Windows.switchToDesktop()

		def targetWindow = Windows.getDriver().findElementByXPath("/*/*[(name() = 'Window' or name() = 'Pane') and contains(@Name, '${aPartOfTitle}')]")
		if (targetWindow == null) {
			return;
		}

		WindowsTestObject windowObject = new WindowsTestObject()
		windowObject.setLocator(targetWindow.getAttribute("Name"))
		windowObject.setLocatorStrategy(LocatorStrategy.NAME)
		Windows.switchToWindow(windowObject)

		Windows.switchToApplication()
		KeywordUtil.logInfo("Done switchToWindowTitlePattern: " + aPartOfTitle)
	}

	def static WebElement findWindowWithTitle(List<WebElement> windows, String titlePattern, String windowAttr) {		
		for (def windowI : windows) {
			def attr = windowI.getAttribute(windowAttr)==null? "":windowI.getAttribute(windowAttr);
			if (Pattern.matches(titlePattern, attr)) {
				return windowI;
			}
		}
		return null;
	}

	@Keyword
	def static validateWindowsSetText(String testObject, String text) {
		try {
			if (!text.isEmpty()) {
				Windows.setText(findWindowsObject(testObject), text)
			}
		} catch(Exception e) {
			KeywordUtil.markFailed("Error on Keyword WindowsImprovedKeywords.validateWindowsSetText() - "+e)
		}
	}
	
	def static switchToApplicationByAutomationId(String automationId) {
		try {
			// Get the Windows driver
			def driver = WindowsDriverFactory.getWindowsDriver()
			// Find the window using AutomationId
			WebElement targetWindow = driver.findElementByXPath("//*[@AutomationId='${automationId}']")
			if (targetWindow != null) {
				// Create a WindowsTestObject for switching
				WindowsTestObject windowObject = new WindowsTestObject()
				windowObject.setLocator(automationId)
				windowObject.setLocatorStrategy("AutomationId")
				// Switch to the identified window
				Windows.switchToWindow(windowObject)
				println("Switched to application with AutomationId: ${automationId}")
				return true
			} else {
				println("Application with AutomationId: ${automationId} not found!")
				return false
			}
		} catch (Exception e) {
			println("Error switching to application: " + e.getMessage())
			return false
		}
	}
}
