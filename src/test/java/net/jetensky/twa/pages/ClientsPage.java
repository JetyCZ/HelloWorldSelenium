package net.jetensky.twa.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ClientsPage extends Page {
    public ClientsPage(WebDriver driver) {
        super(driver);
    }

    public void clickDeleteButton() throws InterruptedException {
        Thread.sleep(500);
        clickElement(By.xpath("//button[text()='Delete']"), false);
        Thread.sleep(500);
    }

    public By clickDeleteIcon(String clientName) {
        By deleteIconBy = By.xpath("//span[contains(text(), '" + clientName + "')]/i[@class='fa fa-times']");
        clickElement(deleteIconBy, false);
        boolean contains = driver.getPageSource().contains("Are you sure you want to delete this client?");
        Assert.assertTrue(contains);
        return deleteIconBy;
    }

    public void clickAddButton(By xpath) {
        clickElement(xpath, false);
    }

    public String typeClientName(String clientName) {
        sendKeys(clientName, By.name("name"));
        return clientName;
    }

}
