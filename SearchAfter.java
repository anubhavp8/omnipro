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

public class SearchAfter extends common {


	public SearchAfter() throws ParseException {
		super();
		// TODO Auto-generated constructor stub
	}

	// test for After
	@Test
	public void executetest() throws IOException {

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

			logger = extent.createTest("Search by After");

			common_searchby("F13", "After", action, driver);

			driver.findElement(By.xpath("//div[contains(@class, 'ui-menu-item-wrapper') and text()='after']")).click();
			Thread.sleep(1000);

			driver.findElement(By.xpath("//*[@class='datepicker hasDatepicker']"))
			.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			Thread.sleep(1000);
			
			driver.findElement(By.xpath("//*[@class='datepicker hasDatepicker']")).sendKeys(prop.getProperty("SearchDate"));
			Thread.sleep(1000);

			driver.findElement(By.xpath("//*[@id='condition-modal']/div/div/div/div[2]/div/div[1]/input[2]"))
					.sendKeys(prop.getProperty("SearchTime"));
			Thread.sleep(1000);

			action.doubleClick(driver.findElement(By.xpath("//*[@id='condition-modal']/div/div/div/div[3]/img[1]")))
					.perform();

			Alter_table(action, driver);

			logger.log(Status.PASS, "Columns adjusted successfully");

			// validation
			int total_char_table_length = driver.findElement(By.xpath("//*[@class='title-counter']")).getText()
					.length();
			int table_length = Integer.parseInt(driver.findElement(By.xpath("//*[@class='title-counter']")).getText()
					.substring(1, total_char_table_length - 1));

			System.out.print("Details of Columns " + " " + read_grid_header() + '\n');
			logger.log(Status.INFO, "Details of column " + " " + read_grid_header());
			
			for (int i = 1; i <= table_length; i++) {
				String tab_data = driver
						.findElement(By
								.xpath("//*/div/div[4]/div/div/div[1]/div/div[3]/div[2]/div/div/div[" + i + "]/div[12]"))
						.getText();
				
				if (tab_data != "")
					if (date_compare(searchDate, tab_data) == true) {
						System.out.print("Verified column " + i + " " + read_grid_data(driver, i) + '\n');
						logger.log(Status.INFO, "Verified column " + i + " " + read_grid_data(driver, i));

						if (i == table_length) {
							logger.log(Status.PASS, "Validation of After Test verified");
							logger.log(Status.PASS, "Search by After Test verified");
						}
						if (i == 13) {
							System.out.print("zz " + '\n');
							logger.log(Status.PASS, "Search by After Test verified");
							save(driver);
							// close_search(search_by, "After", action, driver);
							return;
						}
					} else {
						logger.log(Status.FAIL,
								"Validation of After failed because of " + read_grid_data(driver, i) + " in column " + i);
						System.out.print("Not verified column " + i + " " + read_grid_data(driver, i) + '\n');
						logger.log(Status.INFO, "Not verified column " + i + " " + read_grid_data(driver, i));
						save(driver);
						// close_search(search_by, "After", action, driver);
						return;
					}

			}

			save(driver);
			// close_search(search_by, "After" , action, driver);
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Status.FAIL, "Search by After Test Failed");
			System.out.print("Something went wrong");
			screen_shot(driver);
		}

	}

}