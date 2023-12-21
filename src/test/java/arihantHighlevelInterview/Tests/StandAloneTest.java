package arihantHighlevelInterview.Tests;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException, UnsupportedFlavorException, IOException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://app.gohighlevel.com/");
		// System.out.println(driver.switchTo().alert().getText());

		// login
		driver.findElement(By.id("email")).sendKeys("ghlqa5@gohighlevel.com");
		driver.findElement(By.id("password")).sendKeys("Test123!");
		driver.findElement(By.cssSelector(".hl-btn")).click(); // login button

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(20));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("sb_calendars")));
		// w.until(ExpectedConditions.visibilityOfElementLocated(By.id("tb_calendar-settings-top")));

		driver.findElement(By.id("sb_calendars")).click();
		driver.findElement(By.id("tb_calendar-settings-top")).click();

		WebElement section = driver.findElement(By.cssSelector("section[class='w-full']"));
		driver.switchTo().frame(section.findElement(By.name("calendar-app")));
		// w.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[data-name='groups'"))));
		Thread.sleep(Duration.ofSeconds(10));
		driver.findElement(By.cssSelector("div[data-name='groups'")).click();
		driver.findElement(By.id("group-A5J7UpaOiBlQcpixhvRv-action-dropdown-trigger")).click();
		driver.findElement(By
				.xpath("//div[@id='group-A5J7UpaOiBlQcpixhvRv-action-dropdown'] //div[@class='n-dropdown-option'][4]"))
				.click();
		Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
		String bookingLink = (String) c.getData(DataFlavor.stringFlavor);

		driver.get(bookingLink);
		String date = "2023-12-21";
		String ts = "08:30 PM";
		List<WebElement> cards = driver.findElements(By.cssSelector(".appointment_widgets--service-event--card"));
		for (int i = 0; i < cards.size(); i++) {
			System.out.println("'" + cards.get(i).getText() + "'");
		}
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".label-select-date"))));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,125)");
		driver.findElement(By.cssSelector(".multi_select_calendar")).sendKeys("kolkata");
		List<WebElement> timeZones = driver.findElements(By.cssSelector(".multiselect__element"));
		timeZones.stream().filter(tz -> tz.getText().contains("GMT+05:30 Asia/Kolkata (GMT+5:30)")).findFirst()
				.orElse(null).click();
		
		// w.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//td[@data-id='\"
		// + date + \"']/div[@class='vdpCellContent']"))));
		driver.findElement(By.xpath("//td[@data-id='" + date + "']/div[@class='vdpCellContent']")).click();
		List<WebElement> slots = driver.findElements(By.xpath("//ul/li[@class='widgets-time-slot']"));
		slots.stream().filter(sl -> sl.getText().equals(ts)).findFirst().orElse(null).click();

		driver.findElement(By.xpath("//button[text()='Continue']")).click();

		// info page
		String fname = "Nitin";
		String lname = "Yadav";
		String phone = "1111111111";
		String email = "nitinyadav@gmial.com";
		driver.findElement(By.id("first_name")).sendKeys(fname);
		driver.findElement(By.id("last_name")).sendKeys(lname);
		driver.findElement(By.id("phone")).sendKeys(phone);
		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.name("terms_and_conditions")).click();
		driver.findElement(By.xpath("//button[text()='Schedule Meeting']")).click();

		// Success page
		System.out.println(driver.findElement(By.xpath("//div/h5")).getText());
	}

}
