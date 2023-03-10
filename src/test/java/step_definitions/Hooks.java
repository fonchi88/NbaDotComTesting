package step_definitions;

import dataProviders.ConfigFileReader;
import driver.Browser;
import driver.DriverManager;
import driver.IDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Log;

import java.io.File;

public class Hooks {

    public ConfigFileReader CONFIGURATIONS;

    IDriver dm;


    @Before
    public void BeforeSteps(){

        CONFIGURATIONS = new ConfigFileReader("nba_configuration_prod");

        dm = DriverManager.get(Browser.REMOTE);
        dm.setOptions("--no-sandbox","--disable-dev-shm-usage","--ignore-ssl-errors=yes","--ignore-certificate-errors");

    }

    @After
    public void AfterSteps(Scenario scenario){
        dm.dispose();
    }

    @AfterStep
    public void afterStep(Scenario scenario){
        try{
            File screenshot = ((TakesScreenshot)dm.getDriver()).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
            scenario.attach(fileContent,"image/png","screenshot");
            Log.debug("Attaching screenshot... ");
        }catch(Exception e){
            Log.error("Unable to attach screenshot: "+e);
        }

    }
}
