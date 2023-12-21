package arihantHighlevelInterview.Tests;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import arihantHighlevelInterview.PageObject.bookingPage;
import arihantHighlevelInterview.PageObject.calendarPage;
import arihantHighlevelInterview.PageObject.detailsPage;
import arihantHighlevelInterview.PageObject.schedulingPage;
import arihantHighlevelInterview.PageObject.successPage;
import arihantHighlevelInterview.testComponents.baseTest;

public class scheduleTests extends baseTest {

	String email = "ghlqa5@gohighlevel.com";
	String password = "Test123!";

	@Test(dataProvider = "getData")
	public void scheduleMeeting(HashMap<String, String> input)
			throws InterruptedException, UnsupportedFlavorException, IOException {

		// Test for successful schedule meeting

		calendarPage calendarPage = loginPage.loginApplication(email, password);

		// Invoking booking page
		calendarPage.goToCalendarPage();
		calendarPage.goToCalendarSetings();
		bookingPage bookingPage = calendarPage.getSchedulingLink();

		// selecting calendar for which user need to book meeting
		schedulingPage schedulingPage = bookingPage.bookMeeting(input.get("calendarName"));

		// setting time/date details of meeting
		detailsPage detailsPage = schedulingPage.scheduleMeeting(input.get("timeZone"), input.get("date"),
				input.get("timeSlot"));

		// inputing customer details for meeting
		successPage successPage = detailsPage.scheduleMeeting(input.get("fName"), input.get("lName"),
				input.get("phoneNumber"), input.get("userEmail"));

		// checking for success msg of successful scheduling of meeting
		Assert.assertEquals(successPage.getConfMsg(), "Your Meeting has been Scheduled");

	}

	@Test(dependsOnMethods = { "scheduleMeeting" })
	public void priorityCheck() {

		// Test for checking that engineer with highest priority is appointed for meet

		calendarPage calendarPage = loginPage.loginApplication(email, password);
		calendarPage.goToAppointments();

		// getting the appointment owner
		Assert.assertEquals(calendarPage.getAppointmentOwner(), "Engineer Two");

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")
				+ "\\src\\test\\java\\arihantHighlevelInterview\\data\\BookMeeting.json");
		return new Object[][] { { data.get(0) } };
	}

}
