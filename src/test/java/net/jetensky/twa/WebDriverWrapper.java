package net.jetensky.twa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Component;

@Component
public class WebDriverWrapper {

    private FirefoxDriver webDriver;

    public WebDriver get() {
        return webDriver;
    }

    public void createNew() {
        this.webDriver = new FirefoxDriver();
    }

    public void quit() {
        this.webDriver.quit();
    }


}
