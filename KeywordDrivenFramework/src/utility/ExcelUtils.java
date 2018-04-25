package utility;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import config.Constants;
public class ExcelUtils 
{
private static XSSFSheet ExcelWSheet;
private static XSSFWorkbook ExcelWBook;
private static XSSFCell Cell;
//To write to excelfile
public static void setExcelFile(String Path, String SheetName ) throws Exception
{
	 FileInputStream ExcelFile = new FileInputStream(Path);
     ExcelWBook = new XSSFWorkbook(ExcelFile);
     ExcelWSheet = ExcelWBook.getSheet(SheetName);
	}


public static String getCellData(int RowNum, int ColNum, String SheetName ) throws Exception
{
	ExcelWSheet = ExcelWBook.getSheet(SheetName);
         Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
String CellData = Cell.getStringCellValue();
 return CellData;
 
	 	}

public static int getRowCount(String SheetName){
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int number=ExcelWSheet.getLastRowNum()+1;
			return number;
		}

			
	public static int getRowContains(String sTestCaseName, int colNum,String SheetName) throws Exception{
		int i;	
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int rowCount = ExcelUtils.getRowCount(SheetName);
			for (i=0 ; i<rowCount; i++)
			{
				if(ExcelUtils.getCellData(i,colNum,SheetName).equalsIgnoreCase(sTestCaseName))
				{
					break;
				}
			}
				return i;
	}
	
	public static int getTestStepsCount(String SheetName, String sTestCaseID, int iTestCaseStart) throws Exception{
		for(int i=1;i<ExcelUtils.getRowCount(SheetName);i++){
		if(!sTestCaseID.equals(ExcelUtils.getCellData(i, Constants.Col_TestCaseID, SheetName)))
		{
		int number = i;
		return number;
		}
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		int number=ExcelWSheet.getLastRowNum()+1;
		return number;
		}
		return 1;
			
	}

		
	

}
