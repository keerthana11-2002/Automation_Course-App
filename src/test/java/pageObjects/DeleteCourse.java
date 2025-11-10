package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testBase.BasePage;

public class DeleteCourse extends BasePage {
    public DeleteCourse(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='name']")
    WebElement name;

    @FindBy(xpath = "//div[@class='table-responsive']//tr[1]//td[2]")
    WebElement checkCourseName;
    
    @FindBy(xpath = "//div[@class='table-responsive']//tr[1]//td[12]")
    WebElement deleteCourseName;
    
    @FindBy(xpath="//img[@alt='menu']")
    WebElement menu;
    
    @FindBy(xpath="//button[text()='Sign out']")
    WebElement signout_btn;
    
    
    
    public void verifyName(String courseName) {
        try {
            String xpath = "//table//td[contains(text(),'" + courseName + "')]";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement course = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

            System.out.println("Course found in table: " + course.getText());
             

        } catch (Exception e) {
            System.out.println("Course not found in the table");
        }
    }
    
    public void deleteCourse() {
    	deleteCourseName.click();
    	System.out.println("Course is deleted sucessfully");
    }
    
    public void signout() {
    	menu.click();
    	signout_btn.click();
    }

}
