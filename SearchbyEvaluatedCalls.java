package qmpro;

import qmpro.common;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.aventstack.extentreports.Status;

public class SearchbyEvaluatedCalls extends common {

	public SearchbyEvaluatedCalls() throws ParseException {
		super();
		// TODO Auto-generated constructor stub
	}

	// test for EvaluatedCalls
	@Test
	public void executetest() throws IOException {

		ChromeOptions options = new ChromeOptions();

		String node = prop.getProperty("app.url");
		String user = prop.getProperty("app.user1");
		String pwd = prop.getProperty("app.pwd1");

		extent.attachReporter(reporter);

		new HashMap<String, Object>();

		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		options.addExtensions(new File(
				"C:\\Users\\7053516\\eclipse-workspace\\Test1\\Agent-Desktop-Chrome-Extension-Chrome Web Store_v1.16.crx"));

		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
		Actions action = new Actions(driver);

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		try {

			login(new ChromeOptions(), node, user, pwd, driver, action);

			// driver.findElement(By.xpath("//*[@id='tab-home']/a")).click();
			Thread.sleep(1000);

			logger = extent.createTest("Search Evaluated Calls");

			common_searchby("F17", "Evaluated Calls", action, driver);

			driver.findElement(By.xpath("//div[contains(@class, 'ui-menu-item-wrapper') and text()='after']")).click();
			Thread.sleep(1000);
			
			driver.findElement(By.xpath("//*[@class='datepicker hasDatepicker']"))
			.sendKeys(Keys.chord(Keys.CONTROL, "a"));

			driver.findElement(By.xpath("//*[@class='datepicker hasDatepicker']")).sendKeys(prop.getProperty("SearchDate"));
			Thread.sleep(1000);

			driver.findElement(By.xpath("//*[@id='condition-modal']/div/div/div/div[2]/div/div[1]/input[2]"))
					.sendKeys(prop.getProperty("SearchTime"));
			Thread.sleep(1000);

			action.doubleClick(driver.findElement(By.xpath("//*[@id='condition-modal']/div/div/div/div[3]/img[1]")))
					.perform();

			
			// drop down menu
			
			driver.findElement(By.xpath("//*[@class='link-btn add-condition trn']")).click();

			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".ui-autocomplete-input")).click();

			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".condition-type > .ui-autocomplete-input")).sendKeys("evaluated");
			Thread.sleep(1000);

			driver.findElement(By.xpath("//div[contains(@class, 'ui-menu-item-wrapper') and text()='evaluated']")).click();
			Thread.sleep(1000);
			
			driver.findElement(By.xpath("//span[@class='ui-selectmenu-icon ui-icon ui-icon-triangle-1-s']"))
				   .click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[@class='ui-menu-item-wrapper' and text()='last month']"))
					.click();

			action.doubleClick(driver.findElement(By.xpath("//*[@id='condition-modal']/div/div/div/div[3]/img[1]")))
					.perform();

			Alter_table(action, driver);

			logger.log(Status.PASS, "Columns adjusted successfully");

			// validation
			int total_char_table_length = driver.findElement(By.xpath("//*[@class='title-counter']")).getText()
					.length();
			int table_length = Integer.parseInt(driver.findElement(By.xpath("//*[@class='title-counter']")).getText()
					.substring(1, total_char_table_length - 1));

			if (table_length > 0) {
				logger.log(Status.PASS, "Validation Passed");
			} else {
				logger.log(Status.FAIL, "Validation Fail");
			}

			save(driver);
			// close_search(search_by, "EvaluatedCalls" , action, driver);
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Status.FAIL, "Search by EvaluatedCalls Test Failed");
			System.out.print("Something went wrong");
			screen_shot(driver);
		}

	}

}