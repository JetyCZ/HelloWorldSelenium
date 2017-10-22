package net.jetensky.twa;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * Base class for all the JUnit-based test classes
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SeleniumTestCaseContext.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })

public class JUnitTestBase {

  @Autowired protected WebDriverWrapper driverWrapper;
  static {
    System.setProperty("webdriver.firefox.bin", "/usr/bin/firefox54");
  }

  @Before
  public void createNewDriver() {
    driverWrapper.createNew();
  }

  @After
  public void quitDriver() {
      driverWrapper.quit();
  }

  protected WebDriver driver() {
      return driverWrapper.get();
  }
}
