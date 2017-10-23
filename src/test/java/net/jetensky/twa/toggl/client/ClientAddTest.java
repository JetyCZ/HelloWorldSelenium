package net.jetensky.twa.toggl.client;

import net.jetensky.twa.JUnitTestBase;
import net.jetensky.twa.pages.ClientsPage;
import net.jetensky.twa.pages.IndexPage;
import net.jetensky.twa.pages.MainPage;
import net.jetensky.twa.util.TestUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientAddTest extends JUnitTestBase {

  @Autowired TestUtil testUtil;

  @Autowired IndexPage indexPage;

  @Test
  public void testAddClient() throws InterruptedException {
    MainPage mainPage = testUtil.loginTestUser();
    
    Thread.sleep(300);
    ClientsPage clientsPage = mainPage.clickClients();

    String clientName = clientsPage.typeClientName("TEST" + RandomStringUtils.randomAlphanumeric(4));
    Thread.sleep(300);
    clientsPage.clickAddButton(By.xpath("//button[text()='Add']"));
    By deleteIconBy = clientsPage.clickDeleteIcon(clientName);
    clientsPage.clickDeleteButton();
    Assert.assertEquals(0, driver().findElements(deleteIconBy).size());
  }



}
