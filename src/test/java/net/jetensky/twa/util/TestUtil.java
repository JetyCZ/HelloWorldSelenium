package net.jetensky.twa.util;

import net.jetensky.twa.pages.IndexPage;
import org.openqa.selenium.WebDriver;

public class TestUtil {

    public static void loginTestUser(WebDriver driver, IndexPage indexPage) throws InterruptedException {
        driver.get("http://toggl.com");

        indexPage.clickLogin();
        indexPage.typeEmail("pavel.jetensky@seznam.cz");
        indexPage.typePassword("stromlze");

        Thread.sleep(1000);

        indexPage.submitLoginForm();
    }
}
