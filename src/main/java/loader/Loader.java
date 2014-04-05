package loader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import model.EntityType;
import model.TableRow;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import dao.Base_DataDAO;
import dao.DAOManager;

@Stateless
@LocalBean
public class Loader {
	
	@EJB
	ValidationManager validator;
	@EJB
	RowConverter converter;
	@EJB
	DAOManager daoManager;
	@EJB
	Base_DataDAO base_DataDAO;
	
	private int duplicateCount = 0;
	
	public void loadFile(String excelWorkBookLocation) {
		
		HSSFWorkbook workbook;
		try {
			long start = System.currentTimeMillis();
			workbook = getFileFrom(excelWorkBookLocation);
			validator.validate(workbook);
			long split1 = System.currentTimeMillis();
			
			persist(workbook.getSheetAt(1), EntityType.EVENTCAUSE);
			long split2 = System.currentTimeMillis();
			
			persist(workbook.getSheetAt(2), EntityType.FAILURE);
			long split3 = System.currentTimeMillis();
	        
			persist(workbook.getSheetAt(3), EntityType.USEREQUIPMENT);
			long split4 = System.currentTimeMillis();
	        
			persist(workbook.getSheetAt(4), EntityType.OPERATOR);
			long split5 = System.currentTimeMillis();
	        
			persist(workbook.getSheetAt(0), EntityType.BASEDATA);
			long split6 = System.currentTimeMillis();

			PrintWriter file = new PrintWriter("/home/timing/timing" + new Date().toString() + ".txt");
	        
			file.println("Validation complete in " + (split1 - start) + "ms");
			file.println("Failure persisted in " + (split2 - split1) + "ms");
			file.println("Event Cause persisted in " + (split3 - split2) + "ms");
			file.println("User Equipment persisted in " + (split4 - split3) + "ms");
			file.println("Operator persisted in " + (split5 - split4) + "ms");
			file.println("Base Data persisted in " + (split6 - split5) + "ms");
			file.println("Failed count " + duplicateCount);

	        file.close();
	        
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	
	private void persist(HSSFSheet sheet, EntityType e) {
		for(Row row: sheet) {
			if(row.getRowNum() != 0) {
				TableRow entity = converter.convert((HSSFRow) row, e);
				daoManager.persist(entity);
			}
		}
	}

	public HSSFWorkbook getFileFrom(String excelWorkBookLocation) throws IOException  {
        return new HSSFWorkbook(new FileInputStream(excelWorkBookLocation));
    }
}
