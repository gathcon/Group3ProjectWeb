package loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
			
			try {
				
				String filename = "/home/timing/timing.txt";
				File file = new File(filename);

				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter out = new FileWriter(filename, true);
				out.write(new Date().toString() + "\n");
				out.write("\nValidation complete:\t\t\t" + (split1 - start) + " ms");
				out.write("\nEvent Cause persisted:\t\t" + (split3 - split2) + " ms");
				out.write("\nFailure persisted:\t\t\t\t" + (split2 - split1) + " ms");
				out.write("\nUser Equipment persisted:\t" + (split4 - split3) + " ms");
				out.write("\nOperator persisted:\t\t\t" + (split5 - split4) + " ms");
				out.write("\nBase Data persisted:\t\t\t" + (split6 - split5) + " ms");
				out.write("\n\nTotal time:\t\t\t\t\t" + (split6 - start) + " ms");
				out.write("\n\n#########################################################\n\n");
				out.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	        
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
	
	private void persistBase_Data(HSSFSheet sheet, EntityType e) {
		List<TableRow> rows = new ArrayList<TableRow>();
		for(Row row: sheet) {
			if(row.getRowNum() != 0) {
				TableRow entity = converter.convert((HSSFRow) row, e);
				rows.add(entity);
			}
		}
		base_DataDAO.persist(rows);
	}

	public HSSFWorkbook getFileFrom(String excelWorkBookLocation) throws IOException  {
        return new HSSFWorkbook(new FileInputStream(excelWorkBookLocation));
    }
}
