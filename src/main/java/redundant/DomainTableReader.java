package redundant;

import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;

import validation.DomainTableValidator;
import validation.ErrorLogger;
import redundant.SubReader;

public class DomainTableReader extends SubReader {

	private ErrorLogger errorLogger;
	private DomainTableValidator validator;
	
	private int sheetNumber=0;
	private int currentColumnAmount = 0;
	
	private int[] ColumnsInSheet = {14,3,2,9,4};
	
	private HSSFCell currentCell;
	private ArrayList<Integer> removalList;
	
	public DomainTableReader(ErrorLogger errorLogger){
		validator = new DomainTableValidator();
		this.errorLogger = errorLogger;
		removalList = new ArrayList<Integer>();
		
	}
	
	@Override
	public void read(HSSFSheet sheet) {

		sheetNumber = findSheetNum(sheet);
				
		currentColumnAmount = ColumnsInSheet[sheetNumber];
		loopThroughColumnsIn(sheet);
		
	}

	private int findSheetNum(HSSFSheet sheet) {
		
		System.out.println(sheet.getSheetName());
		
		if(sheet.getSheetName().equalsIgnoreCase("Event-Cause Table")){
			return 1;
		}
		if(sheet.getSheetName().equalsIgnoreCase("Failure Class Table")){
			return 2;
		}
		if(sheet.getSheetName().equalsIgnoreCase("UE Table")){
			return 3;
		}
		if(sheet.getSheetName().equalsIgnoreCase("MCC - MNC Table")){
			return 4;
		}
		
		else{
			return 0;
		}
		
	}

	private void loopThroughColumnsIn(HSSFSheet sheet) {
		
		for(int currentColumn = 0; currentColumn < currentColumnAmount; currentColumn++){
		
			validator.changeDataType(sheet, currentColumn, sheetNumber);
			
			loopThroughRowsInColumn(sheet, currentColumn);
			
			for(int i = 0; i < removalList.size(); i++){
			
				sheet.removeRow(sheet.getRow(removalList.get(i)));
				//System.out.println("Row deleted " + removalList.get(i));
				
			}
			
			removalList.clear();
			validator.setDataType(0);
	
	}
		
		
	}


	private void loopThroughRowsInColumn(HSSFSheet sheet, int currentColumn) {
		
	
		for(Row row: sheet){
			
			HSSFRow currentRow = (HSSFRow) row;
			currentCell = currentRow.getCell(currentColumn);
		
		//	System.out.println("Row at the moment is " + currentRow.getRowNum());
			if(row.getRowNum()==0){
				//do nothing as it is a title row, possibly bring in set Data type logic here???
				//System.out.println("row 0");
			}
						
			
			else{
			
				boolean valid = validator.check(currentRow, currentCell, currentColumn);
				
				if(!valid){
				
					errorLogger.addToLogVector(currentRow);
					removalList.add(currentRow.getRowNum());
					//System.out.println("Sheet: " + sheet.getSheetName() + "| The Cell (" + currentColumn + ", " + currentRow.getRowNum() + ") is NOT VALID");
					//System.out.println("Row " + currentRow.getRowNum() + " added to removal list");
					
				}
				
				else{
					
			//	System.out.println("Sheet: " + sheet.getSheetName() + "| The Cell (" + currentColumn + ", " + currentRow.getRowNum() + ") is valid");		
			
				}
			
			}
			
		}
		
	}


}
