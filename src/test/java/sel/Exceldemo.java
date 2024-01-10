package sel;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class Exceldemo {
	@Test
	public void readwriteexcel() throws Exception{
		FileInputStream fin=new FileInputStream("C:\\Users\\yokesh\\OneDrive\\Documents\\yokesh.xlsx");
		//create workbook
		XSSFWorkbook workbook=new XSSFWorkbook(fin);
		//get sheet from excel
		XSSFSheet sheet=workbook.getSheetAt(0);
		//get a row from excel
		XSSFRow row=sheet.getRow(0);
		//get a cell
		Cell cell=row.getCell(0);
		System.out.println(sheet.getRow(0).getCell(0));
		//XFFS Row row1=sheet.getRow(1);
		//Cell cell=row1.getCell(1);
		System.out.println(sheet.getRow(0).getCell(1));
		System.out.println("Excel read done!");
		
		//write excel
		//sheet.createRow(10);
		
		row=sheet.getRow(0);
		row.createCell(2).setCellValue("Data3");
		fin.close();
		FileOutputStream fout=new FileOutputStream("C:\\Users\\yokesh\\OneDrive\\Documents\\yokesh.xlsx");
		workbook.write(fout);
		fout.close();
		System.out.println("Excel is written successfully");
		
		}
	}
