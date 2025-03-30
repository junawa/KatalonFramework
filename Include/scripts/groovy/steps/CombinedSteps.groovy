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
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import utils.IPSUtils
import ipsKeywords.IPSNavigation
import ipsKeywords.IPSPageAssertion
import ipsKeywords.IPSKeywords
import cucumber.api.Scenario
import cucumber.api.java.Before
import cucumber.api.java.en.Given
import cucumber.api.java.en.When
import cucumber.api.java.en.Then
import methods.DesktopMethods
import methods.CombinedMethods


public class CombinedSteps {

	@Before
	def void before(Scenario scenario) {
		IPSUtils.setupSheetName(scenario);
	}

	//needs to revisit this
	@Given("user {int} launches the {string} application")
	def user_successfully_logs_in_to_the_app(int rowNo, String platform){
		IPSUtils.setRowData(rowNo);
		IPSKeywords.openApp(platform);
	}

	// ========================= CLICK ACTIONS ========================= //
	/**
	 * @param String objName - object name to fetch from object repository
	 * @param String pageName - folder name where object is located
	 * @param String elName - element name from DOM (web, application)
	 * @param String attrName - attribute name from DOM (web, application)
	 * @param String attrVal - attribute value from DOM (web, application)
	 * @param String txtVal - text value
	 */

	// Ex: user clicks "btnMakeAppointment" in "CuraHomePage" page
	@When("user clicks {string} in {string} page")
	def user_clicks_element_in_page(String objName, String pageName){
		CombinedMethods.clickElement(objName, pageName);
	}

	// Ex: user clicks object with name "Finance"
	@When("user clicks object with name {string}")
	def user_clicks_object_with_name(String txtVal){
		CombinedMethods.clickElementWithName(txtVal);
	}

	// Ex: user clicks element "a" with attr "id" - "btn-make-appointment"
	@When("user clicks element {string} with attr {string} - {string}")
	def user_clicks_text_in_page(String elName, String attrName, String attrVal){
		CombinedMethods.clickElementWithAttribute(elName, attrName, attrVal);
	}

	// Ex: user clicks element "a" with value "Make Appointment"
	@When("user clicks element {string} with value {string}")
	def user_clicks_element_with_value_in_page(String elName, String txtVal){
		CombinedMethods.clickElementWithText(elName, txtVal);
	}

	// Ex: user clicks object with attribute "id" containing value "btn-make-appointment" and text "Make Appointment"
	@When("user clicks object with attribute {string} containing value {string} and text {string}")
	def user_clicks_object_with_attr_containg_value_and_Text(String attrName, String attrValue, String txtVal){
		CombinedMethods.clickDynamicElementWithValue(attrName, attrValue, txtVal);
	}
	// ====================== END OF CLICK ACTIONS ====================== //

	// ========================= SET TEXT ========================= //
	/**	
	 * @param String txtVal - text value to fetch from different sources (excel, feature, etc.)
	 * @param String dataSource - type of data source to use
	 */

	// Ex: user set text "feature" value "John Doe" on field "txtUsername" in "Login" page
	@When("user set text {string} value {string} on field {string} in {string} page")
	def user_set_text_on_field_page(String dataSource, String txtVal, String objName, String pageName){
		CombinedMethods.setTextToElement(dataSource, txtVal, objName, pageName);
	}

	// Ex: user set text "feature" value "John Doe" on current field
	@When("user set text {string} value {string} on current field")
	def user_set_text_on_current_field(String dataSource, String txtVal){
		CombinedMethods.setTextToCurrentElement(dataSource, txtVal);
	}

	// Ex: user set text "excel" value "WebUsername" on element "input" with attr "id" - "txt-username"
	@When("user set text {string} value {string} on element {string} with attr {string} - {string}")
	def user_setText_to_element_with_value_in_page(String dataSource, String txtVal, String elName, String attrName, String attrValue){
		CombinedMethods.setTextToElementWithAttribute(dataSource, txtVal, elName, attrName, attrValue);
	}

	// ======================= END OF SET TEXT ======================= //

	// ========================= GET TEXT ========================= //
	// Ex: user get text from "btnMakeAppointment" in "CuraHomePage" page and store as "MakeAppointmentLabel"
	@When("user get text from {string} in {string} page and store as {string}")
	def user_get_text_from_object_repo(String objName, String pageName, String tempName){
		CombinedMethods.getTextUsingObjectRepo(objName, pageName, tempName);
	}

	// Ex: user get text attr value from "txtDemoUsername" in "Login" page and store as "DemoUsername"
	@When("user get text attr value from {string} in {string} page and store as {string}")
	def user_get_text_attr_value_from_object_repo(String objName, String pageName, String tempName){
		CombinedMethods.getAttributeValueUsingObjectRepo(objName, pageName, tempName);
	}

	// Ex: user get text from element "a" with attr "id" - "btn-make-appointment" and store as "MakeAppointmentLabel"
	@When("user get text from element {string} with attr {string} - {string} and store as {string}")
	def user_get_text_from_element_with_attribute(String elName, String attrName, String attrVal, String tempName){
		CombinedMethods.getTextUsingElementWithAttribute(elName, attrName, attrVal, tempName);
	}
	// ====================== END OF GET TEXT ====================== //

	// ========================= SELECT DROPDOWN LIST ========================= //
	/**
	 * @param String listObj - list object name to fetch from object repository
	 * @param String listElName - list element name from DOM (web, application)
	 * @param String listAttrName - list attribute name from DOM (web, application)	 
	 * @param String listAttrVal - list attribute value from DOM (web, application)
	 * 
	 * @param String listItemElName - listItem name from DOM (web, application)
	 * @param String listItemAttrName - listItem attribute name from DOM (web, application)
	 * @param String listItemAttrVal - listItem attribute value from excel file
	 * @param String listItemVal - dynamic listItem value from excel file
	 */
	// Ex: user selects list item "FacilityListItem" in "listItemFacility" - "CuraAppointment" page
	@When("user selects list item {string} in {string} - {string} page")
	def user_selects_list_item_in_object_repository(String listItemVal, String listItemObj, String pageName){
		CombinedMethods.selectDropdownListItemUsingObjectName(listItemVal, listItemObj, pageName);
	}

	// Ex: 	user selects list item "option" with label "FacilityListItemName"
	@When("user selects list item {string} with label {string}")
	def user_selects_list_item_using_label(String elName, String txtVal){
		CombinedMethods.selectListItemUsingLabel(elName, txtVal);
	}

	// Ex: 	user selects list item "option" with attr "option" - "FacilityListItemName"
	@When("user selects list item {string} with attr {string} - {string}")
	def user_selects_list_item_with_attr(String elName, String attrName, String attrVal){
		CombinedMethods.selectListitemUsingAttribute(elName, attrName, attrVal);
	}
	// ===================== END OF SELECT DROPDOWN LIST ======================= //

	// ========================= PAGE NAVIGATION ========================= //

	// Ex: user navigates to "Profile" page
	@When("user navigates to {string} page") // module/page/screen?
	def user_navigates_to_platform_page(String pageName){
		IPSNavigation.navigateToPlatformPage(pageName);
	}

	// ====================== END OF PAGE NAVIGATION ====================== //

	// ========================= ASSERTION ========================= //

	// Ex: user should see the "Profile" page
	@Then("user should see the {string} page") // module/page/screen?
	def user_should_see_the_platform_page(String pageName){
		IPSPageAssertion.assertPlatformPage(pageName);
	}

	// Ex: user verifies if "btnMakeAppointment" in "CuraHomePage" page is present
	@Then("user verifies if {string} in {string} page is present")
	def user_verifies_if_object_present(String objName, String pageName){
		CombinedMethods.verifyElementPresent(objName, pageName);
	}

	// Ex: user verifies if element "a" with attr value {string} - {string} is present
	@Then("user verifies if element {string} with attr {string} value {string} is present")
	def user_verifies_if_element_with_attr_is_present(String elName, String attrName, String attrVal){
		CombinedMethods.verifyDynamicElementPresent(elName, attrName, attrVal);
	}


	// Ex: user verifies if "btnMakeAppointment" in "CuraHomePage" page is visible
	@Then("user verifies if {string} in {string} page is visible")
	def user_verifies_if_element_visible(String objName, String pageName){
		CombinedMethods.verifyElementVisible(objName, pageName);
	}

	// Ex: user verifies if element "a" with attr value {string} - {string} is visible
	@Then("user verifies if element {string} with attr {string} value {string} is visible")
	def user_verifies_if_element_with_attr_is_visible(String elName, String attrName, String attrVal){
		CombinedMethods.verifyDynamicElementVisible(elName, attrName, attrVal);
	}

	// Ex: user verifies if "stored" value "DemoUsername" matches "excel" value "WebUsername"
	@Then("user verifies if {string} value {string} matches {string} value {string}") // module/page/screen?
	def user_checks_if_the_element_in_page_is_present_(String dataSource1, String dataVal1, String dataSource2, String dataVal2){
		CombinedMethods.verifyElementMatchesValue(dataSource1, dataVal1, dataSource2, dataVal2);
	}

	// MORE ASSERTIONS HERE

	// ====================== END OF ASSERTION ====================== //

	// ========================= STORED VALUES ========================= //

	// ===================== END OF STORED VALUES ====================== //

	//needs to revisit this
	@Then("user logs out")
	def user_logs_out() {
		CombinedMethods.closeApplication();
	}
	
	@When("user waits {int} seconds")
	def user_logs_out(int x) {		
		CombinedMethods.addDelay(x);
	}
	
	@When("user press shortcut {string}")
	public void user_press_shortcut(String key) {
		IPSUtils.sendKeyCombination(key)
	}
	
	@When("user press {int} {string}")
	public void user_press_shortcut(int num, String key) {
		IPSUtils.sendKeyMultipleTimes(num, key)
	}

	@When("user switches to {string} app")
	def user_switches_to_app(String platform) {
		GlobalVariable.DEFAULT_PLATFORM = platform;
	}
}
