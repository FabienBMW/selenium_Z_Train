Feature:As a user, I want to sign up so that I can shop

  @TEST_OF-1025
  Scenario:
    Given User is on the register page
    And The user fill "sehajpreet.nicholi@orperfect.com", "hqqps2J$" fields
    And the user leaves the "confirmation password" field blank
    When The user click on Inscription button
    Then user receives error message due to empty confirm password field