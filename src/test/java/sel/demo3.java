package sel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class demo3 {
	WebDriver driver;
	@BeforeTest
	public void openBrowser() {
		
		System.setProperty("webdriver.Chrome.driver","C:\\Users\\yokesh\\Downloads\\msedgedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://artoftesting.com/samplesiteforselenium");
		driver.manage().window().maximize();
	}
@Test
public void test() throws Exception{ 
	//Using list store the total tags

	 List<WebElement> list=driver.findElements(By.tagName("input"));

	 System.out.println("Number of objects - " + list.size());

	 

	 //Select Check boxes

	 WebElement checkbox = driver.findElement(By.xpath("//*[@id=\"commonWebElements\"]/form[2]/input[1]"));

	 checkbox.click();

	 WebElement Checkbox2 = driver.findElement(By.xpath("(//input[@type=\"checkbox\"])[2]"));

	 Checkbox2.click();

	 

	 // to check number of check boxes checked

	 int Check=0;

	 int unCheck=0;

	List <WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

	

	for(WebElement el : checkboxes ) {

		if(el.isSelected()) 

			Check++;

		else

			unCheck++;

	}

	 System.out.println("Total number of checked items" + Check );

	 System.out.println("Total number of unchecked items" + unCheck );}
@AfterTest
public void closeBrowser() {
	//close() closes current window
//	driver.close();
	//quit() closes all windows
	//driver.quit();
	}


}
