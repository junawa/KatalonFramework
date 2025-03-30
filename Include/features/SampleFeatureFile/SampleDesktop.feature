Feature: Sample Desktop

  @TC1 @FolderF1 @ModuleM1 @SheetDesktopData1 @Desktop
  Scenario Outline: Avaloq Login
    Given user <RowNo> launches the "DSK" application
    When user clicks element offset "Avaloq Username"
    And user set text "excel" value "AvaloqUsername" on current field
    And user press 1 "TAB"
    And user set text "excel" value "AvaloqPassword" on current field
    And user clicks object with name "Login"
    And user waits 3 seconds
    And user switches to Avaloq Application
    And user clicks object with name "Continue" 
    And user waits 2 seconds       
    Then user logs out

    Examples: 
      | RowNo |
      |     1 |

  @TC2 @FolderF1 @ModuleM1 @SheetDesktopData1 @Desktop
  Scenario Outline: Avaloq Login
    Given user <RowNo> launches the "DSK" application
    When user clicks element offset "Avaloq Username"
    And user set text "excel" value "AvaloqUsername" on current field
    And user press 1 "TAB"
    And user set text "excel" value "AvaloqPassword" on current field   
    And user clicks "btnLogin" in "Login" page
    And user waits 3 seconds
    And user switches to Avaloq Application
    And user clicks "btnContinue" in "Login" page
    And user waits 2 seconds
    Then user should see the "AvaloqHome" page
    And user clicks "tabItemClient" in "HomeScreen" page 
    And user clicks "tabItemCRM" in "HomeScreen" page
    And user clicks "tabItemMonetaryTrx" in "HomeScreen" page
    And user waits 2 seconds   
    Then user logs out

    Examples: 
      | RowNo |
      |     1 |
