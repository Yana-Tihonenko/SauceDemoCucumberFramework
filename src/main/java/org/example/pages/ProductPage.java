package org.example.pages;

import org.example.models.ProductCard;
import org.example.utils.ProductHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class ProductPage extends BasePage {

  private final By productItemElements = By.className("inventory_item");
  private final By SortDropdownElement = By.className("product_sort_container");

  public ProductPage(WebDriver driver) {
    super(driver);
  }

  public List<ProductCard> getListProductCard() {
    List<WebElement> productElements = findElements(productItemElements);
    if (productElements == null || productElements.isEmpty()) {
      throw new IllegalStateException("Product list is null or empty");
    }
    logger.debug("Fetched {} product items from the page.", productElements.size());
    return ProductHelper.buildProductCards(productElements, true);
  }

  public int getNumberOfProducts() {
    List<ProductCard> productCards = getListProductCard();
    return productCards.size();
  }

  public WebElement getSortDropdown() {
    logger.debug("Fetching the sort dropdown element.");
    return driver.findElement(SortDropdownElement);
  }

  public void sortProductsCarsListBy(String sortName) {
    if (sortName == null || sortName.isEmpty()) {
      throw new IllegalStateException("Sort option is null or empty");
    }
    logger.info("Sorting product list by criteria: {}", sortName);
    Select listOfSortProduct = new Select(getSortDropdown());
    listOfSortProduct.selectByVisibleText(sortName);
  }

  public void addItemToCart() {
    ProductHelper.addRandomItemToCart(getListProductCard());
  }

  public void addItemToCart(String productName) {
    ProductHelper.addItemToCartByName(productName, getListProductCard());
  }

  public void verifySortProducts(String sortedOption) {
    List<?> actualSortProduct;
    List<?> expectedSortProduct;

    switch (sortedOption) {
      case "Price (high to low)" -> {
        actualSortProduct = getPriceListOfProductCards();
        expectedSortProduct = ((List<Double>) actualSortProduct).stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
      }
      case "Price (low to high)" -> {
        actualSortProduct = getPriceListOfProductCards();
        expectedSortProduct = ((List<Double>) actualSortProduct).stream()
            .sorted(Comparator.naturalOrder())
            .collect(Collectors.toList());
      }
      case "Name (A to Z)" -> {
        actualSortProduct = getNameListOfProductCards();
        expectedSortProduct = ((List<String>) actualSortProduct).stream()
            .sorted(Comparator.naturalOrder())
            .collect(Collectors.toList());
      }
      case "Name (Z to A)" -> {
        actualSortProduct = getNameListOfProductCards();
        expectedSortProduct = ((List<String>) actualSortProduct).stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
      }
      default -> throw new IllegalArgumentException("Invalid sort option: " + sortedOption);
    }
    assertEquals(actualSortProduct, expectedSortProduct, sortedOption + " sorting failed.");
    logger.info("Sorting by '{}' is verified successfully.", sortedOption);
  }

  private List<Double> getPriceListOfProductCards() {
    return getListProductCard()
        .stream()
        .map(productCard -> productCard.getItemDetails().getPrice())
        .collect(Collectors.toList());
  }

  private List<String> getNameListOfProductCards() {
    return getListProductCard()
        .stream()
        .map(productCard -> productCard.getItemDetails().getName())
        .collect(Collectors.toList());
  }
}
