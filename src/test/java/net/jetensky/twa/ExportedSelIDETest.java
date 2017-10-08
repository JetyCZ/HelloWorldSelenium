package net.jetensky.twa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ExportedSelIDETest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  static {
    System.setProperty("webdriver.firefox.bin", "/usr/bin/firefox54");
    System.setProperty("webdriver.geckodriver.driver",
            "C:\\apps\\geckodriver\\geckodriver.exe");
  }


  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://jizdnirady.idnes.cz/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testExportedSelIDE() throws Exception {
    driver.get(baseUrl);
    driver.findElement(By.id("ctl00_cDM_cF_0t")).sendKeys("Pardubice");
    driver.findElement(By.id("ctl00_cDM_cT_0t")).sendKeys("Praha");
    driver.findElement(By.id("ctl00_cDM_cSB_cmdSearch")).click();
    // Warning: assertTextPresent may require manual changes
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Pardubice hl\\.n\\.[\\s\\S]*$"));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
  
}
