package com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import util.PropertyReader;

public class BaseTest {

	static WebDriver driver ;    
    public WebDriverWait wait;
    public SoftAssert softAssert;
    
    @BeforeSuite
    void beforeSuite() {
    	System.out.println("beforeSuite");
       WebDriverManager.chromedriver().setup();
       
       ChromeOptions options = new ChromeOptions();
       options.addArguments("disable-infobars");
       driver = new ChromeDriver(options);
       driver.manage().window().maximize();
       driver.navigate().to(PropertyReader.getProperty("BASE.URL"));
       driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
       
    }
    
    @BeforeMethod
    void beforeMethod() {
    	
    }
     
    @AfterSuite(alwaysRun = true)
    void tearDownTest() {
    	driver.close();
    }


    @BeforeClass
	void beforeClass() {
    	wait =  new WebDriverWait(driver, 15);
    	softAssert = new SoftAssert();
	}
}

