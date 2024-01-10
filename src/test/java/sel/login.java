package sel;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class login {
	 WebDriver driver;
	 WebDriverWait wait;
	 Row row;
	 static ExtentTest test;
	 static ExtentReports report;

	    @BeforeTest

	    public void openBrowser() {

	     System.setProperty("webdriver.Chrome.driver","C:\\Users\\yokesh\\Downloads\\msedgedriver.exe");

	     driver=new ChromeDriver();

	     driver.get("C:\\Users\\yokesh\\Downloads\\login.html");

	     driver.manage().window().maximize();

	    }

	    @Test

	    public void test() throws Exception

	    {

	    report = new ExtentReports(("./src/test/resources/Report")+ ("ExtentReportResults.html"));
	    test = report.startTest("ExtentDemo");
	    
	     WebElement textbox1=driver.findElement(By.name("uname"));

	     textbox1.sendKeys("yokesh");
	     
	     
	     
	     test.log(LogStatus.INFO, "username:" +"\t" + "yokesh");

	      WebElement textbox2=driver.findElement(By.name("psw"));

	     textbox2.sendKeys("hare200");
	     

	     test.log(LogStatus.INFO, "Password:" +"\t" + "hare200");

	     driver.findElement(By.xpath("/html/body/form/div[2]/button")).click();

	     test.log(LogStatus.PASS,  "Logged in successfully");

	     WebElement textbox3=driver.findElement(By.name("RESULT_TextField-1"));

	     textbox3.sendKeys("Yokesh");
	     String qwe3=textbox3.getText();
	     test.log(LogStatus.INFO, "fname:" +"\t" + "Yokesh");

	     

	     WebElement textbox4=driver.findElement(By.name("RESULT_TextField-2"));

	     textbox4.sendKeys("Ravi");
	     String qwe4=textbox4.getText();
	     test.log(LogStatus.INFO, "lname:" +"\t" + "Ravi");
	     

	     WebElement textbox5=driver.findElement(By.name("RESULT_TextField-3"));

	     textbox5.sendKeys("9750353556");
	     String qwe5=textbox4.getText();
	     test.log(LogStatus.INFO, "phone:" +"\t" + "9750353556");

	     

	     WebElement textbox6=driver.findElement(By.name("RESULT_TextField-4"));

	     textbox6.sendKeys("India");
	     String qwe6=textbox6.getText();
	     test.log(LogStatus.INFO, "country:" +"\t" + "India");
	     

	     WebElement textbox7=driver.findElement(By.name("RESULT_TextField-5"));

	     textbox7.sendKeys("Erode");
	     String qwe7=textbox7.getText();
	     test.log(LogStatus.INFO, "state:" +"\t" + "Erode");

	     

	     WebElement textbox8=driver.findElement(By.name("RESULT_TextField-6"));

	     textbox8.sendKeys("yokeshr.20msc@kongu.edu");
	     String qwe8=textbox8.getText();
	     
	     test.log(LogStatus.INFO, "email:" +"\t" + "yokeshr.20msc@kongu.edu");

	          

	  

	    WebElement radio1 = driver.findElement(By.xpath("/html/body/form/div[2]/div[15]/table/tbody/tr[1]/td/label"));

	   radio1.click();

	   

	   

	   WebElement checkbox = driver.findElement(By.xpath("//*[@id=\"q15\"]/table/tbody/tr[2]/td/label"));

	   checkbox.click();

	   

	   WebElement checkbox1 = driver.findElement(By.xpath("//*[@id=\"q15\"]/table/tbody/tr[3]/td/label"));

	   checkbox1.click();

	   

	   WebElement list=driver.findElement(By.id("RESULT_RadioButton-9"));

	   Select select = new Select(list);

	   select.selectByIndex(2);

	   
		 //Taking screenshot for failed items
	       TakesScreenshot scrShot =((TakesScreenshot)driver);

	       //Call getScreenshotAs method to create image file

	               File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

	           //Move image file to new destination

	               File DestFile=new File("./src/test/resources/Screenshot"+1+".png");

	               //Copy file at destination

	        FileUtils.copyFile(SrcFile, DestFile);

	   driver.findElement(By.id("FSsubmit")).click();
	   test.log(LogStatus.PASS, "Logged out successfully");
	 

	    }

	     @AfterTest

	     public void closeBrowser()

	     {

	     driver.close();
	     report.endTest(test);
		 report.flush();

	     }

	}


