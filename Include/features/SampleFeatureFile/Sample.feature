Feature: Sample

  @TC1 @FolderF1 @ModuleM1 @SheetS1 @Combined
  Scenario Outline: Web to Desktop testing
    Given user <RowNo> launches the "Web" application
    When user clicks "Button" "Make Appointment"
    #And user enters "WebUsername" and "WebPassword"
    And user enters "WebUsername" on field "Username" in "Login" page	
    And user enters "WebPassword" on field "Password" in "Login" page
    And user clicks "button" "Login" in "Login" page
    And user clicks "btnLogin" in "Login" page
    Then user logs out
    And user switches to "Desktop" app
    Given user <RowNo> launches the "Desktop" application
    When user clicks "TreeItem" "Playlists"
    And user clicks "SplitButton" "Create playlist"
    And user inputs value on text field "PlaylistName"
    Then user logs out

    Examples: 
      | RowNo |
      |     1 |

  @TC2 @FolderF1 @ModuleM1 @SheetS1 @Web
  Scenario Outline: Web Login
    Given user <RowNo> launches the "Web" application
    When user clicks "Button" "Make Appointment"
    #And user enters "WebUsername" and "WebPassword"
    And user enters "WebUsername" on field "Username" in "Login" page	
    And user enters "WebPassword" on field "Password" in "Login" page
    And user clicks "button" "Login" in "Login" page
    Then user logs out

    Examples: 
      | RowNo |
      |     2 |

  @TC3 @FolderF1 @ModuleM1 @SheetS1 @Web
  Scenario Outline: Web Login
    Given user <RowNo> launches the "Desktop" application
    When user clicks "TreeItem" "Playlists"
    And user clicks "SplitButton" "Create playlist"
    And user inputs value on text field "PlaylistName"
    Then user logs out

    Examples: 
      | RowNo |
      |     3 |
