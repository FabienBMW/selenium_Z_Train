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
  Scenario: Test the order button
    When user selects "Enceinte Bluetooth portable robuste"
    And he clicks on add to cart
    And user clicks on cart icon
    And User clicks on order button
    Then The order validation pop-up is displaying

  @TEST_OF-833
  Scenario: Increasing the quantity of a product
    When user selects "I-phone PRO 256 NOIR"
    And he clicks on add to cart
    And he sees the notification
    When user clicks on cart icon
    And click on the + button to increase the quantity of this product in the cart "I-phone PRO 256 NOIR"
    Then we observe in the list that the basket is modified, the quantity of the product has increased

  @TEST_OF-837
  Scenario Outline: Increasing the quantity of a product from the add product
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
      | article            | precise_price |
      | Yucca Elephantipes | 9.99          |

  @TEST_OF-900
  Scenario Outline: check total cart price
    When User clicks on the basket icon of the products "T-shirt en coton biologique"
    And he observes a pop pop which indicates the following message <message>
    And User clicks on the basket icon of the products "Ampoule Vecteur Incandescent"
    And he observes a pop pop which indicates the following message <message>
    And User clicks on the basket icon of the products "Chaussures Hommes de Ville"
    And he observes a pop pop which indicates the following message <message>
    And user clicks on cart icon
    Then User should see the sum total of the prices of the items in the cart

    Examples:
      | message                       |
      | Votre panier à été mis à jour |

  @TEST_OF-904
  Scenario Outline: Check the possibility of manually entering the desired quantity of products
    Given user knows the quantity of "<product>" in cart
    When user selects "<product>"
    And user enters "<product>" quantity "<quantity>"
    And he clicks on add to cart
    Then he observes a pop pop which indicates the following message <message>

    Examples:
      | product            | message                       | quantity |
      | Yucca Elephantipes | Votre panier à été mis à jour | 8        |

  @TEST_OF-832
  Scenario Outline: Decrement quantity of a product
    Given user knows the quantity of "<product>" in cart
    When user selects "<product>"
    And user enters "<product>" quantity "<quantity>"
    And he clicks on add to cart
    Then he observes a pop pop which indicates the following message <message>
    When user clicks on cart icon
    And user clicks "<number>" times on the (-) button to reduce the quantity of "<product>"
#    Then user observes that the basket has not been updated "<product>"
    Then we observe in the list that the basket is modified the "<product>" has been deleted or the "<number>" decreases

    Examples:
      | product                                 | message                       | number | quantity |
      | Yucca Elephantipes                      | Votre panier à été mis à jour | 3      | 8        |
      | Fauteuil Chaise Patchwork, Bois d'hévéa | Votre panier à été mis à jour | 1      | 1        |

  @TEST_OF-903
  Scenario Outline: Test fields required for payment
    When user selects "<product>"
    And he clicks on add to cart
    And user clicks on cart icon
    And User clicks on order button
    And User sends the order form
    Then A required fields error message should appear under the fields

    Examples:
      | product                    |
      | Chaussures Hommes de Ville |

  @TEST_OF-906
  Scenario Outline: Payment validation
    When user selects "<product>"
    And he clicks on add to cart
    And user clicks on cart icon
    And User clicks on order button
    And User fills fields with "<card number>", "<expired date>", "<cvc>", <adresse de livraison> and <methode_livraison>
    And User sends the order form
    Then A validation confirmation message should appear on the screen

    Examples:
      | product                      | card number         | expired date | cvc | adresse de livraison | methode_livraison |
      | Chaise de Bureau Ergonomique | 4242 4242 4242 4242 | 12 / 23      | 520 | 204 Rue Manga-bell   | 1                 |

  @TEST_OF-1166
  Scenario Outline: Affichage des categories de produit
    When User clicks on 'Toutes les categories'
    Then The user should see the product categories displayed "<category_1>" et "<category_2>"

    Examples:
      | category_1 | category_2   |
      | mode homme | electronique |


  @TEST_OF-1167
  Scenario Outline: Affichage des sous categories de produit
    When User clicks on 'Toutes les categories'
    And The user selects category "<category>"
    Then The user must see at least one sub-category displayed "<sub_category>"

    Examples:
      | category   | sub_category |
      | Mode homme | chaussure    |

  @TEST_OF-1168
  Scenario Outline: Affichage des categories de produit
    When User clicks on 'Toutes les categories'
    Then The user should see the product category displayed "<category>"

    Examples:
      | category |
      | other    |

  @TEST_OF-1169
  Scenario Outline: Affichage des attributs de la page des produits
    When user selects "<product>"
    Then The user must see the product sheet displayed
    And User must see attributes "<attribute_1>" et "<attribute_2>"

    Examples:
      | product                      | attribute_1 | attribute_2 |
      | Ampoule Vecteur Incandescent | taille      | couleur     |

  @TES_OF-1181
  Scenario Outline:
    When user selects "<product>"
    Then The user must see the product sheet displayed
    And The user sees the different captures of the product displayed

    Examples:
      |product                      |
      | Chaussures Hommes de Ville  |


  @TEST_OF-1200
  Scenario Outline: Affichage d'un message d'erreur à l'insertion d'un code promo erroné
    When user selects "<product>"
    When User clicks on promo button
    And User enters promo "<code>"
    Then The price of my item is recalculated based on the discount of <remise>%
    Examples:
      | code   | remise | product                      |
#      | SRDRAV | 10     | Chaise de Bureau Ergonomique |
      |FDHSTI | 2    | Chaise de Bureau Ergonomique |
