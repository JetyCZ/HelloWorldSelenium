package net.jetensky.twa.toggl;

import net.jetensky.twa.JUnitTestBase;
import net.jetensky.twa.pages.IndexPage;
import net.jetensky.twa.util.TestUtil;
import org.junit.Before;
import org.junit.Test;

public class LogInTest extends JUnitTestBase {


  private IndexPage indexPage;

  @Before
  public void initPageObjects() {
    indexPage = new IndexPage(driver);
  }

  @Test
  public void testLogIn() throws InterruptedException {
    TestUtil.loginTestUser(driver, indexPage);
  }


}
