package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BasePage_TestCases {
	protected WebDriver driver;
	public Logger logger;

	public Properties p;

	@BeforeClass
	@Parameters({ "os", "browser" })

	public void setup(String os, String br) throws InterruptedException, IOException {

		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass());

		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilties = new DesiredCapabilities();
			if (os.equalsIgnoreCase("windows")) {
				capabilties.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("mac")) {
				capabilties.setPlatform(Platform.MAC);
			} else if (os.equalsIgnoreCase("Linux")) {
				capabilties.setPlatform(Platform.LINUX);
			} else {
				System.out.println("No matching os");
				return;
			}

			switch (br.toLowerCase()) {
			case "chrome":
				capabilties.setBrowserName("chrome");
				break;
			case "edge":
				capabilties.setBrowserName("edge");
				break;
			case "firefox":
				capabilties.setBrowserName("firefox");
				break;
			default:
				System.out.println("No matching browser ...");
				return;
			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444/"), capabilties);
		}

		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid browser name...");
				return;
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));
		logger.info("********Launching our website*******");

	}
	
	public String captureScreen(String tname) {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilepath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "-" + timeStamp + ".png";
		File targetFile = new File(targetFilepath);
		sourceFile.renameTo(targetFile);
		return targetFilepath;
	}

	@AfterClass
	public void teardown() {
		driver.quit();
		logger.info("Closed our application");
	}
}
