package pageObjects;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;

import java.time.Duration;

public class BasePage {

    WebDriver driver;

    WebDriverWait WAIT;
    public BasePage(WebDriver drv, Long timeout){
        driver = drv;

        WAIT = new WebDriverWait(driver,Duration.ofSeconds(timeout));
    }

    public void waitUntilIsPresent(By elem){

        try{

            Log.debug("Waiting for element: "+elem+" to be present in the DOM");
            WAIT.ignoring(NoSuchElementException.class).until(ExpectedConditions.presenceOfElementLocated(elem));

        }catch(Exception e){
            Log.error("Unable to locate element identify by:  "+elem);
            throw new RuntimeException(e);
        }

    }

    public void waitUntilIsVisible(WebElement elem){

        try{

            Log.debug("Waiting for element: "+elem+" to be present in the DOM");
            WAIT.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(elem));

        }catch(StaleElementReferenceException e){
            Log.error(e.toString());
            Log.debug("Retrying....");
            sleepTime(3000);
            WAIT.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(elem));
        } catch(Exception e){
            Log.error("Unable to locate element identify by:  "+elem);
            throw new RuntimeException(e);
        }

    }
    public void waitUntilIsVisible(By elem){

        try{

            Log.debug("Waiting for element: "+elem+" to be present in the DOM");
            WAIT.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOfElementLocated(elem));

        }catch(Exception e){
            Log.error("Unable to locate element identify by:  "+elem);
            throw new RuntimeException(e);
        }

    }

    public void waitUntilInvisibilityOf(By elem){

        try{

            Log.debug("Waiting for element: "+elem+" to be invisible");
            WAIT.ignoring(NoSuchElementException.class).until(ExpectedConditions.invisibilityOfElementLocated(elem));

        }catch(Exception e){
            Log.error("Unable to locate element identify by:  "+elem);
            throw new RuntimeException(e);
        }

    }
    public void sleepTime(int time){
        try{
            Log.debug("Sleeping for: "+time/1000+"S");
            Thread.sleep(time);
        }catch(InterruptedException e){
            Log.error("Something went wrong when the execution wa interrupted: "+e);
            throw new RuntimeException(e);
        }
    }

    public void clickElement(By elem){

        try {
            Log.debug("Clicking element identified by: " + elem);
            waitUntilIsPresent(elem);
            driver.findElement(elem).click();
        }catch(ElementClickInterceptedException e) {
            Log.debug("Error trying to click element identified by: " + elem + " click intercepted,retrying to click element");
            sleepTime(3000);
            driver.findElement(elem).click();
        }catch(ElementNotInteractableException e){
            Log.debug("Element Error trying to click element identified by: "+elem+" element not interactable, retrying");
            sleepTime(3000);
            driver.findElement(elem).click();
        }catch(Exception e){
            Log.error("click failed: "+e );
            throw new RuntimeException(e);
        }

    }

    public void enterText(By elem, String text){
        try{
            waitUntilIsPresent(elem);
            driver.findElement(elem).clear();
            driver.findElement(elem).sendKeys(text);
        }catch(ElementNotInteractableException e){
            Log.debug("Error trying to send keys to element identified by: " + elem + " retrying...");
            sleepTime(3000);
            driver.findElement(elem).click();
        }catch(Exception e){
            Log.error("sendkeys failed: "+e );
            throw new RuntimeException(e);
        }
    }
}
