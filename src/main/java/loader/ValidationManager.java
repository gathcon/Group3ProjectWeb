package loader;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

@Stateless
@LocalBean
public class ValidationManager {
	private String[] columnsThatShouldBeStringsBaseData = { "NE Version", "Description", "COUNTRY", "MARKETING NAME", "MANUFACTURER", "ACCESS CAPABILITY",
			"MODEL", "VENDOR NAME", "OS", "INPUT_MODE" };
	private String[] columnsThatShouldBeStrings = { "NE Version", "Description", "COUNTRY", "OPERATOR", "MARKETING NAME", "MANUFACTURER", "ACCESS CAPABILITY",
			"MODEL", "VENDOR NAME", "UE TYPE", "OS", "INPUT_MODE" };

	@EJB
	ErrorLogger errorLogger;

	List<Integer> removalList;

	public ValidationManager() {
		removalList = new ArrayList<Integer>();
	}

	public void validate(HSSFWorkbook workbook) {

		for (int index = 0; index < workbook.getNumberOfSheets(); index++) {
			HSSFSheet sheet = workbook.getSheetAt(index);
			validateTypeInAllCells(sheet);
		}

		HSSFSheet baseDataSheet = workbook.getSheet("Base Data");
		validateForeignKeysOneCol(baseDataSheet, 2, workbook.getSheet("Failure Class Table"), 0);
		validateForeignKeysOneCol(baseDataSheet, 3, workbook.getSheet("UE Table"), 0);
		validateForeignKeysTwoCol(baseDataSheet, 1, 8, workbook.getSheet("Event-Cause Table"), 1, 0);
		validateForeignKeysTwoCol(baseDataSheet, 4, 5, workbook.getSheet("MCC - MNC Table"), 0, 1);

		errorLogger.writeToFile();

	}

	public void removeBadRowsFromSheet(HSSFSheet sheet) {
		for (Integer i : removalList) {
			if (sheet.getRow(i) != null)
				sheet.removeRow(sheet.getRow(i));
		}
		removalList.clear();
	}

	public void validateForeignKeysOneCol(HSSFSheet baseDataSheet, int baseDataColumnIndex, HSSFSheet refSheet, int refColumnIndex) {
		for (Row row : baseDataSheet) {
			Cell cell = row.getCell(baseDataColumnIndex);
			if (row.getRowNum() != 0 && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
				Double baseDataColumnValue = cell.getNumericCellValue();
				if (cellContentsDontExistInRefTableOneCol(refSheet, baseDataColumnValue, refColumnIndex)) {
					errorLogger.addToLogVector((HSSFRow) row);
					removalList.add(row.getRowNum());
				}
			}
		}
		removeBadRowsFromSheet(baseDataSheet);
	}

	public void validateForeignKeysTwoCol(HSSFSheet baseDataSheet, int baseDataColumnIndex1, int baseDataColumnIndex2, HSSFSheet refSheet, int refColumnIndex1,
			int refColumnIndex2) {
		for (Row row : baseDataSheet) {
			if (row.getRowNum() != 0) {
				Cell cell1 = row.getCell(baseDataColumnIndex1);
				Cell cell2 = row.getCell(baseDataColumnIndex2);
				if(cell1.getCellType() != Cell.CELL_TYPE_BLANK && cell2.getCellType() != Cell.CELL_TYPE_BLANK) {
					Double baseDataColumnValue1 = row.getCell(baseDataColumnIndex1).getNumericCellValue();
					Double baseDataColumnValue2 = row.getCell(baseDataColumnIndex2).getNumericCellValue();

					if (cellContentsDontExistInRefTableTwoCol(refSheet, baseDataColumnValue1, baseDataColumnValue2, refColumnIndex1, refColumnIndex2)) {
						errorLogger.addToLogVector((HSSFRow) row);
						removalList.add(row.getRowNum());
					}
				}
				else if(cell1.getCellType() == Cell.CELL_TYPE_BLANK && cell2.getCellType() != Cell.CELL_TYPE_BLANK) {
					Double baseDataColumnValue2 = row.getCell(baseDataColumnIndex2).getNumericCellValue();

					if (cellContentsDontExistInRefTableOneCol(refSheet, baseDataColumnValue2, refColumnIndex2)) {
						errorLogger.addToLogVector((HSSFRow) row);
						removalList.add(row.getRowNum());
					}
				}
				else if(cell1.getCellType() != Cell.CELL_TYPE_BLANK && cell2.getCellType() == Cell.CELL_TYPE_BLANK) {
					Double baseDataColumnValue1 = row.getCell(baseDataColumnIndex1).getNumericCellValue();

					if (cellContentsDontExistInRefTableOneCol(refSheet, baseDataColumnValue1, refColumnIndex1)) {
						errorLogger.addToLogVector((HSSFRow) row);
						removalList.add(row.getRowNum());
					}
				}
			}
		}
		removeBadRowsFromSheet(baseDataSheet);
	}

	public boolean cellContentsDontExistInRefTableOneCol(HSSFSheet refSheet, Double baseDataColumnValue, int refColumnIndex1) {

		for (Row row : refSheet) {
			if (row.getRowNum() != 0) {
				if(baseDataColumnValue == -1 || baseDataColumnValue == row.getCell(refColumnIndex1).getNumericCellValue()){
					return false;
				}
			}
		}
		return true;
	}

	public boolean cellContentsDontExistInRefTableTwoCol(HSSFSheet refSheet, Double baseDataColumnValue1, Double baseDataColumnValue2, int refColumnIndex1,
			int refColumnIndex2) {

		for (Row row : refSheet) {
			if (row.getRowNum() != 0) {
				if(baseDataColumnValue1 == row.getCell(refColumnIndex1).getNumericCellValue()
						&& baseDataColumnValue2 == row.getCell(refColumnIndex2).getNumericCellValue()) {
					return false;
				}
			}
		}
		return true;
	}

	public void validateTypeInAllCells(HSSFSheet sheet) {

		int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();

		for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
			String header = sheet.getRow(0).getCell(columnIndex).getStringCellValue();

			for (Row row : sheet) {
				if(row.getRowNum() != 0) {
					Cell cell = row.getCell(columnIndex);

					if (isCellInvalid(cell, header)) {
						errorLogger.addToLogVector((HSSFRow) row);
						removalList.add(row.getRowNum());
					}
				}
			}
			removeBadRowsFromSheet(sheet);
		}
	}

	public Boolean isCellInvalid(Cell cell, String header) {

		String sheetName = cell.getSheet().getSheetName();
		String[] columnNamesToCheck;

		if (sheetName.equalsIgnoreCase("Base Data"))
			columnNamesToCheck = columnsThatShouldBeStringsBaseData;
		else
			columnNamesToCheck = columnsThatShouldBeStrings;

		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			if (cell.getStringCellValue().equalsIgnoreCase("(null)") && !shouldBeString(header, columnNamesToCheck)) {
				setCellToNull(cell);
				return false;
			}
			if (shouldBeString(header, columnNamesToCheck)) {
				return false;
			}
		}
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC && !shouldBeString(header, columnNamesToCheck)) {
			return false;
		}
		return true;
	}

	public void setCellToNull(Cell cell) {
		Row row = cell.getRow();
		int index = cell.getColumnIndex();
		cell = row.createCell(index);
	}

	public boolean shouldBeString(String header, String[] columnNamesToCheck) {

		for (String heading : columnNamesToCheck) {
			if (heading.equals(header))
				return true;
		}
		return false;
	}

	public ErrorLogger getErrorLogger() {
		return errorLogger;
	}

	public void setErrorLogger(ErrorLogger errorLogger) {
		this.errorLogger = errorLogger;
	}
}