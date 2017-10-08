package net.jetensky.twa.pages;

import net.jetensky.twa.ObjectWithWebDriverParent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class Page extends ObjectWithWebDriverParent{


  static {
    System.setProperty("webdriver.firefox.bin", "/usr/bin/firefox54");
    // System.setProperty("webdriver.geckodriver.driver",
    //       "C:\\apps\\geckodriver\\geckodriver.exe");
  }

  /*
   * Constructor injecting the WebDriver interface
   * 
   * @param webDriver
   */
  public Page(WebDriver driver) {
    super(driver);
  }

  public String getTitle() {
    return driver.getTitle();
  }

  public void clickLeftMenuItem(String menuItemLabel) {
    clickElement(By.xpath("//span[text()='" +
            menuItemLabel +
            "']"), false);
    waitForElement(By.xpath("//li[contains(@class,'active')]"));

  }

}
