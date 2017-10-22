package net.jetensky.twa.toggl.client;

import net.jetensky.twa.JUnitTestBase;
import net.jetensky.twa.util.TestUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientAdd2Test extends JUnitTestBase {


  @Autowired TestUtil testUtil;

  @Test
  public void testAddClient() throws InterruptedException {
    testUtil.loginTestUser();
  }


}
