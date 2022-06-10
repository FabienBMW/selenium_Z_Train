Feature: As a customer I want to login so that I can shop

  @TEST_OF-808
    Scenario Outline: Verify successful login with credentials
      Given User is on the login page
      When User is logging-in with "<email>" "<password>"
      Then User should be redirected to the home page

      Examples:
        |email             | password|
        |237pk69@gmail.com | P@wk/*69|

  @TEST_OF-807
  Scenario: Login and password field requiered
    Given User is on the login page
    When User click on connexion button
    Then User should see and error message below the login field

  @TEST_OF-806
  Scenario Outline: Password displayed in clear text when logging in
    Given User is on the login page
    When User fill in "<password>" field and click on displayed password button
    Then The password is displayed in clear
    Examples:
      |password|
      |P@wk/*69|

#  @TEST_OF-799
#  Scenario: Access to the registration page
#    Given User is on the login page
#    When User clicks on the resgister link
#    Then User should be directed to the registration page

  @TEST_OF-810
  Scenario Outline: Verify password reset
    Given User is on the login page
    When User clicks on forgot password link
    And User fill in "<email>" and "<newPassword>" and validate
    Then User should be redirected on login page
    Examples:
      | email             |newPassword |
      | 237pk69@gmail.com |P@wk/*69    |

  @TEST_OF-829
  Scenario Outline: Verify successful login with credentials
    Given User is on the login page
    When User is logging-in with "<email>" "<password>"
    Then The products page should display

    Examples:
      |email             | password|
      |237pk69@gmail.com | P@wk/*69|

#  @TEST_OF-1016
#  Scenario Outline:
#    Given User is on the login page
#    When The user click on <social_network> icon
#    Then The user is redirected to a <social_network>
#    But No change on the page
#    Examples:
#      |social_network|
#      | google       |