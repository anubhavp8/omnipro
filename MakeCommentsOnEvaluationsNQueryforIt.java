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

public class MakeCommentsOnEvaluationsNQueryforIt extends common {

	public MakeCommentsOnEvaluationsNQueryforIt() throws ParseException {
		super();
		// TODO Auto-generated constructor stub
	}

	// test for Make comments on Evaluations & Query for it
	@Test
	public void executetest() throws IOException {

		ChromeOptions options = new ChromeOptions();

		String node = prop.getProperty("app.url");
		String user = prop.getProperty("app.user2");
		String pwd = prop.getProperty("app.pwd2");

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

			logger = extent.createTest("Search by Make comments on Evaluations & Query for it");

			common_searchby("F12", "Make comments on Evaluations & Query for it", action, driver);

			driver.findElement(By.xpath("//div[contains(@class, 'ui-menu-item-wrapper') and text()='last month']"))
					.click();

			action.doubleClick(driver.findElement(By.xpath("//*[@id='condition-modal']/div/div/div/div[3]/img[1]")))
					.perform();

			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@class='link-btn add-condition trn']")).click();

			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".ui-autocomplete-input")).click();

			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".condition-type > .ui-autocomplete-input")).sendKeys("comment contains");
			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".condition-type > .ui-autocomplete-input")).sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".condition-type > .ui-autocomplete-input")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"condition-modal\"]/div/div/div/div[2]/div/input")).sendKeys("achieved");
			Thread.sleep(1000);

			driver.findElement(By.xpath("//*[@id='condition-modal']/div/div/div/div[3]/img[1]")).click();

			Alter_table(action, driver);

			logger.log(Status.PASS, "Columns adjusted successfully");
			// validation

			int total_char_table_length = driver.findElement(By.xpath("//*[@class='title-counter']")).getText()
					.length();
			int table_length = Integer.parseInt(driver.findElement(By.xpath("//*[@class='title-counter']")).getText()
					.substring(1, total_char_table_length - 1));
			System.out.print(table_length);

			if (table_length >= 0) {
				logger.log(Status.PASS, "Validation Passed");
			} else {
				logger.log(Status.FAIL, "Validation Failed");
			}


			save(driver);
			// close_search(search_by, "Make comments on Evaluations & Query for it", action, driver);

		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Status.FAIL, "Search by Make comments on Evaluations & Query for it Test Failed");
			System.out.print("Something went wrong");
			screen_shot(driver);
		}

	}

}