Feature: Sample Web

  @TC1 @FolderF1 @ModuleM1 @SheetWebDataFile1 @Web @Clicks @TestOps
  Scenario Outline: Web Login: Click - Element
    Given user <RowNo> launches the "Web" application
    When user clicks "btnMakeAppointment" in "CuraHomePage" page
    And user set text "excel" value "WebUsername" on field "txtUsername" in "Login" page
    And user set text "excel" value "WebPassword" on field "txtPassword" in "Login" page           		  
    And user clicks "btnLogin" in "Login" page
    Then user logs out

    Examples: 
      | RowNo |
      |     1 |
      
 @TC1A @FolderF1 @ModuleM1 @SheetWebDataFile1 @Web @Clicks
  Scenario Outline: Web Login: Click - Element with Attribute
    Given user <RowNo> launches the "Web" application     
    When user clicks element "a" with attr "id" - "btn-make-appointment"
    And user set text "excel" value "WebUsername" on field "txtUsername" in "Login" page
    And user set text "excel" value "WebPassword" on field "txtPassword" in "Login" page
    And user clicks element "button" with attr "id" - "btn-login"
    Then user logs out

    Examples: 
      | RowNo |
      |     1 |     
      
 	@TC2 @FolderF1 @ModuleM1 @SheetWebDataFile1 @Web @Clicks
  Scenario Outline: Web Login: Click -  Element With Text
    Given user <RowNo> launches the "Web" application
    When user clicks element "a" with value "Make Appointment"
   	And user set text "excel" value "WebUsername" on field "txtUsername" in "Login" page
    And user set text "excel" value "WebPassword" on field "txtPassword" in "Login" page
    And user clicks element "button" with value "Login"
    Then user logs out

    Examples: 
      | RowNo |
      |     1 |
      
 	@TC3 @FolderF1 @ModuleM1 @SheetWebDataFile1 @Web @Clicks @SetText
  Scenario Outline: Web Login: Click - Dynamic element with Text
    Given user <RowNo> launches the "Web" application        
    When user clicks object with attribute "id" containing value "btn-make-appointment" and text "Make Appointment"
    And user set text "feature" value "John Doe" on field "txtUsername" in "Login" page
    And user set text "feature" value "ThisIsNotAPassword" on field "txtPassword" in "Login" page
    And user clicks object with attribute "type" containing value "submit" and text "Login"
    Then user logs out

    Examples: 
      | RowNo |
      |     1 |
      
  @TC4 @FolderF1 @ModuleM1 @SheetWebDataFile1 @Web @SetText
  Scenario Outline: Web Login: Set Text - Element With Attribute
    Given user <RowNo> launches the "Web" application
    When user clicks "btnMakeAppointment" in "CuraHomePage" page        
    And user set text "excel" value "WebUsername" on element "input" with attr "id" - "txt-username"
    And user set text "excel" value "WebPassword" on element "input" with attr "id" - "txt-password"
    And user clicks "btnLogin" in "Login" page
    Then user logs out

    Examples: 
      | RowNo |
      |     1 |     
      
  # WebListItem Sheet #           
  @TC5 @FolderF1 @ModuleM1 @SheetWebListItem @Web @SelectDropdown
  Scenario Outline: Web: Select Dropdown ListItem using Object Name
    Given user <RowNo> launches the "Web" application
    When user clicks "btnMakeAppointment" in "CuraHomePage" page
    And user set text "excel" value "WebUsername" on field "txtUsername" in "Login" page
    And user set text "excel" value "WebPassword" on field "txtPassword" in "Login" page	   
    And user clicks "btnLogin" in "Login" page 
    And user clicks "listFacility" in "CuraAppointment" page   
    And user selects list item "FacilityListItem" in "listItemFacility" - "CuraAppointment" page
    And user clicks "chkMedicaid" in "CuraAppointment" page 
    Then user logs out
          
    Examples: 
      | RowNo |
      |     1 |
      
  @TC6 @FolderF1 @ModuleM1 @SheetWebListItem @Web @SelectDropdown
  Scenario Outline: Web: Select Dropdown ListItem using Label
    Given user <RowNo> launches the "Web" application
    When user clicks "btnMakeAppointment" in "CuraHomePage" page
    And user set text "excel" value "WebUsername" on field "txtUsername" in "Login" page
    And user set text "excel" value "WebPassword" on field "txtPassword" in "Login" page	   
    And user clicks "btnLogin" in "Login" page
    And user clicks "listFacility" in "CuraAppointment" page           
    And user selects list item "option" with label "FacilityListItem"    
    And user clicks "chkMedicaid" in "CuraAppointment" page
    Then user logs out
    
    Examples: 
      | RowNo |
      |     1 |
      
   @TC6A @FolderF1 @ModuleM1 @SheetWebListItem @Web @SelectDropdown
  Scenario Outline: Web: Select Dropdown ListItem using Attribute
    Given user <RowNo> launches the "Web" application
    When user clicks "btnMakeAppointment" in "CuraHomePage" page
    And user set text "excel" value "WebUsername" on field "txtUsername" in "Login" page
    And user set text "excel" value "WebPassword" on field "txtPassword" in "Login" page	   
    And user clicks "btnLogin" in "Login" page
    And user clicks "listFacility" in "CuraAppointment" page                  
    And user selects list item "option" with attr "value" - "FacilityListItem"
    And user clicks "chkMedicaid" in "CuraAppointment" page
    Then user logs out
    
    Examples: 
      | RowNo |
      |     1 |
      
  @TC7 @FolderF1 @ModuleM1 @SheetWebListItem @Web @Assertion @Navigation
  Scenario Outline: Web: Navigation and Assertion
    Given user <RowNo> launches the "Web" application
    When user clicks "btnMakeAppointment" in "CuraHomePage" page
    And user set text "excel" value "WebUsername" on field "txtUsername" in "Login" page
    And user set text "excel" value "WebPassword" on field "txtPassword" in "Login" page       		  
    And user clicks "btnLogin" in "Login" page
    Then user should see the "Appointment" page
    When user navigates to "Profile" page
    Then user should see the "Profile" page    
    And user logs out
    
    Examples: 
      | RowNo |
      |     1 |
      
  @TC8 @FolderF1 @ModuleM1 @SheetWebListItem @Web @GetText @Assertion
  Scenario Outline: Web: Get text using Object Repo
    Given user <RowNo> launches the "Web" application
    When user get text from "btnMakeAppointment" in "CuraHomePage" page and store as "MakeAppointmentLabel"
    Then user verifies if "stored" value "MakeAppointmentLabel" matches "feature" value "Make Appointment"
    And user logs out
    
    Examples: 
      | RowNo |
      |     1 |
      
  @TC9 @FolderF1 @ModuleM1 @SheetWebListItem @Web @GetText @Assertion
  Scenario Outline: Web: Get text using attribute 'value' in Object Repo
    Given user <RowNo> launches the "Web" application
    When user clicks "btnMakeAppointment" in "CuraHomePage" page
    And user get text attr value from "txtDemoUsername" in "Login" page and store as "DemoUsername"
    And user get text attr value from "txtDemoPassword" in "Login" page and store as "DemoPasword"
    Then user verifies if "stored" value "DemoUsername" matches "excel" value "WebUsername"
    And user verifies if "stored" value "DemoPasword" matches "excel" value "WebPassword"
    And user verifies if "btnLogin" in "Login" page is present
    And user verifies if "btnLogin" in "Login" page is visible
    When user clicks "btnLogin" in "Login" page
    Then user logs out
    
    Examples: 
      | RowNo |
      |     1 | 
      
  @TC10 @FolderF1 @ModuleM1 @SheetWebListItem @Web @GetText @Assertion
  Scenario Outline: Web: Get text using dynamic values - element with attribute
    Given user <RowNo> launches the "Web" application    
    When user get text from element "a" with attr "id" - "btn-make-appointment" and store as "MakeAppointmentLabel"  
    Then user verifies if element "a" with attr "id" value "btn-make-appointment" is present
    Then user verifies if element "a" with attr "id" value "btn-make-appointment" is visible
    And user logs out
    
    Examples: 
      | RowNo |
      |     1 |         
            