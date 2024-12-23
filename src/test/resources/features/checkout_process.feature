Feature: Checkout Process
  # This feature tests the checkout process from adding items to completing the order.

  Background:
    Given the user is logged in
    And the user added product to the cart
    And the user is on the "cart" page
    And the user clicks Checkout button

  Scenario: Checkout with valid information
    When enters shipping information: "John Doe", "123 Main St", "12345"
    And confirms the order
    Then the user sees a confirmation message "Thank you for your order!"

  Scenario: Checkout with missing information
    When leaves the shipping information empty
    Then an error message "Error: First Name is required" is displayed

  Scenario: The user cancels the checkout process
    When clicks the Cancel button
    Then  the user is redirected to the "cart" page
