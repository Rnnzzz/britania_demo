@OAQA-4827
Feature: Account Creation

  Background: User with create account rights is using the system and is on Cash Account screen
    Given create actor logged in to the system
    And navigate to Cash Account screen

  @OAQA-4852 @OAQA-4851
  Scenario Outline: Users should be able to create and authorise account
    When user create new cash account "<currency>"
    Then account arrangement should be created
    When admin user authorize the record
    Then account status should be "Authorised"

    Examples: Currency list
      | currency |
#      | BSD      |
#      | AUD      |
#      | BRL      |
#      | CAD      |
#      | CHF      |
#      | CNY      |
#      | EUR      |
#      | GBP      |
#      | HKD      |
#      | JPY      |
#      | MXN      |
#      | NOK      |
#      | NZD      |
#      | SEK      |
#      | SGD      |
      | USD      |
#      | ZAR      |
#      | RUB      |
#      | TRY      |

#  Scenario: User creating an account using other currency
#    When user create new cash account "KRW"
#    Then user should see an error message: "Currency not allowed to create cash account"
#
#  Scenario: User creating an account without customer id and currency
#    When user create new cash account with no customerID and currency
#    Then user should see an error message: "Customer and CCY fields are mandatory"

