package com.eatP2;

import com.eatP2.pages.BasePage;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    protected SoftAssertions softAssertions;
    protected WebDriverWait wait;

    @Before
    public void setUp() {
        // Set the path to the chromedriver executable
        ChromeOptions options = new ChromeOptions();

        // "Chrome is being controlled by automated test software" mesajını gizler
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});


        driver = new ChromeDriver(options);
        softAssertions = new SoftAssertions();
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }


    @After
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
        softAssertions.assertAll();

    }

}
