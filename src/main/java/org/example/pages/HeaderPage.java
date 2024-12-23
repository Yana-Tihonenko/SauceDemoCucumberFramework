package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HeaderPage extends BasePage {

  private final By cartQuantityElement = By.className("shopping_cart_badge");

  public HeaderPage(WebDriver driver) {
    super(driver);
  }

  public String getCartQuantity(boolean isMandatory) {
    List<WebElement> cartQuantity = driver.findElements(cartQuantityElement);
    if (isMandatory && cartQuantity.isEmpty()) {
      logger.error("Cart quantity element is not found.");
      throw new IllegalStateException("Cart quantity element is missing but required.");
    }
    if (cartQuantity.isEmpty()) {
      return null;
    }
    return cartQuantity.get(0).getText();
  }

}
