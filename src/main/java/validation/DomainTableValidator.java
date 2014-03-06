package validation;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

public class DomainTableValidator extends SubValidator{

	private int dataTypeInColumn =0;
	
	private String[] Strings = {"NE Version", "Description", "COUNTRY", "OPERATOR",
			"MARKETING NAME", "MANUFACTURER", "ACCESS CAPABILITY","MODEL","VENDOR NAME", 
			"UE TYPE", "OS", "INPUT_MODE" };
	
	
	
	@Override
	public boolean check(HSSFRow row, HSSFCell cell, int columnIndex) {
		//System.out.println(dataTypeInColumn + " vs " + cell.getCellType());
		
		if(cell.getCellType() == dataTypeInColumn){
		
			return true;
		}
		 
		 
		if(cell.getCellType() == 1 && cell.getStringCellValue().equalsIgnoreCase("(null)")){
			
		//	System.out.println("NULL FOUND in colum " + columnIndex + ", row " + row.getRowNum() + "... expected data type is " + dataTypeInColumn);
			
			if(dataTypeInColumn == 0){
				//row.removeCell(cell);
				cell = null;
	//			HSSFCell newCell = row.createCell(columnIndex, 0);
				
				
			}
			
			return true;
			
			
			
		}
		
		
		return false;
	}

	
	public void setDataType(int dataType) {
		this.dataTypeInColumn =  dataType;
	}



	public void changeDataType(HSSFSheet sheet, int currentColumn, int sheetNumber) {
		
		HSSFRow row = sheet.getRow(0);
		HSSFCell currentCell = row.getCell(currentColumn);
		String columnName = currentCell.getStringCellValue();
		
		for (int i = 0; i < Strings.length; i++){
			if(Strings[i].equalsIgnoreCase(columnName)){
				dataTypeInColumn = 1;
				//System.out.println("current column " + currentCell.getStringCellValue() + "is a String!!!");
			}
			
		}
		
		if(sheetNumber == 0){
			if(columnName.equalsIgnoreCase("UE Type") || columnName.equalsIgnoreCase("Operator")){
				dataTypeInColumn = 0;
			}
		}
		
		
	}
	
	

}
