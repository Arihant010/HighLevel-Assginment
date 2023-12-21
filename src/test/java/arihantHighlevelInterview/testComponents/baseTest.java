package arihantHighlevelInterview.testComponents;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import arihantHighlevelInterview.PageObject.loginPage;

public class baseTest {

	public WebDriver driver;
	public loginPage loginPage;
	String browser = "chrome";

	public WebDriver initializeDriver() throws IOException {

		// invoking browser for testing

		// can add more browsers
		if (browser.contentEquals("chrome")) {

			driver = new ChromeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();

		return driver;

	}

	@BeforeMethod(alwaysRun = true)
	public void launchApplication() throws IOException {

		driver = initializeDriver();

		// invoking login page at the start of every test to start testing
		loginPage = new loginPage(driver);
		loginPage.goTo();

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		// closing browser
		driver.close();
	}

	public List<HashMap<String, String>> getJsonDataToMap(String FilePath) throws IOException {

		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(FilePath), StandardCharsets.UTF_8);

		// Convert string(converted from json) to hashmap

		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	public String getScreenShot(String testCaseName, WebDriver name) throws IOException {

		// code for screenshot

		TakesScreenshot ts = (TakesScreenshot) name;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return file.getName();

	}

}
