package com;

import util.PropertyReader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest extends Base {

	@Override
	@BeforeClass
	void beforeClass() {
		super.beforeClass();
		WebElement login = driver.findElement(By.linkText("Log in"));
		wait.until(ExpectedConditions.elementToBeClickable(login));
		login.click();
	}

	@Test(priority = 1)
	public void blankFieldsLogin() {

		WebElement userName = driver.findElement(By.id("loginusername"));
		wait.until(ExpectedConditions.visibilityOf(userName));
		userName.clear();
		userName.sendKeys("");

		WebElement userPassword = driver.findElement(By.id("loginpassword"));
		wait.until(ExpectedConditions.visibilityOf(userPassword));
		userPassword.clear();
		userPassword.sendKeys("");

		driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();

		wait.until(ExpectedConditions.alertIsPresent());
		String alertMessage = driver.switchTo().alert().getText();
		System.out.println("Validate Empty Message: " + alertMessage);
		softAssert.assertEquals(alertMessage, PropertyReader.getProperty("EMPTY.LOGIN"));

		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		softAssert.assertAll();
	}

@Test(priority = 2)
	public void invalidUsername() {

		WebElement userName = driver.findElement(By.id("loginusername"));
		wait.until(ExpectedConditions.visibilityOf(userName));
		userName.clear();
		userName.sendKeys("invaliduser");

		WebElement userPassword = driver.findElement(By.id("loginpassword"));
		wait.until(ExpectedConditions.visibilityOf(userPassword));
		userPassword.clear();
		userPassword.sendKeys("incorrectPassword");

		driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();

		wait.until(ExpectedConditions.alertIsPresent());
		String alertMessage = driver.switchTo().alert().getText();
		System.out.println("Validate Correct user / Invalid password : " + alertMessage);
		softAssert.assertEquals(alertMessage, PropertyReader.getProperty("INVALID.LOGIN"));

		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		softAssert.assertAll();

	}
@Test(priority = 3)
public void validLogin() {

	WebElement userName = driver.findElement(By.id("loginusername"));
	wait.until(ExpectedConditions.visibilityOf(userName));
	userName.clear();
	userName.sendKeys("Eshwari");

	WebElement userPassword = driver.findElement(By.id("loginpassword"));
	wait.until(ExpectedConditions.visibilityOf(userPassword));
	userPassword.clear();
	userPassword.sendKeys("Mobile#02");

	driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();

	wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log out")));

	WebElement welcomeUser = driver.findElement(By.id("nameofuser"));
	wait.until(ExpectedConditions.visibilityOf(welcomeUser));

	System.out.println("Validate Boolean welcomeUser: " + welcomeUser.isDisplayed());
	Assert.assertEquals(welcomeUser.getText(), "Welcome Eshwari");
	Assert.assertTrue(welcomeUser.isDisplayed());

	wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log out"))).click();

}

@Test(priority = 4)
public void validUserWithInvalidPassword() {

	WebElement userName = driver.findElement(By.id("loginusername"));
	wait.until(ExpectedConditions.visibilityOf(userName));
	userName.clear();
	userName.sendKeys("Eshwari");

	WebElement userPassword = driver.findElement(By.id("loginpassword"));
	wait.until(ExpectedConditions.visibilityOf(userPassword));
	userPassword.clear();
	userPassword.sendKeys("incorrectPassword");

	driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();

	wait.until(ExpectedConditions.alertIsPresent());
	String alertMessage = driver.switchTo().alert().getText();
	System.out.println("Validate Correct user / Invalid password : " + alertMessage);
	softAssert.assertEquals(alertMessage, PropertyReader.getProperty("WRONG.PASSWORD.LOGIN"));

	wait.until(ExpectedConditions.alertIsPresent());
	driver.switchTo().alert().accept();
	softAssert.assertAll();
}
	

}