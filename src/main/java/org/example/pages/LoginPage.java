package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertEquals;


public class LoginPage extends BasePage {
  private final By usernameField = By.id("user-name");
  private final By passwordField = By.id("password");
  private final By loginButton = By.name("login-button");
  private final By errorMessage = By.cssSelector("[data-test='error']");

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  public void enterUserName(String userName) {
    enterText(usernameField, userName);
  }

  public void enterPassword(String password) {
    enterText(passwordField, password);
  }

  public void clickLoginButton() {
    click(loginButton);
  }

  public void loginWithValidCredentials() {
    String username = getConfigValue("username");
    String password = getConfigValue("password");

    enterUserName(username);
    enterPassword(password);
    clickLoginButton();
  }

  public void loginUser(String userName, String password) {
    enterUserName(userName);
    enterPassword(password);
    clickLoginButton();
  }

  public String getErrorMessage() {
    WebElement errorElement = findElement(errorMessage);
    if (errorElement == null) {
      logger.error("Error message is not displayed.");
      return "";
    }
    String message = errorElement.getText();
    logger.info("Captured error message: {}", message);
    return message;
  }

  public void verifyMessageDisplayed(String errorMessage) {
    assertEquals(getErrorMessage(), errorMessage, "Message '" + errorMessage + "' is not displayed.");
  }
}


