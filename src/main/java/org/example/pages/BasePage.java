package org.example.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Properties;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class BasePage {
  protected WebDriver driver;
  private final int DEFAULT_TIMEOUT = 3;
  protected final Logger logger = LogManager.getLogger(getClass());
  private final Properties properties = new Properties();


  public BasePage(WebDriver driver) {
    this.driver = driver;
    loadProperties();
  }

  private void loadProperties() {
    try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
      if (input == null) {
        logger.error("config.properties is not found");
        return;
      }
      properties.load(input);
    } catch (IOException ex) {
      logger.error("Error loading properties file", ex);
    }
  }

  protected WebElement findElement(By locator) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
      return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    } catch (Exception e) {
      throw new IllegalStateException("Element is not found. Locator: " + locator);
    }
  }

  protected List<WebElement> findElements(By locator) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
      return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    } catch (Exception e) {
      logger.error("Elements not found. Locator: {}", locator);
      return Collections.emptyList();
    }
  }

  public static Double extractDigits(String str) {
    return Double.parseDouble(str.replaceAll("[^0-9.]", ""));
  }

  protected String getConfigValue(String key) {
    String value = properties.getProperty(key);
    if (value == null) {
      logger.error("Config value for key '{}' is not found.", key);
      throw new IllegalStateException("Missing config value for key: " + key);
    }
    return value;
  }

  public void navigateToPage(String pageName) {
    if (driver == null) {
      throw new IllegalStateException("Driver is not initialized!");
    }
    String url = getConfigValue(pageName.toLowerCase());
    logger.info("Navigating to {} page.", pageName);
    driver.get(url);
  }

  protected void enterText(By locator, String text) {
    WebElement element = findElement(locator);
    logger.info("Entering text '{}' into field with locator '{}'.", text, locator);
    element.clear();
    element.sendKeys(text);
  }

  public void verifyPageDisplayed(String pageName) {
    String currentUrl = driver.getCurrentUrl();
    switch (pageName.toLowerCase()) {
      case "products" -> assertTrue(currentUrl.contains("inventory.html"), "Products page is not displayed.");
      case "cart" -> assertTrue(currentUrl.contains("cart.html"), "Cart page is not displayed.");
      case "order confirmation" ->
          assertTrue(currentUrl.contains("checkout-complete.html"), "Order confirmation page is not displayed.");
      default -> throw new IllegalArgumentException("Unknown page: " + pageName);
    }
  }

  protected void click(By locator) {
    WebElement element = findElement(locator);
    logger.info("Clicking element with locator '{}'.", locator);
    element.click();
  }

}
