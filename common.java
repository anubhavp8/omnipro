package qmpro;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Properties;
import freemarker.core.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class common {

	public String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

	public ExtentHtmlReporter reporter = new ExtentHtmlReporter("./Reports/Test_report" + timeStamp + ".html");
	// Create object of ExtentReports class- This is main class which will create
	// report
	public static ExtentReports extent = new ExtentReports();
	// attach the reporter which we created in Step 1
	public Properties prop = new Properties();
	public ExtentTest logger;
	Date date = Calendar.getInstance().getTime();
	DateFormat dateFormat = new SimpleDateFormat("dd/mm/yy HH:mm");
	String strDate = dateFormat.format(date);
	String searchDate;
	Calendar aCalendar = Calendar.getInstance();
	Date firstDateOfPreviousMonth;
	Date firstDayOfTheWeek;
	
	public common() throws java.text.ParseException {
		// constructor definition to read the file
		try {
			String fileName = "app.config";
			ClassLoader classLoader = common.class.getClassLoader();

			// Make sure that the configuration file exists
			URL res = Objects.requireNonNull(classLoader.getResource(fileName),
					"Can't find configuration file app.config");
			InputStream is = new FileInputStream(res.getFile());
			// load the properties file
			prop.load(is);

			searchDate = prop.getProperty("SearchDate") + prop.getProperty("SearchYear") + " "
					+ prop.getProperty("SearchTime");

			aCalendar.set(Calendar.DATE, 1);
			firstDateOfPreviousMonth = aCalendar.getTime();

			aCalendar.set(Calendar.DAY_OF_WEEK, aCalendar.getActualMinimum(Calendar.DAY_OF_WEEK));
			firstDayOfTheWeek = aCalendar.getTime();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.print(1);
		}
	}

	public void close_search(String search_by, String test_detail, Actions action, WebDriver driver)
			throws IOException {

		try {
			Thread.sleep(2000);

			action.moveToElement(driver.findElement(By.linkText(search_by))).perform();

			Thread.sleep(1000);
			// closing the search tab
			driver.findElement(By.xpath("//button[@class='close' and @type='button' and text()='×']")).click();

			// closing the search frame2
			Thread.sleep(1000);
			driver.findElement(By.partialLinkText("Search by " + test_detail)).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#\\39 C7F0D8D-00DA-4AB0-B9A7-472E6148602A > .jstree-icon")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#personal_searches > .jstree-icon")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("personal_searches_anchor")).click();
		} catch (Exception e) {
			e.printStackTrace();
			extent.flush();
			System.out.print("Something went wrong");
			screen_shot(driver);
		}
	}

	public String read_grid_header() {
		return "Agent" + " " + "Date/Time" + " " + "Duration" + " " + "Disposition" + " " + "Recording" + " " + "Score"
				+ " " + "Status" + " " + "Evaluator" + " " + "Scenario" + " " + "Team" + " " + "Service";

	}

	public String read_grid_data(WebDriver driver, int i) {
		return driver
				.findElement(By.xpath("//*/div/div[4]/div/div/div[1]/div/div[3]/div[2]/div/div/div[" + i + "]/div[11]"))
				.getText()
				+ " "
				+ driver.findElement(
						By.xpath("//*/div/div[4]/div/div/div[1]/div/div[3]/div[2]/div/div/div[" + i + "]/div[12]"))
						.getText()
				+ " "
				+ driver.findElement(
						By.xpath("//*/div/div[4]/div/div/div[1]/div/div[3]/div[2]/div/div/div[" + i + "]/div[6]"))
						.getText()
				+ " "
				+ driver.findElement(
						By.xpath("//*/div/div[4]/div/div/div[1]/div/div[3]/div[2]/div/div/div[" + i + "]/div[4]"))
						.getText()
				+ " "
				+ driver.findElement(
						By.xpath("//*/div/div[4]/div/div/div[1]/div/div[3]/div[2]/div/div/div[" + i + "]/div[8]"))
						.getText()
				+ " "
				+ driver.findElement(
						By.xpath("//*/div/div[4]/div/div/div[1]/div/div[3]/div[2]/div/div/div[" + i + "]/div[3]"))
						.getText()
				+ " "
				+ driver.findElement(
						By.xpath("//*/div/div[4]/div/div/div[1]/div/div[3]/div[2]/div/div/div[" + i + "]/div[2]"))
						.getText()
				+ " "
				+ driver.findElement(
						By.xpath("//*/div/div[4]/div/div/div[1]/div/div[3]/div[2]/div/div/div[" + i + "]/div[5]"))
						.getText()
				+ " "
				+ driver.findElement(
						By.xpath("//*/div/div[4]/div/div/div[1]/div/div[3]/div[2]/div/div/div[" + i + "]/div[1]"))
						.getText()
				+ " "
				+ driver.findElement(
						By.xpath("//*/div/div[4]/div/div/div[1]/div/div[3]/div[2]/div/div/div[" + i + "]/div[10]"))
						.getText()
				+ " "
				+ driver.findElement(
						By.xpath("//*/div/div[4]/div/div/div[1]/div/div[3]/div[2]/div/div/div[" + i + "]/div[9]"))
						.getText();
	}

	public String common_searchby(String funct, String test_detail, Actions action, WebDriver driver)
			throws IOException {

		String search_by = funct + "- Search by " + test_detail + timeStamp;

		try {
			// driver.switchTo().frame(2);
			driver.findElement(By.partialLinkText("Search by " + test_detail)).click();

			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='panel-home']/div/div[2]/div/div[3]/div[1]/div[2]/div/img[2]"))
					.click();
			Thread.sleep(2000);

			driver.findElement(By.cssSelector(".node-name")).sendKeys(search_by);
			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".node-name")).sendKeys(Keys.ENTER);
			Thread.sleep(2000);

			action.moveToElement(driver.findElement(By.cssSelector(".add-folder")), -200, 200).click();

			Thread.sleep(2000);
			driver.findElement(By.linkText(search_by)).click();
			Thread.sleep(1000);
			// filter element - started
			driver.findElement(
					By.xpath("//*[@id='panel-home']/div/div[2]/div/div[3]/div[1]/div[4]/div[1]/div[1]/div/div"))
					.click();
			Thread.sleep(1000);

			// drop down element
			driver.findElement(By.xpath("//*[@class='ui-selectmenu-icon ui-icon ui-icon-triangle-1-s']")).click();
			Thread.sleep(2000);

		} catch (Exception e) {
			// e.printStackTrace();
			extent.flush();
			System.out.print("Something went wrong");
			screen_shot(driver);
		}
		return search_by;

	}

	public void Alter_table(Actions action, WebDriver driver) throws IOException {

		try {

			Thread.sleep(2000);

			driver.findElement(By.cssSelector(".search-btn")).click();
			Thread.sleep(6000);

			// check if search collapse image is visible
			// if (driver.findElement(By.className("collapse-condition")).isDisplayed());
			// {System.out.print("22");
			// driver.findElement(By.className("collapse-condition")).click();}
			// Thread.sleep(1000);

			driver.findElement(
					By.xpath("//*/div/div[4]/div/div/div[1]/div/div[1]/div[2]/div/div/div[1]/div[2]/span/span"))
					.click();
			Thread.sleep(1000);

			driver.findElement(By.xpath(
					"//*[@class='ui-button ui-widget ui-checkboxradio-checked ui-state-active ui-checkboxradio-label ui-controlgroup-item' and text()='Sentiment']"))
					.click();
			driver.findElement(By.xpath(
					"//*[@class='ui-button ui-widget ui-checkboxradio-checked ui-state-active ui-checkboxradio-label ui-controlgroup-item' and text()='CSAT']"))
					.click();
			driver.findElement(By.xpath(
					"//*[@class='ui-button ui-widget ui-checkboxradio-checked ui-state-active ui-checkboxradio-label ui-controlgroup-item' and text()='Customer']"))
					.click();
			driver.findElement(By.xpath(
					"//*[@class='ui-button ui-widget ui-checkboxradio-checked ui-state-active ui-checkboxradio-label ui-controlgroup-item' and text()='NPS']"))
					.click();
			driver.findElement(By.xpath(
					"//*[@class='ui-button ui-widget ui-checkboxradio-label ui-controlgroup-item' and text()='Scenario']"))
					.click();
			driver.findElement(By.xpath(
					"//*[@class='ui-button ui-widget ui-checkboxradio-label ui-controlgroup-item' and text()='Duration']"))
					.click();
			driver.findElement(By.xpath(
					"//*[@class='ui-button ui-widget ui-checkboxradio-label ui-controlgroup-item' and text()='Disposition']"))
					.click();
			driver.findElement(By.xpath(
					"//*[@class='ui-button ui-widget ui-checkboxradio-label ui-controlgroup-item' and text()='Recording']"))
					.click();
			driver.findElement(By.xpath(
					"//*[@class='ui-button ui-widget ui-checkboxradio-label ui-controlgroup-item' and text()='Score']"))
					.click();
			driver.findElement(By.xpath(
					"//*[@class='ui-button ui-widget ui-checkboxradio-label ui-controlgroup-item' and text()='Evaluator']"))
					.click();

			// drop down element close
			driver.findElement(By.xpath("//div[starts-with(@id, 'panel-search')]/div")).click();
			Thread.sleep(1000);

			Thread.sleep(1000);
			// Element which needs to drag.
			WebElement From = driver.findElement(
					By.xpath("//*/div/div[4]/div/div/div[1]/div/div[1]/div[2]/div/div/div[2]/div[2]/div/span[1]"));
			// Element on which need to drop.
			WebElement To = driver.findElement(
					By.xpath("//*/div/div[4]/div/div/div[1]/div/div[1]/div[2]/div/div/div[12]/div[2]/div/span[1]"));
			// Dragged and dropped.
			action.clickAndHold(From).moveToElement(To).release().build().perform();

			// Element which needs to drag.
			From = driver.findElement(
					By.xpath("//*/div/div[4]/div/div/div[1]/div/div[1]/div[2]/div/div/div[3]/div[2]/div/span[1]"));
			// Element on which need to drop.
			To = driver.findElement(
					By.xpath("//*/div/div[4]/div/div/div[1]/div/div[1]/div[2]/div/div/div[12]/div[2]/div/span[1]"));
			// Dragged and dropped.
			action.clickAndHold(From).moveToElement(To).release().build().perform();

			// Element which needs to drag.
			From = driver.findElement(
					By.xpath("//*/div/div[4]/div/div/div[1]/div/div[1]/div[2]/div/div/div[6]/div[2]/div/span[1]"));
			// Element on which need to drop.
			To = driver.findElement(
					By.xpath("//*/div/div[4]/div/div/div[1]/div/div[1]/div[2]/div/div/div[12]/div[2]/div/span[1]"));
			// Dragged and dropped.
			action.clickAndHold(From).moveToElement(To).release().build().perform();
			Thread.sleep(1000);

		} catch (Exception e) {
			e.printStackTrace();
			extent.flush();
			System.out.print("Something went wrong");
			screen_shot(driver);
		}
	}

	public void save(WebDriver driver) throws IOException {

		try {
			Thread.sleep(1000);
			// minimize and display search
			driver.findElement(By.className("extend-condition")).click();

			driver.findElement(By.xpath("//*[text()='apply as default layout']")).click();
			Thread.sleep(1000);

			driver.findElement(By.xpath("//*[@class='save-btn link-btn trn']")).click();

			screen_shot(driver);

			// quit_function(driver);
		} catch (Exception e) {
			e.printStackTrace();
			extent.flush();
			System.out.print("Something went wrong");
			screen_shot(driver);
		}
	}

	public void log_out(WebDriver driver) throws IOException {

		try {
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.findElement(By.cssSelector(".b-icon-power-off")).click();
			Thread.sleep(1000);

			driver.findElement(By.id("gwt-debug-cdbOk")).click();
			Thread.sleep(6000);

			quit_function(driver);

		} catch (Exception e) {
			e.printStackTrace();
			extent.flush();
			System.out.print("Something went wrong");
		}
	}

	public void login(final ChromeOptions options, String node, String user, String pwd, WebDriver driver,
			Actions action) throws IOException {

		try {
			driver.get(node);
			Thread.sleep(2000);

			driver.findElement(By.name("username")).sendKeys(user);
			driver.findElement(By.name("password")).sendKeys(pwd);
			driver.findElement(By.id("auth-submit")).click();

			Thread.sleep(2000);

			// Resize current window to the set dimension
			driver.manage().window().maximize();
			Thread.sleep(2000);

			// ***************************extra code for
			// docker**********************************//
			while (driver.findElements(By.id("gwt-debug-cdbOk")).size() == 0)
				Thread.sleep(1000);

			driver.findElement(By.id("gwt-debug-cdbOk")).click();
			// ***************************extra code for
			// docker**********************************//
			Thread.sleep(1000);
			// clicking on quality management link
			driver.findElement(By.cssSelector("#b-navigation-item-quality-management > div.b-svg")).click();

			Thread.sleep(4000);
			driver.switchTo().frame(2);
			driver.findElement(By.id("personal_searches_anchor")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#personal_searches > .jstree-icon")).click();
			Thread.sleep(1000);
			action.doubleClick(driver.findElement(By.partialLinkText("Automation"))).perform();
			Thread.sleep(1000);

		} catch (Exception e) {
			e.printStackTrace();
			extent.flush();
			System.out.print("Something went wrong");
		}
	}

	public boolean isTimeWith_in_Interval(String valueToCheck, String startTime, String endTime)
			throws java.text.ParseException, ParseException {
		boolean isBetween = false;
		Date time1 = new SimpleDateFormat("HH:mm").parse(startTime);

		Date time2 = new SimpleDateFormat("HH:mm").parse(endTime);

		Date d = new SimpleDateFormat("HH:mm").parse(valueToCheck);

		if (time1.before(d) && time2.after(d)) {
			isBetween = true;
		}
		return isBetween;
	}

	public boolean date_compare(String startTime, String endtime) throws java.text.ParseException {
		boolean isBetween = false;
		Date date1 = new SimpleDateFormat("dd/mm/yy HH:mm").parse(startTime);
		Date date2 = new SimpleDateFormat("dd/mm/yy HH:mm").parse(endtime);

		// Date date2 = date;

		if (date2.compareTo(date1) >= 0) {
			isBetween = true;
		}
		return isBetween;

	}

	public boolean date_compare(String startTime, Date date2) throws java.text.ParseException {
		boolean isBetween = false;
		Date date1 = new SimpleDateFormat("dd/mm/yy HH:mm").parse(startTime);

		// Date date2 = date;

		if (date2.compareTo(date1) >= 0) {
			isBetween = true;
		}
		return isBetween;

	}
	


	public void screen_shot(WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File("C:\\Users\\7053516\\eclipse-workspace\\Test1\\Screenshots\\Screen.png"));

		log_out(driver);
	}

	public void quit_function(WebDriver driver) {

		driver.quit();

	}

}
