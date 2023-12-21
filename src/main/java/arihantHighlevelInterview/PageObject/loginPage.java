package arihantHighlevelInterview.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import arihantHighlevelInterview.abstractComponents.abstractComponents;

public class loginPage extends abstractComponents {

	WebDriver driver;

	public loginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "email")
	WebElement inputEmail;

	@FindBy(id = "password")
	WebElement inputPassword;

	@FindBy(css = ".hl-btn")
	WebElement loginButton;

	// function for login and return object of next page for further testing
	public calendarPage loginApplication(String email, String Pass) {
		inputEmail.sendKeys(email);
		inputPassword.sendKeys(Pass);
		loginButton.click();
		return new calendarPage(driver);

	}

	public void goTo() {
		driver.get("https://app.gohighlevel.com/");
	}

}
