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

public class SearchbyObserver extends common {

	public SearchbyObserver() throws ParseException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void executetest() throws IOException, InterruptedException {

		ChromeOptions options = new ChromeOptions();

		String node = prop.getProperty("app.url");
		String user = prop.getProperty("app.user3");
		String pwd = prop.getProperty("app.pwd3");

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

			logger = extent.createTest("Search by Observer");

			common_searchby("F8", "Observer", action, driver);

			driver.findElement(By.xpath("//div[contains(@class, 'ui-menu-item-wrapper') and text()='last month']"))
					.click();

			action.doubleClick(driver.findElement(By.xpath("//*[@id='condition-modal']/div/div/div/div[3]/img[1]")))
					.perform();

			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@class='link-btn add-condition trn']")).click();

			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".ui-autocomplete-input")).click();

			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".condition-type > .ui-autocomplete-input")).sendKeys("evaluated by");
			Thread.sleep(1000);

			driver.findElement(By.cssSelector(".condition-type > .ui-autocomplete-input")).sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".condition-type > .ui-autocomplete-input")).sendKeys(Keys.ENTER);

			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".condition-additional .label-input")).sendKeys(prop.getProperty("Monitored&Observedby"));
			Thread.sleep(3000);

			driver.findElement(By.cssSelector(".condition-additional .label-input")).sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(1000);

			driver.findElement(By.cssSelector(".condition-additional .label-input")).sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(1000);

			driver.findElement(By.cssSelector(".condition-additional .label-input")).sendKeys(Keys.ENTER);

			driver.findElement(By.xpath("//*[@id='condition-modal']/div/div/div/div[3]/img[1]")).click();

			Alter_table(action, driver);

			logger.log(Status.PASS, "Columns adjusted successfully");

			// validation

			int total_char_table_length = driver.findElement(By.xpath("//*[@class='title-counter']")).getText()
					.length();
			int table_length = Integer.parseInt(driver.findElement(By.xpath("//*[@class='title-counter']")).getText()
					.substring(1, total_char_table_length - 1));

			System.out.print("Details of Table Columns " + " " + read_grid_header() + '\n');
			logger.log(Status.INFO, "Details of column " + " " + read_grid_header());
			
			for (int i = 1; i <= table_length; i++) {
				String tab_data = driver
						.findElement(By
								.xpath("//*/div/div[4]/div/div/div[1]/div/div[3]/div[2]/div/div/div[" + i + "]/div[1]"))
						.getText().trim();
				
				String tab_date = driver
						.findElement(By.xpath(
								"//*/div/div[4]/div/div/div[1]/div/div[3]/div[2]/div/div/div[" + i + "]/div[12]"))
						.getText().trim();

				if (tab_data.contains(prop.getProperty("Monitored&Observedby")) && date_compare(tab_date, firstDateOfPreviousMonth) == true) {
					System.out.print("Verified column " + i + " " + read_grid_data(driver, i) + '\n');
					logger.log(Status.INFO, "Verified column " + i + " " + read_grid_data(driver, i));

					if (i == table_length) {
						logger.log(Status.PASS, "Validation of Observer Test verified");
						logger.log(Status.PASS, "Search by observer Test verified");
					}
					if (i == 13) {
						logger.log(Status.PASS, "Search by Observer Test verified");
						save(driver);
						// close_search(search_by, "Agent", action, driver);
						return;
					}
				} else {
					logger.log(Status.FAIL, "Validation of Observer failed because of " + read_grid_data(driver, i) + " in column " + i);
					System.out.print("Not verified column " + i + " " + read_grid_data(driver, i) + '\n');
					logger.log(Status.INFO, "Not verified column " + i + " " + read_grid_data(driver, i));

					save(driver);
					// close_search(search_by, "Call Duration", action, driver);
					return;
				}

			}

			save(driver);
			// close_search(search_by, "Observer", action, driver);

		} catch (Exception e) {
			// e.printStackTrace();
			logger.log(Status.FAIL, "Search by Observer Test Failed");
			System.out.print("Something went wrong");
			screen_shot(driver);
		}

	}

}