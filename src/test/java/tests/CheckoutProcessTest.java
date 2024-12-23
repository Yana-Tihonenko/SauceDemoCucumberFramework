package tests;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.CartPage;
import org.example.pages.CheckoutPage;
import org.example.utils.DriverManager;

import static org.testng.Assert.assertEquals;

public class CheckoutProcessTest {

  CartPage cartPage = new CartPage(DriverManager.getDriver());
  CheckoutPage checkoutPage = new CheckoutPage(DriverManager.getDriver());

  @When("the user clicks Checkout button")
  public void theUserClicksCheckoutButton() {
    cartPage.clickCheckoutButton();
  }

  @And("enters shipping information: {string}, {string}, {string}")
  public void entersShippingInformation(String firstName, String lastName, String postalCode) {
    checkoutPage.inputClientInformation(firstName, lastName, postalCode);
  }

  @And("confirms the order")
  public void confirmsTheOrder() {
    checkoutPage.clickContinueButton();
    checkoutPage.clickFinishButton();
  }

  @Then("the user sees a confirmation message {string}")
  public void theUserSeesAConfirmationMessage(String message) {
    assertEquals(message, checkoutPage.getTextCompleteHeader());
  }

  @And("leaves the shipping information empty")
  public void leavesTheShippingInformationEmpty() {
    checkoutPage.clickContinueButton();
  }

  @And("clicks the Cancel button")
  public void clicksTheCancelButton() {
    checkoutPage.clickCancelButton();
  }
}
