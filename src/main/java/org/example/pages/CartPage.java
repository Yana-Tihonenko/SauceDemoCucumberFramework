package org.example.pages;

import io.qameta.allure.Step;
import org.example.models.ProductCard;
import org.example.utils.ProductHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.Collections;
import java.util.List;


public class CartPage extends BasePage {
  private final By listItemElements = By.className("cart_item");
  private final By checkoutButtonElement = By.id("checkout");

  public CartPage(WebDriver driver) {
    super(driver);
  }

  @Step("Retrieve list of products from the cart")
  public List<ProductCard> getListProductCard() {
    List<WebElement> listItemsElements = findElements(listItemElements);
    if (listItemsElements.isEmpty()) {
      logger.info("No products found in the cart.");
      return Collections.emptyList();
    }
    logger.debug("Fetched {} product items from the page.", listItemsElements.size());
    return ProductHelper.buildProductCards(listItemsElements, false);
  }
  @Step("Click the 'Checkout' button")
  public void clickCheckoutButton() {
    click(checkoutButtonElement);
  }
  @Step("Check if the cart is empty")
  public boolean isProductCartEmpty() {
    return getListProductCard().isEmpty();
  }
  @Step("Remove all products from the cart")
  public void removeItemsFromCart() {
    getListProductCard().forEach(ProductCard::clickAddOrRemoveButton);
  }
  @Step("Get product by name: {productName}")
  public ProductCard getItemByName(String productName) {
    return getListProductCard().stream()
        .filter(product -> product.getItemDetails().getName().equals(productName))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException(
            "Product with name '" + productName + "' not found in the cart."));
  }
}
