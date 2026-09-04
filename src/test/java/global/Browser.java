package global;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Properties;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser {

	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static ExtentReports reports = new ExtentReports();

	@BeforeMethod
	public void launchBrowser() {
		// prop, fis will help you to read data from config.properties file and provide
		// required results.
		FileInputStream fis;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\configuration\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String browser = prop.getProperty("browser");
		switch (browser.toLowerCase()) {
		case "chrome": {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			manageBrowser();
			break;
		}
		case "edge": {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			manageBrowser();
			break;
		}
		case "firefox": {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			manageBrowser();
			break;
		}
		default: {
			//System.out.println("Invalid Browser");
			//if invalid browser is used, we need to throw exception, not as passed, it should fail.
			//so we use throw keyword to throw exception
			throw new InvalidArgumentException("Invalid Browser" + browser);
		}
		}

	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
	
	public void manageBrowser() {
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		String time = (String) prop.get("IMPLICIT_WAIT");
		long timeInLong = Long.parseLong(time);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInLong));
	}
	
	@BeforeSuite
	public void startExtendReport() {
		String path = System.getProperty("user.dir")+"\\Reports";
		String time = Instant.now().toString().replace(":", "-");
		ExtentSparkReporter reporter = new ExtentSparkReporter(path+"\\ExtendReport_" + time);
		reports.attachReporter(reporter);
	}
	
	@AfterSuite
	public void stopReport() {
		reports.flush(); //closing the report
	}
	
	public Media  getScreenshot() {
		TakesScreenshot ts = (TakesScreenshot)driver;
		return MediaEntityBuilder.createScreenCaptureFromBase64String(ts.getScreenshotAs(OutputType.BASE64)).build();
	}
	
	// // Jenkins Poll SCM test - updated
}
