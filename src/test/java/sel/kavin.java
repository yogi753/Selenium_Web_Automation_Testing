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

public class kavin {
WebDriver driver;
WebDriverWait wait;
Row row;
static ExtentTest test;
static ExtentReports report;


@BeforeTest

public void openBrowser() throws Exception {
WebDriverManager.edgedriver().setup();
driver=new EdgeDriver();
driver.get("https://infyspringboard.onwingspan.com/auth/realms/infyspringboard/protocol/openid-connect/auth?client_id=portal&redirect_uri=https%3A%2F%2Finfyspringboard.onwingspan.com%2Fweb%2Fen%2F&state=099db6eb-baea-44a8-8182-345838292068&response_mode=fragment&response_type=code&scope=openid&nonce=58dda90b-df5b-41d4-8907-5b6c05679cd4");
driver.manage().window().maximize();



}

@Test
public void test() throws Exception {
wait=new WebDriverWait(driver, Duration.ofSeconds(20));
report = new ExtentReports(("./src/test/resources/Report")+ ("ExtentReportResults.html"));


FileInputStream fin= new FileInputStream("C:\\Users\\yokesh\\Downloads\\kavin.xlsx");
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
driver.findElement(By.xpath("//*[@id=\"kc-login\"]")).click();
Thread.sleep(3000);

//verify whether is logged in properly
try {

if(driver.getTitle().equalsIgnoreCase("Home | Infosys Springboard")) {
System.out.println("Valid Credentials");
row = sheet.getRow(i);
   row.createCell(2).setCellValue("Valid Credentials");
   test.log(LogStatus.PASS, "Status:"+"\t"+"Logged in with correct credentials");
       Thread.sleep(2000);
   driver.findElement(By.xpath("//*[@id=\"home-menu-more\"]")).click();
//driver.navigate().back();
   Thread.sleep(5000);
   //WebElement home_logout = driver.findElement(By.xpath("(//span[@class=\"mat-button-focus-overlay\"])[1]"));
   //home_logout.click();
   System.out.println("print logout");
   Thread.sleep(500);
   WebElement logoutclick=driver.findElement(By.xpath("//span[contains(text(),\"Logout\")]"));
   logoutclick.click();
   
   WebElement yesclick=driver.findElement(By.xpath("//span[contains(text(),\" Yes\")]"));
   yesclick.click();
   Thread.sleep(5000);
   WebElement login=driver.findElement(By.xpath("//span[contains(text(),\"LOGIN\")]"));
   login.click();
   
   

  //driver.get("https://infyspringboard.onwingspan.com/auth/realms/infyspringboard/protocol/openid-connect/auth?client_id=portal&redirect_uri=https%3A%2F%2Finfyspringboard.onwingspan.com%2Fweb%2Fen%2F&state=099db6eb-baea-44a8-8182-345838292068&response_mode=fragment&response_type=code&scope=openid&nonce=58dda90b-df5b-41d4-8907-5b6c05679cd4");
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

	row = sheet.getRow(i);
   row.createCell(2).setCellValue("Invalid Credentials");
   test.log(LogStatus.FAIL, "Status:"+"\t"+"Test Failed");
   driver.navigate().refresh();
   username.clear();
}
}
catch(Exception e){
System.out.println("exception");
}
}

fin.close();
   FileOutputStream fout = new FileOutputStream("C:\\Users\\yokesh\\Downloads\\kavin.xlsx");
   workbook.write(fout);
   fout.close();
   workbook.close();
System.out.println("Excel is written successfully");
}

@AfterTest
 public void closebrowser() {
driver.close(); report.endTest(test);
 report.flush();

 }
}