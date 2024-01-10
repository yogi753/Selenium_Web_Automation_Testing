package sel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoProject {
	WebDriver driver;
	WebDriverWait wait;
	Row row;
	static ExtentTest test;
	static ExtentReports report;
	
	
	@BeforeTest
	
		public void openBrowser() throws Exception {
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();
		driver.get("https://practicetestautomation.com/practice-test-login/");
		driver.manage().window().maximize();
		
		
		
	}
	
	@Test
	public void test() throws Exception {
		wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		report = new ExtentReports(("./src/test/resources/Report")+ ("ExtentReportResults.html"));
		
		FileInputStream fin= new FileInputStream("C:\\Users\\yokesh\\Downloads\\yokesh.xlsx");
		//Create workbook
		XSSFWorkbook workbook = new XSSFWorkbook(fin);
		// get sheet from excel
		XSSFSheet sheet = workbook.getSheetAt(0);
			
		for(int i=1; i<sheet.getLastRowNum()+1;i++) {
			//get cell from excel for username
			test = report.startTest("ExtentDemo");
			String un = sheet.getRow(i).getCell(0).toString();
			test.log(LogStatus.INFO,"Username:" + un);
			System.out.println(i + "\t" + "UserName" +"\t" +un);
			
			//wait till user input box loads
			WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
			//send username values to the web from excel sheet
			username.sendKeys(un);
			
			//get cell from excel for password
			String pw = sheet.getRow(i).getCell(1).toString();
			test.log(LogStatus.INFO, "Password:" +"\t" + pw);
			//wait till password input box loads
			WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
			//send values to the web from excel sheet
			password.sendKeys(pw);
			System.out.println("\t" + pw + "\n");
			
			//click on submit button
			WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
			submit.click();
		 
			//verify whether is logged in properly
			try {
			 	 if(driver.getTitle().equalsIgnoreCase("Logged In Successfully | Practice Test Automation")) {
					System.out.println("Valid Credentials");
					row = sheet.getRow(i);
				    row.createCell(2).setCellValue("Valid Credentials");
				    test.log(LogStatus.PASS, "Status:"+"\t"+"Logged in with correct credentials");
					driver.navigate().back();
					username.clear();					
					System.out.println("logged out");
									
				}
			 	 else
			 	 {
			 		 //Taking screenshot for failed items
			        TakesScreenshot scrShot =((TakesScreenshot)driver);

			        //Call getScreenshotAs method to create image file

			                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

			            //Move image file to new destination

			                File DestFile=new File("./src/test/resources/Screenshot"+i+".png");

			                //Copy file at destination

			         FileUtils.copyFile(SrcFile, DestFile);
					System.out.println("InValid Credentials");
					username.clear();
					row = sheet.getRow(i);
				    row.createCell(2).setCellValue("Invalid Credentials");
				    test.log(LogStatus.FAIL, "Status:"+"\t"+"Test Failed");
			 	 }
				}
			 catch(Exception e){
				System.out.println("exception"); 
			 }
		}
			
		 	fin.close();
		    FileOutputStream fout = new FileOutputStream("C:\\Users\\yokesh\\Downloads\\yokesh.xlsx");
		    workbook.write(fout);
		    fout.close();
			System.out.println("Excel is written successfully");
	}
	
	@AfterTest
	public void closebrowser() {
		driver.close();
		report.endTest(test);
		report.flush();
		
	}
}
