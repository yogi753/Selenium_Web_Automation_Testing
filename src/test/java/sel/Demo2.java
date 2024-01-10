package sel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Demo2 {
	WebDriver driver;
	@BeforeTest
	public void openBrowser() {
		
		System.setProperty("webdriver.Chrome.driver","C:\\Users\\yokesh\\Downloads\\msedgedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
	}
@Test
public void test() throws Exception{ 
	//Verify Title
	String verifyTitle = driver.getTitle();
	System.out.println(driver.getTitle());
	if(verifyTitle.equalsIgnoreCase("Google"))
		System.out.println("Title is matching");
	else
		System.out.println("Title is not matched");
//     System.out.println(driver.getPageSource());
	
     WebElement google_textbox=driver.findElement(By.name("q"));
     google_textbox.sendKeys("Kongu Engineering College");
     google_textbox.sendKeys(Keys.ENTER);
     
     //here we need to paste the xpath link for clicking the particular link tags.
     driver.findElement(By.xpath("//*[@id=\"kp-wp-tab-overview\"]/div[1]/div/div/div/div/div/div/div/div/div/div[1]/a/h3")).click();
     
     //click button on modal dialog.
     driver.findElement(By.xpath("//*[@id=\"KecAdmission\"]/div/div/div[3]/a")).click();
     // above first method is it doesn't works then (or)try the below method is alter way for clicking the modal dialogs.
     
     //Thread.sleep(2000);
     //WebElement modalcontainer = driver.findElement(By.className("modal-content")); //give only class name of that dialog box not xpath here.
     //modalcontainer.findElement(By.linkText("More Details")).click();
     
     driver.navigate().to("https://www.w3schools.com/");
     }
@AfterTest
public void closeBrowser() {
	//close() closes current window
	driver.close();
	//quit() closes all windows
	driver.quit();
	}

}
