package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BasePage;

public class SignIn_Page extends BasePage {

	public SignIn_Page(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@name='email1']")
	WebElement email;

	@FindBy(xpath = "//input[@name='password1']")
	WebElement password;

	@FindBy(xpath = "//button[text()='Sign in']")
	WebElement signin_btn;

	public void setEmail(String userEmail) {
		email.sendKeys(userEmail);
	}

	public void setPassword(String pwd) {
		password.sendKeys(pwd);

	}

	public void setSignBtn() {
		signin_btn.click();
	}

}
