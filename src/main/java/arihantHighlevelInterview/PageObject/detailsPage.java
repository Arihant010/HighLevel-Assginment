package arihantHighlevelInterview.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import arihantHighlevelInterview.abstractComponents.abstractComponents;

public class detailsPage extends abstractComponents {

	WebDriver driver;

	public detailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "first_name")
	WebElement fName;

	@FindBy(id = "last_name")
	WebElement lName;

	@FindBy(id = "phone")
	WebElement phone;

	@FindBy(name = "email")
	WebElement email;

	@FindBy(name = "terms_and_conditions")
	WebElement tnc;

	@FindBy(xpath = "//button[text()='Schedule Meeting']")
	WebElement scheduleMeetingButton;

	// setting user details of customer for meeting
	public successPage scheduleMeeting(String fname, String lname, String phoneNumber, String userEmail) {
		fName.sendKeys(fname);
		lName.sendKeys(lname);
		phone.sendKeys(phoneNumber);
		email.sendKeys(userEmail);
		tnc.click();
		scheduleMeetingButton.click();

		return new successPage(driver);

	}

}
