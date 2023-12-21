package arihantHighlevelInterview.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import arihantHighlevelInterview.PageObject.calendarPage;

public class abstractComponents {

	WebDriver driver;

	public abstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "sb_calendars")
	WebElement calendar;

	// function to go to calendar page(written in abstract components as it can be
	// accessed from any page)
	public calendarPage goToCalendarPage() {
		waitForElementToAppear(calendar);
		calendar.click();
		return new calendarPage(driver);
	}

	public void waitForElementToAppear(WebElement element) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(15));
		w.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToDisappear(WebElement element) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(15));
		w.until(ExpectedConditions.invisibilityOf(element));
	}

	public void waitForElementToBeClickable(WebElement element) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(15));
		w.until(ExpectedConditions.elementToBeClickable(element));
	}

}
