Feature: User Authentication
  # This feature tests the login functionality  of the site
  Scenario: Successful login
    Given the user is on the "login" page
    When the user enters credentials: user name "standard_user", password "secret_sauce"
    Then the user is redirected to the "Products" page
    And the user sees "6" products of the product page

  Scenario:  Unsuccessful login with invalid password
    Given the user is on the "login" page
    When the user enters credentials: user name "locked_out_user", password "secret"
    Then an error message "Epic sadface: Username and password do not match any user in this service" is displayed

  Scenario: Login with a blocked user
    Given the user is on the "login" page
    When the user enters credentials: user name "locked_out_user", password "secret_sauce"
    Then an error message "Epic sadface: Sorry, this user has been locked out." is displayed