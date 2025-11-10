package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import testBase.BasePage;

public class Course_update extends BasePage {

	public Course_update(WebDriver driver) {
		super(driver);
	}

	public void selectDate(String day, String monthYear) {

		String monYearText = driver.findElement(By.xpath("//div[contains(@class,'react-datepicker__current-month')]"))
				.getText();
		while (!monYearText.equals(monthYear)) {
			WebElement nextMonth = driver.findElement(By.xpath("//button[@aria-label='Next Month']"));
			nextMonth.click();
			monYearText = driver.findElement(By.xpath("//div[contains(@class,'react-datepicker__current-month')]"))
					.getText();
		}

		WebElement date = driver.findElement(By.xpath("//div[contains(@class,'react-datepicker__day') and text()='"
				+ day + "' and not(contains(@class,'outside-month'))]"));
		date.click();
	}

	@FindBy(xpath = "//span[text()='Manage']")
	WebElement mouseHover;

	@FindBy(xpath = "//a[text()='Manage Courses']")
	WebElement manageCourse;

	@FindBy(xpath = "//button[text()='Add New Course ']")
	WebElement addCourse;

	@FindBy(xpath = "//input[@id='thumbnail']")
	WebElement file;

	@FindBy(xpath = "//input[@id='name']")
	WebElement name;

	@FindBy(xpath = "	//textarea[@id='description']")
	WebElement description;

	@FindBy(xpath = "//input[@id='instructorNameId']")
	WebElement instructor;

	@FindBy(xpath = "//input[@id='price']")
	WebElement price;

	@FindBy(xpath = "//input[@name='startDate']")
	WebElement startDate;

	@FindBy(xpath = "//input[contains(@name,'endDate')]")
	WebElement endDate;

	@FindBy(xpath = "//img[@alt='select category']")
	WebElement category;

	@FindBy(xpath = "//button[normalize-space()='Cypress']")
	WebElement categoryName;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	WebElement save_btn;

	public void mouseAction() {
		Actions a = new Actions(driver);
		a.moveToElement(mouseHover).perform();

	}

	public void clickCourse() {
		manageCourse.click();

	}

	public void clickAddCourse() {
		addCourse.click();

	}

	public void insertFile() {
		file.sendKeys(System.getProperty("user.dir") + "\\images\\quotes.png");
	}

	public void courseName() {
		name.sendKeys("Javascript");
	}

	public void courseDesc() {
		description.sendKeys(
				"JavaScript (JS) is a high-level, interpreted programming language primarily used to make web pages interactive.");
	}

	public void courseInstructor() {
		instructor.sendKeys("Keerthana");
	}

	public void coursePrice() {
		price.clear();
		price.sendKeys("1000");
	}

	public void selectStartDate() {
		startDate.click();
		selectDate("1", "July 2026");

	}

	public void selectEndDate() {
		endDate.click();
		selectDate("31", "October 2026");

	}

	public void selectCategory() {
		category.click();
		categoryName.click();
	}

	public void selectSave() {
		save_btn.click();
	}

}
