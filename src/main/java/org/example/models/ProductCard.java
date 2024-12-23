package org.example.models;

import org.openqa.selenium.WebElement;

import java.util.Objects;

public class ProductCard {
  private final Item itemDetails;
  private final WebElement nameLink;
  private WebElement imageLink;
  private WebElement addOrRemoveButton;


  public ProductCard(Item itemDetails, WebElement nameLink, WebElement addOrRemoveButton) {
    this.itemDetails = itemDetails;
    this.nameLink = nameLink;
    this.addOrRemoveButton = addOrRemoveButton;
  }

  public ProductCard(Item itemDetails, WebElement nameLink, WebElement imageLink, WebElement addOrRemoveButton) {
    this.itemDetails = itemDetails;
    this.nameLink = nameLink;
    this.imageLink = imageLink;
    this.addOrRemoveButton = addOrRemoveButton;
  }

  public Item getItemDetails() {
    return itemDetails;
  }


  public void clickAddOrRemoveButton() {
    addOrRemoveButton.click();
  }

  public String getActualNameButton() {
    return addOrRemoveButton.getText();
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ProductCard that)) return false;
    return Objects.equals(itemDetails, that.itemDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemDetails, addOrRemoveButton);
  }

  @Override
  public String toString() {
    return String.format("Name: %s, Description: %s, Price: %.2f, ImageSrc: %s, Button: %s",
        itemDetails.getName(), itemDetails.getDescription(), itemDetails.getPrice(), itemDetails.getImageSrc(), getActualNameButton());
  }
}
