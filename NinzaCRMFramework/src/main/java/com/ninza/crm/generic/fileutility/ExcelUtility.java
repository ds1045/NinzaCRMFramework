package com.ninza.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	public String getDataFromExcel(String sheetName, int rowNum, int cellNum) throws Throwable {

		FileInputStream fis = new FileInputStream("./src/test/resources/testscriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		wb.close();
		
		return data;
	}
	
	public int getRowCount(String sheetName) throws Throwable {
		
		FileInputStream fis = new FileInputStream("./src/test/resources/testscriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowCount = wb.getSheet(sheetName).getLastRowNum();	
		
		return rowCount;
	}
	
	public void setDataIntoExcel(String sheetName, int rowNum, int cellNum, String data) throws Throwable {

		FileInputStream fis = new FileInputStream("./src/test/resources/testscriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum).setCellValue(data);
		FileOutputStream fos = new FileOutputStream("./src/test/resources/testscriptdata.xlsx");
		wb.write(fos);
		wb.close();
		
	}
	
	public Object[][] redmultipleDataFromExcel(String sheetName) throws Throwable {
		
		FileInputStream fis = new FileInputStream("./src/test/resources/testscriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(sheetName);
		int lastRow = sh.getLastRowNum();
		int lastCell = sh.getRow(0).getLastCellNum();
		Object[][] data = new Object[lastRow][lastCell];
		for(int i=0; i<lastRow; i++) 
		{
			for(int j=0; j<lastCell; j++)
			{
				data[i][j] = sh.getRow(i+1).getCell(j).getStringCellValue();
			}
		}
		wb.close();
		
		return data;
	}
	
}
