package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver, Long timeout){
        super(driver,timeout);
    }

    public By email_txt = By.id("email");

    public By password_txt = By.id("password");

    public By submit_btn = By.id("submit");
}
