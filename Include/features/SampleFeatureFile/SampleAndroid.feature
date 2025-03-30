Feature: Sample

  @TC1 @FolderF1 @ModuleM1 @SheetMobileInternalDataFileSample @Combined
  Scenario Outline: AOS
    Given user <RowNo> launches the "AOS" application
    When user clicks "btnAlreadyRegistered" in "Login" page
    And user set text "excel" value "Username" on field "txtUsername" in "Login" page
    And user set text "excel" value "Password" on field "txtPassword" in "Login" page
    And user clicks "btnLogin" in "Login" page

    Examples: 
      | RowNo |
      |     1 |