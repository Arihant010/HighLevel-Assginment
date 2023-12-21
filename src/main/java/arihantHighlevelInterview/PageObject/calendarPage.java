package arihantHighlevelInterview.PageObject;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import arihantHighlevelInterview.abstractComponents.abstractComponents;

public class calendarPage extends abstractComponents {

	WebDriver driver;

	public calendarPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "tb_calendar-settings-top")
	WebElement calendarSettings;

	@FindBy(css = "section[class='w-full']")
	WebElement section;

	@FindBy(id = "tb_apppontment-tab")
	WebElement appointments;

	@FindBy(id = "group-A5J7UpaOiBlQcpixhvRv-action-dropdown-trigger")
	WebElement actionDropDown;

	@FindBy(xpath = "//div[@id='group-A5J7UpaOiBlQcpixhvRv-action-dropdown'] //div[@class='n-dropdown-option'][4]")
	WebElement copySchedulingLink;

	// function to access calendar settings
	public void goToCalendarSetings() {
		calendarSettings.click();
	}

	// function to retrieve and invoke scheduling link for a specific group
	public bookingPage getSchedulingLink() throws InterruptedException, UnsupportedFlavorException, IOException {
		WebElement frame = section.findElement(By.name("calendar-app"));
		driver.switchTo().frame(frame);
		waitForElementToAppear(driver.findElement(By.cssSelector(".title")));
		driver.findElement(By.cssSelector("div[data-name='groups'")).click(); // accessing groups
		actionDropDown.click();
		copySchedulingLink.click();
		Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard(); // getting scheduling link form clipboard as the
																		// above button copies it in system
		String bookingLink = (String) c.getData(DataFlavor.stringFlavor);
		driver.get(bookingLink);
		return new bookingPage(driver); // returning object of booking page for further testing

	}

	// function to get appointments for second test
	public void goToAppointments() {
		goToCalendarPage();
		appointments.click();

	}

	// retrieving appointment owner for previously scheduled link
	public String getAppointmentOwner() {
		WebElement frame = section.findElement(By.name("calendar-app"));
		driver.switchTo().frame(frame);
		return driver.findElement(By.xpath("//td[@data-col-key='assignedUserId']")).getText();

	}

}
