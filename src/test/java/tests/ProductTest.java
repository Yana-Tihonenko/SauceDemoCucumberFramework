package tests;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.CartPage;
import org.example.pages.HeaderPage;
import org.example.pages.ProductPage;
import org.example.utils.DriverManager;

import static org.testng.Assert.*;

public class ProductTest {

  ProductPage productPage = new ProductPage(DriverManager.getDriver());
  CartPage cartPage = new CartPage(DriverManager.getDriver());
  HeaderPage headerPage = new HeaderPage(DriverManager.getDriver());

  @When("the user selects {string} from the filter dropdown")
  public void theUserSelectsFromTheFilterDropdown(String sortedOpting) {
    productPage.sortProductsCarsListBy(sortedOpting);
  }

  @Then("the products are displayed sorted by the selected option {string}")
  public void theProductsAreDisplayedSortedByTheSelectedOption(String sortedOption) {
    productPage.verifySortProducts(sortedOption);
  }

  @When("the user adds {string} to the cart")
  public void theUserAddsProductToTheCart(String productName) {
    productPage.addItemToCart(productName);
  }

  @Then("{string} is displayed in the cart")
  public void productIsDisplayedInTheCart(String productName) {
    productPage.navigateToPage("cart");
    assertNotNull(cartPage.getItemByName(productName), productName + " is not displayed in the cart!");
  }

  @And("the cart counter displays {string}")
  public void theCartCounterDisplays(String expectedCount) {
    String actualCount = headerPage.getCartQuantity(true);
    assertEquals(actualCount, expectedCount, "Cart counter mismatch!");
  }

  @And("the user added product to the cart")
  public void theUserAddedProductToTheCart() {
    productPage.addItemToCart();
  }

  @When("the user removes the product in the cart")
  public void theUserRemovesTheProductInTheCart() {
    productPage.navigateToPage("cart");
    cartPage.removeItemsFromCart();
  }

  @Then("the product is no longer displayed in the cart")
  public void theProductIsNoLongerDisplayedInTheCart() {
    assertTrue(cartPage.isProductCartEmpty());
  }

}

