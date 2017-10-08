package net.jetensky.twa;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ObjectWithWebDriverParent {

    private static Logger log = LoggerFactory.getLogger(ObjectWithWebDriverParent.class);
    protected WebDriver driver;


    public ObjectWithWebDriverParent(WebDriver driver) {
        this.driver = driver;
    }

    protected static WebElement waitForElement(By by, int timeoutInSeconds, WebDriver driverToUse) {

      log.info("Waiting for element " + by.toString() + ", timeout: " + timeoutInSeconds);
      int attemptsRemaining = 10;
      try {
        WebElement element = waitForElementPresenceOnPage(by, timeoutInSeconds, driverToUse);

/*
        ExpectedCondition<Boolean> displayedCondition = e -> element.isDisplayed();
        (new WebDriverWait(driverToUse, timeoutInSeconds)).until(displayedCondition);
*/

        return element;
      } catch (UnreachableBrowserException e) {
        if (attemptsRemaining>0) {
          log.info("waitForElement failed with " + e.getClass() + ", trying again. Remaining tries:" + attemptsRemaining);
          attemptsRemaining--;
          return waitForElement(by, timeoutInSeconds, driverToUse);
        } else {
          throw new IllegalStateException("Cannot finish wait element because of " + e.getClass() + " occured too many times", e);
        }
      } catch (Exception e) {
          log.error("Problem", e);
          throw new IllegalStateException("Cannot finish wait element because of exception", e);
      }

    }

    public static WebElement waitForElementPresenceOnPage(By by, int timeoutInSeconds, WebDriver driverToUse) {
      ExpectedCondition<WebElement> elementCondition = d -> d.findElement(by);
      return (new WebDriverWait(driverToUse, timeoutInSeconds)).until(elementCondition);
    }

    protected static void waitUntil(ExpectedCondition<Boolean> conditionToMeet, int timeOutInSeconds, WebDriver driver) {
      (new WebDriverWait(driver, timeOutInSeconds)).until(conditionToMeet);
    }

    public static void clickElement(WebElement element, WebDriver driverToUse, boolean waitForPageToLoad) {
      // Sometimes, this causes following exception:
      //  Element is not clickable at point (507, 25.783340454101562). Other element would receive the click: <div class="navbar-header"></div>
      //  element.click();
      // So using javascript click instead

      ((JavascriptExecutor) driverToUse).executeScript("arguments[0].click();", element);

      if (waitForPageToLoad) {
        WebElement finalElement = element;
        waitUntil(input -> {
          try {
            finalElement.findElement(By.id("does_not_matter"));
            return false;
          } catch (StaleElementReferenceException e) {
            log.debug("StaleElementReferenceException");
            return true;
          } catch (NoSuchElementException e) {
            log.debug("NoSuchElementException");
            return false;
          }
        }, 30, driverToUse);
      }
    }

    protected void waitForPageToLoad(WebElement elementOnOldPage) {
      WebElement finalElement = elementOnOldPage;
      waitUntil(input -> {
        try {
          finalElement.findElement(By.id("does_not_matter"));
          return false;
        } catch (StaleElementReferenceException e) {
          return true;
        } catch (NoSuchElementException e) {
          return false;
        }
      }, 30, driver);
    }

    public WebElement waitForElement(By by) {
      return waitForElement(by, 60, driver);
    }

    /**
     * Waits until element appears in the page DOM (no matter if it is displayed or not)
     */
    public WebElement waitForElementPresenceOnPage(By by, int timeoutInSeconds) {
      return waitForElementPresenceOnPage(by, timeoutInSeconds, driver);
    }

    public void clickElement(By elementBy, boolean waitForPageToLoad) {
      WebDriver driverToUse = this.driver;
      clickElement(elementBy, driverToUse, waitForPageToLoad);
    }

    public void clickElement(By elementBy, WebDriver driverToUse, boolean waitForPageToLoad) {
      log.debug("Clicking element: " + elementBy);

      waitUntil(d -> {
          try {
              WebElement element = waitForElement(elementBy, 30, driverToUse);
              clickElement(element, driverToUse, waitForPageToLoad);
              return true;
          } catch (StaleElementReferenceException e) {
              return false;
          } catch (Exception e) {
              return false;
          }
      }, 30);

    }

    /**
     * This executes Javascript the safe way - code is able to recover from StaleElementReferenceException
     * @param driverToUse
     * @param js
     * @param elementBy
     */
    protected void executeJavascriptForElement(WebDriver driverToUse, String js, By elementBy) {
      waitUntil(input -> {
        try {
          log.debug("Waiting for element to execute javascript on: " + elementBy);
          WebElement form = waitForElement(elementBy, 30, driverToUse);
          log.debug("Element to execute javascript on found: " + elementBy);
          log.debug("Executing script:" + js);
          ((JavascriptExecutor) driverToUse).executeScript(js, form);
          return true;
        } catch (StaleElementReferenceException e) {
          log.debug("StaleElementException, retrying; for: " + elementBy);
          return false;
        }
      },30);
    }

    public void waitUntil(ExpectedCondition<Boolean> conditionToMeet, int timeOutInSeconds) {
      (new WebDriverWait(this.driver, timeOutInSeconds)).until(conditionToMeet);
    }

    public void sendKeys(String value, By inputElementBy) {
        WebDriver driverToUse = driver;
        sendKeys(value, inputElementBy, driverToUse);
    }

    protected void sendKeys(String value, By inputElementBy, WebDriver driverToUse) {
        WebElement element = waitForElement(inputElementBy, 30, driverToUse); // This prevents "Element is not currently visible and so may not be interacted with"
        waitUntil(driver -> {
            try {
                element.clear();
                element.sendKeys(value);
                return true;
            } catch (StaleElementReferenceException e) {
                return false;
            }
        }, 30);
    }

}
