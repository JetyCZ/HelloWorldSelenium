package net.jetensky.twa.util;

import net.jetensky.twa.ObjectWithWebDriverParent;
import net.jetensky.twa.pages.IndexPage;
import net.jetensky.twa.pages.MainPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestUtil extends ObjectWithWebDriverParent{

    @Autowired IndexPage indexPage;
    @Autowired MainPage mainPage;

    public MainPage loginTestUser() throws InterruptedException {
        driver().get("http://toggl.com");

        indexPage.clickLogin();
        indexPage.typeEmail("pavel.jetensky@seznam.cz");
        indexPage.typePassword("stromlze");

        Thread.sleep(300);
        indexPage.submitLoginForm();
        return mainPage;
    }
}
