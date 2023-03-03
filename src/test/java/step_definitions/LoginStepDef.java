package step_definitions;

import dataProviders.ConfigFileReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObjects.LoginPage;
import pageObjects.ToolBarComponent;
import utils.Log;

import java.time.Duration;


public class LoginStepDef {
    WebDriver driver;

    ConfigFileReader configs;

    ToolBarComponent toolBar;
    LoginPage login;

    Duration TIMEOUT = Duration.ofSeconds(30L);
    public LoginStepDef(Hooks hooks){
        driver = hooks.dm.getDriver();
        configs = hooks.CONFIGURATIONS;
        toolBar = new ToolBarComponent(driver,30L);
        login = new LoginPage(driver,30L);
    }
    @When("Providing a valid {string} and {string}")
    public void providingAValidaUsernameAndPassword(String username, String password) {


        driver.navigate().to(configs.getUrl()+"/stats");

        toolBar.clickElement(toolBar.signIn_icon);
        toolBar.clickElement(toolBar.signInWithId_opt);

        login.enterText(login.email_txt,username);
        login.enterText(login.password_txt,password);

        login.clickElement(login.submit_btn);

    }

    @Then("User features are visible")
    public void userFeaturesAreVisible() {

        Log.debug("Looking for sign out option");

        toolBar.waitUntilInvisibilityOf(toolBar.signIn_icon);

        toolBar.waitUntilIsVisible(driver.findElement(toolBar.userNavControls_menu).findElement(By.xpath(".//*[local-name()='svg']")));

        Assert.assertTrue(driver.findElement(toolBar.userNavControls_menu).findElement(By.xpath(".//*[local-name()='svg']")).isDisplayed());

        Log.debug("Sign out option is present");
    }
}
