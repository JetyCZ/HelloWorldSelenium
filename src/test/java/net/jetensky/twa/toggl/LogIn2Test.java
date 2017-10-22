package net.jetensky.twa.toggl;

import net.jetensky.twa.JUnitTestBase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class LogIn2Test extends JUnitTestBase {

  @Test
  public void testHomePageHasAHeader() throws InterruptedException {
    driver().get("http://toggl.com");

    driver().findElement(By.linkText("Log in")).click();
    driver().findElement(By.name("email")).sendKeys("pavel.jetensky@seznam.cz");
    driver().findElement(By.name("password")).sendKeys("stromlze");

    Thread.sleep(300);
    driver().findElement(By.xpath("//button[text()='Log in']")).click();

    Assert.assertEquals(1, driver().findElements(By.xpath("//div[@class='Timer__button']")).size());

  }
}
