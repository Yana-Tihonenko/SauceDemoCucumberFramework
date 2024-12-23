package tests;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.LoginPage;
import org.example.pages.ProductPage;
import org.example.utils.DriverManager;

import static org.testng.Assert.assertEquals;


public class LoginTest {

  LoginPage loginPage = new LoginPage(DriverManager.getDriver());
  ProductPage productPage = new ProductPage(DriverManager.getDriver());

  @Given("the user is on the {string} page")
  public void the_user_is_on_the_page(String pageName) {
    loginPage.navigateToPage(pageName);
  }

  @Then("the user is redirected to the {string} page")
  public void the_user_is_redirected_to_the_page(String namePage) {
    productPage.verifyPageDisplayed(namePage);
  }

  @Then("an error message {string} is displayed")
  public void an_error_message_is_displayed(String errMassage) {
    loginPage.verifyMessageDisplayed(errMassage);
  }

  @And("the user sees {string} products of the product page")
  public void theUserSeesProductsOfTheProductPage(String countProductOfPage) {
    int expectedCount = Integer.parseInt(countProductOfPage.trim());
    assertEquals(productPage.getNumberOfProducts(), expectedCount);
  }

  @When("the user enters credentials: user name {string}, password {string}")
  public void theUserEntersCredentialsUserNamePassword(String userName, String password) {
    loginPage.loginUser(userName, password);
  }

  @Given("the user is logged in")
  public void theUserIsLoggedIn() {
    loginPage.navigateToPage("login");
    loginPage.loginWithValidCredentials();
  }

}
