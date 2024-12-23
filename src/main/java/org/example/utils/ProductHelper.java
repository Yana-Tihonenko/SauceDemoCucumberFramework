package org.example.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.models.Item;
import org.example.models.ProductCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static org.example.pages.BasePage.extractDigits;

public class ProductHelper {

  private static final By itemNameElement = By.className("inventory_item_name");
  private static final By itemDescriptionElement = By.className("inventory_item_desc");
  private static final By itemPriceElement = By.className("inventory_item_price");
  private static final By itemImageElement = By.tagName("img");
  private static final By addOrRemoveButtonElement = By.tagName("button");
  private static final Logger logger = LogManager.getLogger(ProductHelper.class);


  public static List<ProductCard> buildProductCards(List<WebElement> productElements, boolean withImage) {
    return productElements.stream()
        .map(element -> ProductHelper.buildProductCard(element, withImage))
        .collect(Collectors.toList());
  }

  public static ProductCard buildProductCard(WebElement productElement, boolean withImage) {
    try {
      String name = productElement.findElement(itemNameElement).getText();
      String description = productElement.findElement(itemDescriptionElement).getText();
      Double price = extractDigits(productElement.findElement(itemPriceElement).getText());

      Item itemDetails;
      WebElement button = productElement.findElement(addOrRemoveButtonElement);
      WebElement nameLink = productElement.findElement(itemNameElement);

      if (withImage) {
        String imageSrc = productElement.findElement(itemImageElement).getAttribute("src");
        itemDetails = new Item(name, description, price, imageSrc);
        WebElement imageLink = productElement.findElement(itemImageElement);
        return new ProductCard(itemDetails, nameLink, imageLink, button);
      } else {
        itemDetails = new Item(name, description, price);
        return new ProductCard(itemDetails, nameLink, button);
      }
    } catch (Exception e) {
      logger.error("Failed to build ProductCard. Skipping.", e);
      return null;
    }
  }

  public static void addRandomItemToCart(List<ProductCard> productCards) {
    if (productCards == null || productCards.isEmpty()) {
      throw new IllegalStateException("Product list is empty or null");
    }
    int randomIndex = ThreadLocalRandom.current().nextInt(0, productCards.size());
    ProductCard randomItem = productCards.get(randomIndex);
    randomItem.clickAddOrRemoveButton();
    logger.info("Random item '{}' added to cart.", randomItem.getItemDetails().getName());
  }

  public static void addItemToCartByName(String productName, List<ProductCard> productCards) {
    ProductCard productCard = productCards.stream()
        .filter(product -> product.getItemDetails().getName().equals(productName))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Product with name " + productName + " not found."));

    if (productCard.getActualNameButton().equals("Add to cart")) {
      productCard.clickAddOrRemoveButton();
      logger.info("Product '{}' added to cart.", productName);
    } else {
      logger.info("Product '{}' is already in the cart.", productName);
    }
  }

}
