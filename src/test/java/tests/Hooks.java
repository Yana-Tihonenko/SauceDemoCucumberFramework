package tests;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.utils.DriverManager;

public class Hooks {

  @Before
  public void setUp() {
    DriverManager.getDriver();
  }

  @After
  public void tearDown() {
    DriverManager.quitDriver();
  }
}
