package net.jetensky.twa;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.*;
import ru.stqa.selenium.factory.WebDriverPool;

import java.net.URL;

/**
 * Base class for all the JUnit-based test classes
 */
public class JUnitTestBase {
  protected WebDriver driver;

  
  static {
    System.setProperty("webdriver.firefox.bin", "/usr/bin/firefox54");
    // System.setProperty("webdriver.geckodriver.driver",
    //       "C:\\apps\\geckodriver\\geckodriver.exe");
  }

  protected static URL gridHubUrl;
  protected static String baseUrl;
  protected static Capabilities capabilities;

  @ClassRule
  public static ExternalResource webDriverProperties = new ExternalResource() {
    @Override
    protected void before() throws Throwable {
      SuiteConfiguration config = new SuiteConfiguration();
      baseUrl = config.getProperty("site.url");
      if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
        gridHubUrl = new URL(config.getProperty("grid.url"));
      }
      capabilities = config.getCapabilities();
    };
  };

  @Rule
  public ExternalResource webDriver = new ExternalResource() {

    @Override
    protected void before() throws Throwable {
      driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
    };
  };


}
