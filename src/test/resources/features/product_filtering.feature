Feature: Product filtering
  #This feature tests filtering options for product
  Background:
    Given the user is logged in

  Scenario: Sorting product by price (low to high)
    When the user selects "Price (low to high)" from the filter dropdown
    Then the products are displayed sorted by the selected option "Price (low to high)"

  Scenario: Sorting product by name (Z to A)
    When the user selects "Name (Z to A)" from the filter dropdown
    Then the products are displayed sorted by the selected option "Name (Z to A)"

