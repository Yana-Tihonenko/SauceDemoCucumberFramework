package tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "tests",
    plugin = {
        "pretty",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",  // Генерация allure-results
        "html:target/cucumber-html-report",
        "json:target/cucumber.json"
    }
)

public class TestRunner extends AbstractTestNGCucumberTests {
    static {
        System.setProperty("allure.results.directory", "target/allure-results");
    }
}
