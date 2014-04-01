package loader;

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

import dao.DAOManager;
import dao.DatabaseResponse;

@Stateless
@LocalBean
public class Loader {
	
	@EJB
	ValidationManager validator;
	@EJB
	RowConverter converter;
	@EJB
	DAOManager daoManager;
	
	public void loadFile(String excelWorkBookLocation) {
		
		HSSFWorkbook workbook;
		try {
			long start = System.currentTimeMillis();
			workbook = getFileFrom(excelWorkBookLocation);
			validator.validate(workbook);
			long split1 = System.currentTimeMillis();
			persist(workbook.getSheet("Failure Class Table"), EntityType.FAILURE);
			long split2 = System.currentTimeMillis();
	        persist(workbook.getSheet("Event-Cause Table"), EntityType.EVENTCAUSE);
			long split3 = System.currentTimeMillis();
	        persist(workbook.getSheet("UE Table"), EntityType.USEREQUIPMENT);
			long split4 = System.currentTimeMillis();
	        persist(workbook.getSheet("MCC - MNC Table"), EntityType.OPERATOR);
			long split5 = System.currentTimeMillis();
	        persist(workbook.getSheet("Base Data"), EntityType.BASEDATA);
			long split6 = System.currentTimeMillis();

	        
			System.out.println("Validation complete in " + (split1 - start) + "ms");
			System.out.println("Failure persisted in " + (split2 - split1) + "ms");
			System.out.println("Event Cause persisted in " + (split3 - split2) + "ms");
			System.out.println("User Equipment persisted in " + (split4 - split3) + "ms");
			System.out.println("Operator persisted in " + (split5 - split4) + "ms");
			System.out.println("Base Data persisted in " + (split6 - split5) + "ms");

	        
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	
	private void persist(HSSFSheet sheet, EntityType e) {
		for(Row row: sheet) {
			if(row.getRowNum() != 0) {
				TableRow entity = converter.convert((HSSFRow) row, e);
				DatabaseResponse response = daoManager.persist(entity);
				if(response != DatabaseResponse.OK) {
					System.out.println(response);
				}
			}
		}
	}

	public HSSFWorkbook getFileFrom(String excelWorkBookLocation) throws IOException  {
        return new HSSFWorkbook(new FileInputStream(excelWorkBookLocation));
    }
}
