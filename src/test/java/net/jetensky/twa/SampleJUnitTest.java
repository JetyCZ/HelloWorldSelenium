package net.jetensky.twa;

import net.jetensky.twa.pages.HomePage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SampleJUnitTest extends JUnitTestBase {

  private HomePage homepage;

  static {
    System.setProperty("webdriver.firefox.bin", "/usr/bin/firefox54");
  }

  @Before
  public void initPageObjects() {
    homepage = new HomePage(driver);
  }

  @Test
  public void testHomePageHasAHeader() {
    driver.get("http://idos.cz");
    Assert.assertTrue(driver.getTitle().contains("Autobusy"));
  }
}
