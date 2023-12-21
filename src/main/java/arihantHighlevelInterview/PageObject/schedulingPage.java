package arihantHighlevelInterview.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import arihantHighlevelInterview.abstractComponents.abstractComponents;

public class schedulingPage extends abstractComponents {

	WebDriver driver;

	public schedulingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".label-select-date")
	WebElement schedulePage;

	@FindBy(css = ".multi_select_calendar")
	WebElement timeZoneSelect;

	@FindBy(css = ".multiselect__element")
	List<WebElement> timeZones;

	@FindBy(css = ".vdpCell")
	List<WebElement> dates;

	@FindBy(css = ".loader")
	WebElement loader;

	@FindBy(xpath = "//ul/li[@class='widgets-time-slot']")
	List<WebElement> slots;

	@FindBy(xpath = "//button[text()='Continue']")
	WebElement continueButton;

	// setting time details for meeting
	public detailsPage scheduleMeeting(String timeZone, String date, String timeSlot) throws InterruptedException {
		waitForElementToAppear(schedulePage);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,125)");
		timeZoneSelect.sendKeys(timeZone);
		timeZones.stream().filter(tz -> tz.getText().contains(timeZone)).findFirst().orElse(null).click();// setting
																											// timezones
		waitForElementToDisappear(loader);
		WebElement day = dates.stream().filter(dates -> dates.getAttribute("data-id").equals(date)).findFirst()
				.orElse(null);
		day.findElement(By.cssSelector(".vdpCellContent")).click();// setting date for meeting
		slots.stream().filter(slots -> slots.getText().equals(timeSlot)).findFirst().orElse(null).click();// setting
																											// time slot
																											// for
																											// meeting
		continueButton.click();

		return new detailsPage(driver); // returning object of details page for further testing

	}
}
