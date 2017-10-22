package net.jetensky.twa.toggl.smoke;

import net.jetensky.twa.JUnitTestBase;
import net.jetensky.twa.util.TestUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class LogInTest extends JUnitTestBase {

  @Autowired TestUtil testUtil;

  @Test
  public void testLogIn() throws InterruptedException {
    testUtil.loginTestUser();
  }


}
