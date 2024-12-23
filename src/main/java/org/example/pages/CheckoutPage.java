package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CheckoutPage extends BasePage {

  private final By firstNameField = By.id("first-name");
  private final By lastNameField = By.id("last-name");
  private final By postalCodeField = By.id("postal-code");
  private final By continueButton = By.id("continue");
  private final By completeHeader = By.className("complete-header");
  private final By finishButton = By.id("finish");
  private final By cancelButton = By.id("cancel");

  public CheckoutPage(WebDriver driver) {
    super(driver);
  }


  public void enterFirstName(String firstName) {
    enterText(firstNameField, firstName);
  }

  public void enterLastName(String lastName) {
    enterText(lastNameField, lastName);
  }

  public void enterPostalCode(String postalCode) {
    enterText(postalCodeField, postalCode);
  }

  public void clickCancelButton() {
    click(cancelButton);
  }

  public void clickContinueButton() {
    click(continueButton);
  }

  public void inputClientInformation(String firstName, String lastName, String postalCode) {
    enterFirstName(firstName);
    logger.debug("Input First Name: {}", firstName);
    enterLastName(lastName);
    logger.debug("Input Last Name: {}", lastName);
    enterPostalCode(postalCode);
    logger.debug("Input Postal Code: {}", postalCode);

  }

  public void clickFinishButton() {
    click(finishButton);
  }

  public String getTextCompleteHeader() {
    WebElement completeHeaderText = findElement(completeHeader);
    logger.debug("Fetching text from 'Complete Header'.");
    return completeHeaderText.getText();
  }


}