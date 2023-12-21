package arihantHighlevelInterview.PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import arihantHighlevelInterview.abstractComponents.abstractComponents;

public class bookingPage extends abstractComponents {

	WebDriver driver;

	public bookingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".appointment_widgets--service-event--card")
	List<WebElement> meetingCards;

	// selecting specified calendar to schedule meeting
	public schedulingPage bookMeeting(String calendarName) {

		meetingCards.stream().filter(cards -> cards.getText().contains(calendarName)).findFirst().orElse(null).click();
		return new schedulingPage(driver);
	}

}
