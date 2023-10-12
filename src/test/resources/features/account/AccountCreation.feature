@OAQA-4827
Feature: Account Creation

  Background: User with create account rights is using the system and is on Cash Account screen
    Given create actor logged in to the system
    And navigate to Cash Account screen

  Scenario: User create cash account and authorise by admin
    When user create cash account with "USD" using client "191615" as "Applicant"
    Then account arrangement should be created
    When admin user authorize the record
    Then account status should be "Authorised"

  @OAQA-4855 @OAQA-4851
  Scenario: User creating an account using other currency not in the list
    When user create cash account with "KRW" using client "191615" as "Applicant"
    Then user should see an error message: "MISSING CURRENCY - RECORD"

  @OAQA-4856 @OAQA-4851
  Scenario: User creating an account without customer id
    When user create cash account with " " using client " " as " "
    Then user should see an error message: "MANDATORY INPUT FOR NEW ARRANGEMENT"