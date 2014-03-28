package redundant;
  
import java.io.FileInputStream;
import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.EntityType;
import model.TableRow;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import validation.ErrorLogger;

@Stateless
@LocalBean
public class Loader {
      
    private DomainTableReader reader;
    private BaseDataTableReader baseReader;
    private ErrorLogger errorLogger;
    private int[] rowsInTables;
    
    @EJB
    private EntityPersistenceDistribution epd;
    
    public void loadFile(String excelWorkBookLocation)  {
        try {
        	errorLogger = new ErrorLogger();
            HSSFWorkbook workBook = getFileFrom(excelWorkBookLocation);
            if(workBook==null){return;}
            readAll(workBook);
            readBaseData(workBook);
            errorLogger.writeToFile();
            createNumbers(workBook);
            createEntities(workBook);
        } catch (IOException e) {
            System.out.println("Couldn't find the file at: " + excelWorkBookLocation);
        }
    }
      
    private void createNumbers(HSSFWorkbook workBook) {
          
        rowsInTables = new int[6];
          
        rowsInTables[0] = workBook.getSheetAt(0).getPhysicalNumberOfRows();
        System.out.println("Number of BD rows = " + rowsInTables[0]);
        rowsInTables[1] = workBook.getSheetAt(1).getPhysicalNumberOfRows();
        rowsInTables[2] = workBook.getSheetAt(2).getPhysicalNumberOfRows();
        rowsInTables[3] = workBook.getSheetAt(3).getPhysicalNumberOfRows();
        rowsInTables[4] = workBook.getSheetAt(4).getPhysicalNumberOfRows();
  
    }
  
    public int[] getRowsInTables() {
        return rowsInTables;
    }
    public HSSFWorkbook getFileFrom(String excelWorkBookLocation) throws IOException  {
        return new HSSFWorkbook(new FileInputStream(excelWorkBookLocation));
    }
      
    public void readAll(HSSFWorkbook workBook) {
        reader = new DomainTableReader(errorLogger);
        for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
            HSSFSheet sheet = workBook.getSheetAt(i);
            reader.read(sheet);
        }           
    }
      
    public void readBaseData(HSSFWorkbook workBook) {
        baseReader = new BaseDataTableReader(workBook, errorLogger);
        baseReader.read();
    }
  
    public void createEntities(HSSFWorkbook workBook) {
          
        if(workBook==null){return;}
        createEntitiesFor(workBook.getSheet("Failure Class Table"), EntityType.FAILURE);
        createEntitiesFor(workBook.getSheet("Event-Cause Table"), EntityType.EVENTCAUSE);
        createEntitiesFor(workBook.getSheet("UE Table"), EntityType.USEREQUIPMENT);
        createEntitiesFor(workBook.getSheet("MCC - MNC Table"), EntityType.OPERATOR);
        createEntitiesFor(workBook.getSheet("Base Data"), EntityType.BASEDATA);
    }
      
    public void createEntitiesFor(HSSFSheet sheet, EntityType e){
          
        if(sheet==null){return;}
          
        for(Row r: sheet){
            HSSFRow row = (HSSFRow) r;
              
            if(e != EntityType.BASEDATA && row.getRowNum()==0){
                  
            }
            else if(row != null){
                ConvertedRow convertedRow = new ConvertedRow();
                RowConverter converter = new RowConverter();
                convertedRow = converter.convert(row, e);
                TableRow entity = EntityCreator.getEntity(convertedRow);
               
                //persist here
                epd.find(entity, e);
            }       
        }
        System.out.println(e.toString() + " Entities persisted");
    }
} 