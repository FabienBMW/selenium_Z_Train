Feature: As a customer, I am loggin and I want to shop

  Background:
    Given User is on the homepage
      | email                             | password |
      | sehajpreet2.nicholi@orperfect.com | hqqps2J$ |


  @TEST_OF-830
  Scenario: Display product sheet
    When User clicks on a product
    Then The product sheet should appear

  @TEST_OF-831
  Scenario Outline: Add product to cart from product page
    When user selects "<product>"
    And he clicks on add to cart
    Then he observes a pop pop which indicates the following message <message>

    Examples:
      | product                        | message                       |
      | JVC, Caque Supra-aural Pliable | Votre panier à été mis à jour |

  @TEST_OF-902
  Scenario: Empty cart
    When user selects "Enceinte Bluetooth portable robuste"
    And he clicks on add to cart
    And user clicks on cart icon
    And User clicks on trash cart button
    And user clicks on cart icon
    Then The cart should be empty


  @TEST_OF-839
  Scenario: Delete product to card
    When user selects "Enceinte Bluetooth portable robuste"
    And he clicks on add to cart
    And user clicks on cart icon
    And User clicks on delete icon of product
    Then The product is not already visible in the card

  @TEST_OF-840
  Scenario: Log out of account
    When User move to account icon and clicks on logout
    Then User should be redirected on login page

  @TEST_OF-901
  Scenario: Test the oder button
    When user selects "Enceinte Bluetooth portable robuste"
    And he clicks on add to cart
    And user clicks on cart icon
    And User clicks on oder button
    Then The oder validation pop-up is displaying