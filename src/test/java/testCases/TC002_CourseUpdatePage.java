package testCases;

import org.testng.annotations.Test;

import pageObjects.Course_update;
import pageObjects.DeleteCourse;
import pageObjects.SignIn_Page;
import testBase.BasePage_TestCases;

public class TC002_CourseUpdatePage extends BasePage_TestCases {

	@Test
	public void signup() {
		SignIn_Page obj = new SignIn_Page(driver);
		obj.setEmail(p.getProperty("email"));
		obj.setPassword(p.getProperty("password"));
		obj.setSignBtn();
	}

	@Test(dependsOnMethods = "signup")

	public void course() {
		Course_update obj = new Course_update(driver);
		obj.mouseAction();
		obj.clickCourse();
		obj.clickAddCourse();
		logger.info("*******Successfully we click add on course button*******");
		obj.insertFile();
		logger.info("*******Successfully we upload the file*******");

		obj.courseName();
		obj.courseDesc();
		obj.courseInstructor();
		obj.coursePrice();
		obj.selectStartDate();
		obj.selectEndDate();
		obj.selectCategory();
		obj.selectSave();
		logger.info("*******Successfully course is created on the table*******");

	}

	@Test(dependsOnMethods = "course")
	public void deleteCourse() {
		DeleteCourse obj = new DeleteCourse(driver);
		obj.verifyName("Javascript");
		obj.deleteCourse();
		logger.info("*******Successfully we deleted the course*******");

		obj.signout();
		logger.info("*******Successfully we signout the app*******");

	}

}
