package utils

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
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.util.KeywordUtil
import java.awt.Robot
import java.awt.event.KeyEvent
import java.util.HashMap;
import java.util.Map;

public class IPSUtils {

	private static final String WEB = "Web";
	private static final String DESKTOP = "DSK";
	private static final String ANDROID = "AOS";
	private static final String IOS = "iOS";
	private static final String API = "API";

	private static final String EXCEL_FILE_DATASOURCE = "excel";
	private static final String FEATURE_FILE_DATASOURCE= "feature";
	private static final String STORED_VALUE_DATASOURCE= "stored";

	private static final String COMMON = "Common";
	private static final String ELEMENT_WITH_ATTR = "elementWithAttribute";
	private static final String ELEMENT_WITH_TEXT = "elementWithText";
	private static final String OBJECT_NAME = "objectName";
	private static final String ATTR_WITH_TEXT = "attrWithText";

	private static final String ATTRIBUTE_VALUE = "value";
	private static final String VALUES_DONT_MATCH_ERROR = "Values don't match.";

	private static final String AVALOQ_CLASSNAME = "WindowsForms10.Window.8.app.0.1ed9395_r7_ad1";
	private static final Robot robot = new Robot();
	/**
	 * Get/Set sheetname to use for data files
	 */
	def static String sheetName;
	def static String getSheetName() {
		return sheetName;
	}

	def static void setSheetName(String name) {
		sheetName = name;
	}

	/**
	 * Setup data file to use using sheet name across scenario execution.
	 * Only executed once per scenario outline
	 * @param Scenario scenario - get scenario objects of feature files like tags
	 */
	def static void setupSheetName(Scenario scenario) {
		String path, folder, module, sheet = "";
		boolean folderCtr, moduleCtr, sheetCtr = false;
		for (String tag : scenario.getSourceTagNames()) {
			if (tag.startsWith("@Folder") && !folderCtr) {
				folder = tag.substring(7);
				folderCtr = true;
			}
			if (tag.startsWith("@Module") && !moduleCtr) {
				module =  tag.substring(7);
				moduleCtr = true;
			}
			if (tag.startsWith("@Sheet") && !sheetCtr) {
				sheet =  tag.substring(6);
				sheetCtr = true;
			}
		}

		path = "$folder/$module/$sheet";
		IPSUtils.setSheetName(path);
	}

	def static List<Map<String, String>> getAllRowData(String dataFilePath, int rowNum){
		def excelData = TestDataFactory.findTestData(dataFilePath);
		List<Map<String, String>> rowDataList = [];
		int columnCount = excelData.getColumnNumbers();
		Map<String, String> rowData = [:];

		for (int col = 1; col <= columnCount; col++) {
			String columnName = excelData.getColumnNames()[col - 1]; // Get the column name
			String cellValue = excelData.getValue(columnName, rowNum); // Get the cell value for the specified row
			rowData.put(columnName, cellValue); // Add the cell value to the map
		}
		rowDataList.add(rowData);
		return rowDataList;
	}

	def static Map<String, String> excelRowData = [:];
	def static void setRowData(int rowNo) {
		List<Map<String, String>> dataList = IPSUtils.getAllRowData("${GlobalVariable.ENV}/${IPSUtils.getSheetName()}", rowNo)
		excelRowData = dataList.get(0);
	}

	def static String getRowData(String name) {
		excelRowData.get(name);
	}

	def static void killApplication(String processName) {
		try {
			String command = "taskkill /F /IM ${processName}"
			Process process = Runtime.getRuntime().exec(command)
			process.waitFor()
			KeywordUtil.logInfo("Terminated process: ${processName}")
		} catch (Exception e) {
			KeywordUtil.logInfo("Failed to terminate process: ${processName}. Error: ${e.message}")
		}
	}

	def static void ensureWinAppDriverIsRunning() {
		try {
			// Check if WinAppDriver is running
			Process checkProcess = Runtime.getRuntime().exec("tasklist | findstr WinAppDriver.exe")
			BufferedReader reader = new BufferedReader(new InputStreamReader(checkProcess.getInputStream()))
			String line = reader.readLine()
			checkProcess.waitFor()

			if (line != null && line.contains("WinAppDriver.exe")) {
				KeywordUtil.logInfo("WinAppDriver is already running.")
			} else {
				KeywordUtil.logInfo("WinAppDriver is not running. Starting it now...")
				String winAppDriverPath = "C:\\Program Files (x86)\\Windows Application Driver\\WinAppDriver.exe"
				Runtime.getRuntime().exec("cmd /c start \"\" \"$winAppDriverPath\"")
				Thread.sleep(5000)
				KeywordUtil.logInfo("WinAppDriver started successfully.")
			}
		} catch (Exception e) {
			e.printStackTrace()
			throw new RuntimeException("Failed to check or start WinAppDriver.")
		}
	}

	private static Map<String, String> storedValues = new HashMap<>()
	def static void setValue(String key, String value) {
		storedValues.put(key, value)
	}
	def static String getValue(String key) {
		return storedValues.get(key)
	}

	def static String getObjectRepoPath(String objName, String pageName) {
		String repoPath = "Object Repository/${GlobalVariable.DEFAULT_PLATFORM}/${pageName}/${objName}";		
		return repoPath;
	}


	def static List<Map<String, Integer>> swipeCoordinates = [];


	/***
	 * Set mobile swipe coordinates across scenario execution
	 * Only executed once per scenario outline
	 */
	def static List<Map<String, Integer>> setMobileSwipeScreenCoordinates() {
		int xCoordinate = GlobalVariable.DEVICE_WIDTH;
		int yCoordinate = GlobalVariable.DEVICE_HEIGHT;
		List<Map<String, Integer>> rowDataList = [];
		Map<String, Integer> rowData = [:];
		try {
			// UP
			rowData.put("xStart", (int) (xCoordinate * 0.50));
			rowData.put("xEnd", (int) (xCoordinate * 0.50));
			rowData.put("yStart", (int) (yCoordinate * 0.75));
			rowData.put("yEnd", (int) (yCoordinate * 0.25));
			swipeCoordinates.add(rowData);
			// DOWN
			rowData = new HashMap<>();
			rowData.put("xStart", (int) (xCoordinate * 0.50));
			rowData.put("xEnd", (int) (xCoordinate * 0.50));
			rowData.put("yStart", (int) (yCoordinate * 0.25));
			rowData.put("yEnd", (int) (yCoordinate * 0.75));
			swipeCoordinates.add(rowData);
			// LEFT
			rowData = new HashMap<>();
			rowData.put("xStart", (int) (xCoordinate * 0.75));
			rowData.put("xEnd", (int) (xCoordinate * 0.25));
			rowData.put("yStart", (int) (yCoordinate * 0.5));
			rowData.put("yEnd", (int) (yCoordinate * 0.5));
			swipeCoordinates.add(rowData);
			// RIGHT
			rowData = new HashMap<>();
			rowData.put("xStart", (int) (xCoordinate * 0.25));
			rowData.put("xEnd", (int) (xCoordinate * 0.75));
			rowData.put("yStart", (int) (yCoordinate * 0.50));
			rowData.put("yEnd", (int) (yCoordinate * 0.50));
			swipeCoordinates.add(rowData);

			return rowDataList;
		} catch(Exception e) {
			KeywordUtil.markFailed("Error on IPSUtils.setMobileSwipeScreenCoordinates() with exception: "+e);
		}
	}

	/**
	 * Get swipe coordinates based on saved dimension values
	 * @param direction - swipe direction like UP, DOWN, LEFT, RIGHT
	 * @param name - swipe direction properties like xStart, xEnd, yStart, yEnd
	 * @example IPSUtils.getSwipeData("UP", "xStart");
	 */

	def static String getSwipeData(String direction, String name) {
		int index;
		try {
			switch (direction) {
				case "UP":
					index = 0;
					break;
				case "DOWN":
					index = 1;
					break;
				case "LEFT":
					index = 2;
					break;
				case "RIGHT":
					index = 3;
					break;
				default:
					KeywordUtil.logInfo("getSwipeData - Invalid Direction: " + direction)
			}

			List<Map<String, String>> dataList = IPSUtils.swipeCoordinates.get(index)
			return swipeCoordinates.get(name);
		}
		catch (Exception e) {
			KeywordUtil.markFailed("Error on IPSUtils.getSwipeData() with exception: "+e);
		}
	}

	def static void setDeviceDimension() {
		try {
			GlobalVariable.DEVICE_WIDTH = Mobile.getDeviceWidth()
			GlobalVariable.DEVICE_HEIGHT = Mobile.getDeviceHeight()
		} catch(Exception err) {
			KeywordUtil.logInfo("Error: " + err)
		}
	}

	def static String getSourceDataValue(String dataSource, String dataSourceVal) {
		String val = "";
		try {
			switch (dataSource) {
				case IPSUtils.EXCEL_FILE_DATASOURCE:
					val = IPSUtils.getRowData(dataSourceVal)
					break;
				case IPSUtils.FEATURE_FILE_DATASOURCE:
					val = dataSourceVal
					break;
				case IPSUtils.STORED_VALUE_DATASOURCE:
					val = IPSUtils.getValue(dataSourceVal)
					break;
				default:
					throw new IllegalArgumentException("Unsupported data source: ${dataSource}")
			}
		}
		catch (Exception e) {
			KeywordUtil.markFailed("Error on CombinedMethods.getSourceDataValue() with exception: "+e)
		}

		return val;
	}

	private static final Map<String, Integer> keyEventMap = [:]
	
	def static void setKeyEventMap() {
		KeyEvent.class.fields.findAll { it.name.startsWith('VK_') }.each {
			int keyCode = it.getInt(null)
			String keyName = it.name.substring(3) // Remove "VK_"

			// Store uppercase version
			keyEventMap[keyName] = keyCode
			// Store lowercase version (if it's a single letter A-Z)
			if (keyName.length() == 1 && keyName[0] in ('A'..'Z')) {
				keyEventMap[keyName.toLowerCase()] = keyCode
			}
		}

		keyEventMap.putAll([
			"TAB": KeyEvent.VK_TAB,
			"ENTER": KeyEvent.VK_ENTER,
			"SPACE": KeyEvent.VK_SPACE,
			"ESC": KeyEvent.VK_ESCAPE,
			"BACKSPACE": KeyEvent.VK_BACK_SPACE,
			"DELETE": KeyEvent.VK_DELETE,
			"UP": KeyEvent.VK_UP,
			"DOWN": KeyEvent.VK_DOWN,
			"LEFT": KeyEvent.VK_LEFT,
			"RIGHT": KeyEvent.VK_RIGHT,
			"HOME": KeyEvent.VK_HOME,
			"END": KeyEvent.VK_END,
			"PAGEUP": KeyEvent.VK_PAGE_UP,
			"F1": KeyEvent.VK_F1,
			"F2": KeyEvent.VK_F2,
			"F3": KeyEvent.VK_F3,
			"F4": KeyEvent.VK_F4,
			"F5": KeyEvent.VK_F5,
			"F6": KeyEvent.VK_F6,
			"F7": KeyEvent.VK_F7,
			"F8": KeyEvent.VK_F8,
			"F9": KeyEvent.VK_F9,
			"F10": KeyEvent.VK_F10,
			"F11": KeyEvent.VK_F11,
			"F12": KeyEvent.VK_F12
		])
	}

	static int getKeyEvent(String key) {
		return keyEventMap.getOrDefault(key, -1)
	}	

	def static void pressKey(String key) {
		int keyCode = getKeyEvent(key)
		if (keyCode == -1) {
			return
		}

		robot.keyPress(keyCode)
		robot.keyRelease(keyCode)
	}

	static void typeText(String word) {
		for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i)
            boolean isUpperCase = Character.isUpperCase(letter)
            int keyCode = getKeyEvent(Character.toString(letter).toUpperCase())
 
            if (keyCode == -1) {              
                continue
            }
 
            if (isUpperCase) {
                robot.keyPress(KeyEvent.VK_SHIFT)
            }
 
            robot.keyPress(keyCode)
            robot.keyRelease(keyCode)
 
            if (isUpperCase) {
                robot.keyRelease(KeyEvent.VK_SHIFT)
            } 
            Thread.sleep(50) 
        }      
    }
	

	// Press combination of keys using "+"
	def static void sendKeyCombination(String keyCombo) {		
		String[] keys = keyCombo.toUpperCase().split("\\+")

		List<Integer> keyCodes = keys.collect { getKeyEvent(it) }.findAll { it != -1 }

		if (keyCodes.size() == keys.size()) {
			keyCodes.each { robot.keyPress(it) }
			Thread.sleep(100)
			keyCodes.reverse().each { robot.keyRelease(it) }
		}
	}

	def static sendKeyMultipleTimes(int num, String keyCombo) {
		for(int x = 0; x<num; x++) {
			sendKeyCombination(keyCombo);
		}
	}
}
