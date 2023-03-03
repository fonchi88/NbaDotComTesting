package pageObjects;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ToolBarComponent extends BasePage {

    public ToolBarComponent(WebDriver driver, Long timeout){
        super(driver,timeout);
    }
    public By signIn_icon = By.xpath("//span[contains(text(),'Sign In')]/parent::button");

    public By signInWithId_opt = By.xpath("//a[@data-id='nba:navigation:nba-sign-in:link']");

    public By logout_btn = By.xpath("//button[contains(text(),' Sign Out')]");

    public By userNavControls_menu = By.id("nav-controls");
}
