package net.jetensky.twa.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
public class IndexPage extends Page {

    public void submitLoginForm() {
        By logInButtonBy = By.xpath("//button[text()='Log in']");
        clickElement(logInButtonBy, true);
        Assert.assertNotNull(waitForElement(By.xpath("//span[text()='Timer']")));
    }

    public void typePassword(String password) {
        driver().findElement(By.name("password")).sendKeys(password);
    }

    public void typeEmail(String email) {
        driver().findElement(By.name("email")).sendKeys(email);
    }

    public void clickLogin() {
        driver().findElement(By.linkText("Log in")).click();
    }
}
