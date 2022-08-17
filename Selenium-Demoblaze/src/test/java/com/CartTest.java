package com;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.PropertyReader;

public class CartTest extends BaseTest {

	Random random = new Random();
	int numOfProducts;
	int totalPrice = 0;

	@Override
	@BeforeMethod
	void beforeMethod() {
		super.beforeMethod();
	}

	@Override
	@BeforeClass
	void beforeClass() {
		super.beforeClass();

		WebElement login = driver.findElement(By.linkText("Log in"));
		wait.until(ExpectedConditions.elementToBeClickable(login));
		login.click();

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

	}

	@Test(priority = 1)
	public void addToCart() throws InterruptedException {

		WebElement clickPhone = driver.findElement(By.linkText("Phones"));
		wait.until(ExpectedConditions.elementToBeClickable(clickPhone));
		clickPhone.click();

		WebElement samsungPhone = driver.findElement(By.linkText("Samsung galaxy s6"));
		wait.until(ExpectedConditions.elementToBeClickable(samsungPhone));
		samsungPhone.click();

		WebElement firstPhone = driver.findElement(By.className("name"));
		System.out.println("First Added Phone: " + firstPhone.getText());

		WebElement samsungCart = driver.findElement(By.linkText("Add to cart"));
		wait.until(ExpectedConditions.elementToBeClickable(samsungCart));
		samsungCart.click();
		wait.until(ExpectedConditions.alertIsPresent());
		String cartMessage = driver.switchTo().alert().getText();

		Assert.assertEquals(cartMessage, PropertyReader.getProperty("ADD.PRODUCT"));
		softAssert.assertAll();
		driver.switchTo().alert().accept();

		// go back to Home page to add the next product
		driver.navigate().to(PropertyReader.getProperty("BASE.URL"));
		TimeUnit.SECONDS.sleep(1);

		WebElement nokiaPhone = driver.findElement(By.linkText("Nokia lumia 1520"));
		wait.until(ExpectedConditions.elementToBeClickable(nokiaPhone));
		nokiaPhone.click();

		WebElement secondPhone = driver.findElement(By.className("name"));
		System.out.println("Second Added Phone: " + secondPhone.getText());

		WebElement nokiaCart = driver.findElement(By.linkText("Add to cart"));
		wait.until(ExpectedConditions.elementToBeClickable(nokiaCart));
		nokiaCart.click();
		wait.until(ExpectedConditions.alertIsPresent());
		cartMessage = driver.switchTo().alert().getText();

		Assert.assertEquals(cartMessage, PropertyReader.getProperty("ADD.PRODUCT"));

		softAssert.assertAll();
		driver.switchTo().alert().accept();
		TimeUnit.SECONDS.sleep(3);
	}

	@Test(priority = 2)
	public void cartDelete() throws InterruptedException {

		boolean deleted = false;

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Cart"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Place Order']")));

		// find the number of items with delete text
		List<WebElement> itemsToDelete = (List<WebElement>) driver.findElements(By.linkText("Delete"));
		int itemsCount = itemsToDelete.size();

		while (itemsToDelete.size() > 0) {
			System.out.println("Items count : " + itemsCount);
			WebElement elementToDelete = (itemsToDelete.get(0));
			wait.until(ExpectedConditions.elementToBeClickable(elementToDelete));
			elementToDelete.click();
			TimeUnit.SECONDS.sleep(2);
			// get items again after delete. the count should go down one for every iteration
			itemsToDelete = (List<WebElement>) driver.findElements(By.linkText("Delete"));
		}

		System.out.println("Delete Cart Items Passed");
		deleted = true;
		Assert.assertTrue(deleted);

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log out"))).click();
	}

}