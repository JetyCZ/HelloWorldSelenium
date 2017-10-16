package net.jetensky.twa.toggl.client;

import net.jetensky.twa.JUnitTestBase;
import net.jetensky.twa.pages.ClientsPage;
import net.jetensky.twa.pages.IndexPage;
import net.jetensky.twa.util.TestUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class ClientAddTest extends JUnitTestBase {


  private IndexPage indexPage;
  private ClientsPage clientsPage;



  @Before
  public void initPageObjects() {
    String jenkins;

    indexPage = new IndexPage(driver);
    clientsPage = new ClientsPage(driver);
  }

  @Test
  public void testAddClient() throws InterruptedException {
    TestUtil.loginTestUser(driver, indexPage);

    Thread.sleep(300);
    indexPage.clickLeftMenuItem("Clients");

    String clientName = clientsPage.typeClientName("TEST" + RandomStringUtils.randomAlphanumeric(4));
    Thread.sleep(300);
    clientsPage.clickAddButton(By.xpath("//button[text()='Add']"));
    By deleteIconBy = clientsPage.clickDeleteIcon(clientName);
    clientsPage.clickDeleteButton();
    Assert.assertEquals(0, driver.findElements(deleteIconBy).size());
  }



}
