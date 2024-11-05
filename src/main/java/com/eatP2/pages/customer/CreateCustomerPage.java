package com.eatP2.pages.customer;

import com.eatP2.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateCustomerPage extends BasePage {
    private By createCustomerTitle= By.xpath("/html/body/app-root/app-createcustomer/body/div[1]/div[1]/h3");

    public CreateCustomerPage(WebDriver driver) {
        super(driver);
    }

    public String getCreateCustomerTitle() {
        return find(createCustomerTitle).getText();
    }
}
