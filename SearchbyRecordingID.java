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

public class SearchbyRecordingID extends common {

	public SearchbyRecordingID() throws ParseException {
		super();
		// TODO Auto-generated constructor stub
	}

	// test for recording
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

			logger = extent.createTest("Search by Recording ID");

			common_searchby("F9", "Recording", action, driver);

			driver.findElement(By.xpath("//div[contains(@class, 'ui-menu-item-wrapper') and text()='this month']"))
					.click();

			action.doubleClick(driver.findElement(By.xpath("//*[@id='condition-modal']/div/div/div/div[3]/img[1]")))
					.perform();

			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@class='link-btn add-condition trn']")).click();

			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".ui-autocomplete-input")).click();

			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".condition-type > .ui-autocomplete-input"))
					.sendKeys("global Interaction ID");
			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".condition-type > .ui-autocomplete-input")).sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".condition-type > .ui-autocomplete-input")).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"condition-modal\"]/div/div/div/div[2]/div/input"))
					.sendKeys(prop.getProperty("SearchbyRecordingID1"));
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"condition-modal\"]/div/div/div/div[3]/img[1]")).click();

			// second condition check

			driver.findElement(By.xpath("//*[@class='link-btn add-condition trn']")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".ui-autocomplete-input")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".condition-type > .ui-autocomplete-input"))
					.sendKeys("global Interaction ID");
			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".condition-type > .ui-autocomplete-input")).sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".condition-type > .ui-autocomplete-input")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"condition-modal\"]/div/div/div/div[2]/div/input"))
					.sendKeys(prop.getProperty("SearchbyRecordingID2"));
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"condition-modal\"]/div/div/div/div[3]/img[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*/div/div[2]/div/div[3]/div[1]/div[4]/div[1]/div[1]/div[4]/div/div[1]"))
					.click();
			Thread.sleep(1000);

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
			// close_search(search_by, "Recording", action, driver);

		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Status.FAIL, "Search by Recording ID Test Failed");
			System.out.print("Something went wrong");
			screen_shot(driver);
		}

	}

}