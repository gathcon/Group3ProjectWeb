package loader;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.EntityType;

import org.apache.poi.hssf.usermodel.HSSFRow;

import redundant.ConvertedRow;

@LocalBean
@Stateless
public class RowConverter {

	public ConvertedRow convert(HSSFRow row, EntityType e) {
		
		
		
		return null;
	}
	
}
