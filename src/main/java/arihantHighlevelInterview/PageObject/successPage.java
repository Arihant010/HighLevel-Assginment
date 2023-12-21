package arihantHighlevelInterview.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import arihantHighlevelInterview.abstractComponents.abstractComponents;

public class successPage extends abstractComponents {

	WebDriver driver;

	public successPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".confirmation-message")
	WebElement confMsg;

	// function to retrieve confirmation message of meeting scheduled
	public String getConfMsg() {
		return confMsg.getText();
	}

}
