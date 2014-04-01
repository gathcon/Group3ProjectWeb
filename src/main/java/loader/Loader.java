package loader;

import java.io.FileInputStream;
import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.EntityType;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import dao.DAOManager;

@Stateless
@LocalBean
public class Loader {
	
	@EJB
	ValidationManager validator;
//	@EJB
//	Converter converter;
	@EJB
	DAOManager daoManager;
	
	public void loadFile(String excelWorkBookLocation) {
		
		HSSFWorkbook workbook;
		try {
			workbook = getFileFrom(excelWorkBookLocation);
			validator.validate(workbook);
			
			persist(workbook.getSheet("Failure Class Table"), EntityType.FAILURE);
	        persist(workbook.getSheet("Event-Cause Table"), EntityType.EVENTCAUSE);
	        persist(workbook.getSheet("UE Table"), EntityType.USEREQUIPMENT);
	        persist(workbook.getSheet("MCC - MNC Table"), EntityType.OPERATOR);
	        persist(workbook.getSheet("Base Data"), EntityType.BASEDATA);
	        
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	
	private void persist(HSSFSheet sheet, EntityType e) {
//		for(Row row: sheet) {
//			Table entity = converter.convert(row, e);
//			daoManager.persist(entity);
//		}
	}

	public HSSFWorkbook getFileFrom(String excelWorkBookLocation) throws IOException  {
        return new HSSFWorkbook(new FileInputStream(excelWorkBookLocation));
    }
}