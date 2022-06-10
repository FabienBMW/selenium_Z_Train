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
    When user selects "Smart Montre M6 Fitness Tracker Montre-bracelet"
    And he clicks on add to cart
    And user clicks on cart icon
    And User clicks on delete icon of "Smart Montre M6 Fitness Tracker Montre-bracelet"
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

  @TEST_OF-833
  Scenario:
    When user selects "I-phone PRO 256 NOIR"
    And he clicks on add to cart
    And he sees the notification
    When user clicks on cart icon
    And click on the + button to increase the quantity of this product in the cart "I-phone PRO 256 NOIR"
    Then we observe in the list that the basket is modified, the quantity of the product has increased

  @TEST_OF-837
  Scenario Outline:
    Given user knows the quantity of "<product>" in cart
    When user selects "<product>"
    And he fills in the quantity of the product to add with + icons
    And he clicks on add to cart
    Then he observes a pop pop which indicates the following message <message>
    And user selects "<product>"
    And he fills in the quantity of the product to add with + icons
    And he clicks on add to cart
    Then he observes a pop pop which indicates the following message <message>
    When user clicks on cart icon
    Then user observes that the basket has not been updated "<product>"

    Examples:
      | message                       | product                                         |
      | Votre panier à été mis à jour | Smart Montre M6 Fitness Tracker Montre-bracelet |


  @TEST_OF-1015
  Scenario Outline: Product information
    When user selects "<article>"
    Then Spawn a popup on the page with description of the <article> and the <precise_price>

    Examples:
      | article              | precise_price | email                             | password |
      | Yucca Elephantipes   | 9.99          | sehajpreet2.nicholi@orperfect.com | hqqps2J$ |
      | I-phone PRO 256 NOIR | 1259.02       | sehajpreet2.nicholi@orperfect.com | hqqps2J$ |
