# SauceDemo Test Automation Project

## Overview

This project leverages Cucumber to implement Behavior-Driven Development (BDD) practices for the SauceDemo platform,
ensuring a smooth user experience through comprehensive testing of essential functionalities.

## Features

- Automated tests include:

    - User login functionality

    - Adding/removing products from the cart

    - Checkout workflow validation

## Tech Stack

- **Language**: Java

- **Framework**: TestNG

- **Tools**: Selenium WebDriver, Cucumber (for BDD scenarios)

- **Build System**: Maven

- **Logging**: Log4j2

- **Assertions**: TestNG

- **Reports**: Allure

## Project Structure

- **models:** Contains data classes (ProductCard, Item) modeling application entities.

- **pages:** Implements Page Object Model (POM) for separation of test logic from UI interactions.

- **utils:** Helper classes for WebDriver management and common test utilities.

- **tests:** Houses TestNG test classes covering core e-commerce functionality.

- **resources/features:** BDD feature files (Cucumber) defining test scenarios.

- **config.properties:** Configuration for URLs and credentials.



