package redundant; 

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import validation.BaseDataValidator;
import validation.ErrorLogger;

import java.util.ArrayList;

public class BaseDataTableReader extends TableReader {
    
    //Failure,Market,Operator,EventId,CauseCode,UEtype
    //2,4,5,1,8,3
    private BaseDataValidator validator;
    private HSSFWorkbook workBook;
    private ErrorLogger logger;
    private ArrayList<HSSFRow> rowsToDelete;
    
    public BaseDataTableReader(HSSFWorkbook workBook, ErrorLogger logger){
        this.workBook = workBook;
        this.logger = logger;
        this.validator = new BaseDataValidator(workBook);
        rowsToDelete = new ArrayList<HSSFRow>();
    }
    
    @Override
    public void read() {
        HSSFSheet sheet = this.workBook.getSheet("Base Data");
        this.read(sheet);
    }
    
    @Override
    public void read(HSSFSheet sheet) {
        readSingleColumn(sheet, "failure", 2);
        readTwoColumns(sheet, "mccmnc", 4,5);
        readTwoColumns(sheet, "eventidandcausecode",8,1);
        readSingleColumn(sheet, "tac", 3);
    }

    public void readSingleColumn(HSSFSheet sheet, String check, int columnNumber) {
            HSSFRow row;
            HSSFCell cell;
            boolean isCellValid;
        
            if(sheet==null){return;}
                
            for(Row r: sheet){
                row = (HSSFRow) r;
                cell = row.getCell(columnNumber);
                isCellValid = validator.check(cell, check);
                
                if(!isCellValid){
                    logger.addToLogVector(row);         
                    rowsToDelete.add(row);
                }
            }
            
            removeRowsFromSheetThatAreInArrayList(sheet);
    }

    public void readTwoColumns(HSSFSheet sheet, String check, int columnNumber1, int columnNumber2) {
        HSSFRow row;
        HSSFCell cell1,cell2;
        boolean cellsValid;

        if(sheet==null){return;}
        
   
        
        for(Row r: sheet){
            row = (HSSFRow) r;
            cell1 = row.getCell(columnNumber1);
            cell2 = row.getCell(columnNumber2);
            
            cellsValid = validator.check(cell1, cell2, check);
            
            if(!cellsValid){
                logger.addToLogVector(row);
                rowsToDelete.add(row);
            }
        }   
        
        removeRowsFromSheetThatAreInArrayList(sheet);
    }

    public void removeRowsFromSheetThatAreInArrayList(HSSFSheet sheet){
        for(HSSFRow r: rowsToDelete){
            sheet.removeRow(r);
        }
        
        rowsToDelete.clear();
    }
    
    public void addToRowsToDelete(HSSFRow row){
    	rowsToDelete.add(row);
    }
    
    public int getSizeOfArrayList(){
    	return rowsToDelete.size();
    }



}
