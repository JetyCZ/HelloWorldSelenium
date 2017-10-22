package net.jetensky.twa.pages;

import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainPage extends Page {
    @Autowired ClientsPage clientsPage;

    public ClientsPage clickClients() {
        clickLeftMenuItem("Clients");
        return clientsPage;
    }

    public void clickLeftMenuItem(String menuItemLabel) {
        clickElement(By.xpath("//span[text()='" +
                menuItemLabel +
                "']"), false);
        waitForElement(By.xpath("//li[contains(@class,'active')]"));

    }

}
