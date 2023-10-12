@OAQA-4827
Feature: Account Creation

  Background: User with create account rights is using the system and is on Cash Account screen
    Given create actor logged in to the system
    And navigate to Cash Account screen

# A create user should be able to create an account and an admin user should then be able to authorize the created account
  @OAQA-4852 @OAQA-4851
  Scenario Outline: Users should be able to create and authorise account
    When user create cash account with "<currency>" using client "<customerID>" as "<customerRole>"
    Then account arrangement should be created
    When admin user authorize the record
    Then account status should be "Authorised"

    Examples: Cash currency list
      | currency | customerID | customerRole |
      | GBP      | 203866     | Applicant    |
      | USD      | 191615     | Applicant    |


