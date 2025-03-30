Feature: Sample

  @TC1 @FolderF1 @ModuleM1 @SheetS1 @Combined
  Scenario Outline: API
    Given user send web service request "KeyCloak" in "Module"
    When user set access token jsonpath "access_token"
    When user send web service request "Inquire_Lead" with value "157888300" in "Module"
    When user verify request jsonPath "leadId" value "1578883"
    When user verify request jsonPath "leadStatus" value "100024 = Account Opened"
    When user verify request jsonPath "betterBankingNumber" value "52750290558"
    When user verify request jsonPath "eqBankingNumber" value "H03651"

    Examples: 
      | RowNo |
      |     1 |