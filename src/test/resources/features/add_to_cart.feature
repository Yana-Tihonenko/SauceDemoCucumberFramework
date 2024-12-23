Feature: Add to Cart
  # This feature tests adding and removing products to/from the cart

  Scenario Outline: Add product to cart
    Given the user is logged in
    When the user adds "<product_name>" to the cart
    Then "<product_name>" is displayed in the cart
    Examples:
      | product_name            |
      | Sauce Labs Backpack     |
      | Sauce Labs Bolt T-Shirt |

  Scenario:Remove product from cart
    Given the user is logged in
    And the user added product to the cart
    When the user removes the product in the cart
    Then the product is no longer displayed in the cart

