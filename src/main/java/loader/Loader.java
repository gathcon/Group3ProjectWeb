package loader;

import java.io.FileInputStream;
import java.io.IOException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.TableRow;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import model.EntityType;
import dao.DAOManager;

@Stateless
@LocalBean
public class Loader {
	
	public void loadFile(String excelWorkBookLocation) {
		
		HSSFWorkbook workBook;
		try {
			workBook = getFileFrom(excelWorkBookLocation);
			persist(workBook.getSheet("Failure Class Table"), EntityType.FAILURE);
	        persist(workBook.getSheet("Event-Cause Table"), EntityType.EVENTCAUSE);
	        persist(workBook.getSheet("UE Table"), EntityType.USEREQUIPMENT);
	        persist(workBook.getSheet("MCC - MNC Table"), EntityType.OPERATOR);
	        persist(workBook.getSheet("Base Data"), EntityType.BASEDATA);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	
	private void persist(HSSFSheet sheet, EntityType e) {
		for(Row row: sheet) {
			//Table entity = Converter.convert(row, e);
			//DAOManager.persist(entity);
		}
		
	}

	public HSSFWorkbook getFileFrom(String excelWorkBookLocation) throws IOException  {
        return new HSSFWorkbook(new FileInputStream(excelWorkBookLocation));
    }

}
