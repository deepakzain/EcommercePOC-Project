/**
 * 
 */
package com.ecommercepoc.utility;

/**
 * @author deepak.j
 *
 */
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class NewExcelLibrary {

	public static String path = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TestData.xlsx";

	public FileInputStream fis = null;

	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;

	public NewExcelLibrary() {

		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// returns the row count in a sheet
	public int getRowCount(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}

	}

	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
			if (rowNum <= 0) {
				return "";
			}

			int index = workbook.getSheetIndex(sheetName);
			int col_Num = -1;
			if (index == -1) {
				return "";
			}

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num = i;
			}
			if (col_Num == -1) {
				return "";
			}

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null) {
				return "";
			}

			cell = row.getCell(col_Num);

			if (cell == null) {
				return "";
			}

			if (cell.getCellType().name().equals("STRING")) {
				return cell.getStringCellValue();
			} else if (cell.getCellType().name().equals("BLANK")) {
				return "";
			} else {
				return String.valueOf(cell.getBooleanCellValue());
			}

		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colName + " does not exist in xls";
		}
	}

	// returns the data from a cell
	public String getCellData(String sheetName, int colNum, int rowNum) {
		try {
			if (rowNum <= 0) {
				return "";
			}

			int index = workbook.getSheetIndex(sheetName);

			if (index == -1) {
				return "";
			}

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null) {
				return "";
			}

			cell = row.getCell(colNum);
			if (cell == null) {
				return "";
			}

			if (cell.getCellType().name().equals("STRING")) {
				return cell.getStringCellValue();
			}

			else if (cell.getCellType().name().equals("BLANK")) {
				return "";
			}

			else {
				return String.valueOf(cell.getBooleanCellValue());
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
		}
	}

	// find whether sheets exists
	public boolean isSheetExist(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			index = workbook.getSheetIndex(sheetName.toUpperCase());
			if (index == -1)
				return false;
			else
				return true;
		} else
			return true;
	}

	// returns number of columns in a sheet
	public int getColumnCount(String sheetName) {
		// check if sheet exists
		if (!isSheetExist(sheetName))
			return -1;

		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);

		if (row == null)
			return -1;

		return row.getLastCellNum();

	}

	public int getCellRowNum(String sheetName, String colName, String cellValue) {

		for (int i = 2; i <= getRowCount(sheetName); i++) {
			if (getCellData(sheetName, colName, i).equalsIgnoreCase(cellValue)) {
				return i;
			}
		}
		return -1;

	}
}
