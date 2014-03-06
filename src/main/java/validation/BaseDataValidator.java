package validation;  

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Row;

public class BaseDataValidator extends SubValidator {
    
    private HSSFWorkbook workBook;
    
    public BaseDataValidator(HSSFWorkbook workBook){
        this.workBook = workBook;
    }
 
    @Override
    public boolean check(HSSFCell cell, String check) {
        
        boolean result=false;
        
        if(check.equals("failure")){
            result = singleColumnCheck(cell, "Failure Class Table");
        } else if (check.equals("tac")){
            result = singleColumnCheck(cell,"UE Table");
        }   
        return result;
    }


    @Override
    public boolean check(HSSFCell cell1, HSSFCell cell2, String check) {
        
        boolean result = false;
        
        if(check.equals("mccmnc")){
            result = dualColumnCheck(cell1, cell2, "MCC - MNC Table");
        } else if(check.equals("eventidandcausecode")){
            result = dualColumnCheck(cell1, cell2, "Event-Cause Table");
        }
                    
        return result;
    }
    
    public boolean singleColumnCheck(HSSFCell passedInCell, String table){
    
        double passedInCellNumericValue;
        double sheetCellNumericValue;
        HSSFSheet sheet;
        HSSFRow row;
        HSSFCell sheetCell;
        
            if(passedInCell == null){
            	System.out.println("Got in here");
                return true;
            }
    
            if(passedInCell.getCellType() == 0){
                    
                passedInCellNumericValue = passedInCell.getNumericCellValue(); 
              
                sheet = workBook.getSheet(table);
                    
                if(sheet != null) {
                    for(Row r: sheet){
                        row = (HSSFRow) r;
                        sheetCell = row.getCell(0);
                        
                        if(sheetCell.getCellType() ==0){
                            sheetCellNumericValue = sheetCell.getNumericCellValue();
                            
                            if(Double.compare(passedInCellNumericValue,sheetCellNumericValue) == 0){
                                return true;
                            }
                        }   
                            
                
                    }
                }
            }
           
        
        return false;
    }
    
    
    public boolean dualColumnCheck(HSSFCell cell1, HSSFCell cell2, String table){
        
        double passedInCellValue1;
        double passedInCellValue2;
        
        double sheetCellValue1;
        double sheetCellValue2;
        HSSFSheet sheet;
        HSSFRow row;
        HSSFCell sheetCell1, sheetCell2;
        
        if(cell1 == null && cell2 == null){
            return false;
         }
         
         if(table.equals("Event-Cause Table") && cell1 == null){
         	return singleColumnCheck(cell2, table);
         }
         
         if(table.equals("Event-Cause Table") && cell2 == null){
         	return singleColumnCheck(cell1, table);
         }
         
        //check both passed in cells are numeric
        if(cell1.getCellType() == 0 && cell2.getCellType() == 0){
            // get numeric values
            passedInCellValue1 = cell1.getNumericCellValue();
            passedInCellValue2  = cell2.getNumericCellValue();
            
            sheet = workBook.getSheet(table);
            if(sheet != null) {
                  for(Row r: sheet){
                        row = (HSSFRow) r;
                        sheetCell1 = row.getCell(0);
                        sheetCell2 = row.getCell(1);
                        
                        if(sheetCell1.getCellType() ==0 && sheetCell2.getCellType()==0){
                            sheetCellValue1 = sheetCell1.getNumericCellValue();
                            sheetCellValue2 = sheetCell2.getNumericCellValue();
                            
                            if(Double.compare(passedInCellValue1,sheetCellValue1) == 0 && Double.compare( passedInCellValue2,sheetCellValue2) == 0 ){
                                return true;
                            }
                        }              
            
                  }
            }
        }
  
        return false;
    }

}
