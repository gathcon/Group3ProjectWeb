package validation;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

public class SubValidator extends Validator {

	
	public boolean check(HSSFCell cell, String check) {
		return false;
	}

	
	public boolean check(HSSFCell cell1, HSSFCell cell2, String check) {
		return false;
	}

	
	public boolean check(HSSFCell cell) {
		return false;
	}


	@Override
	public boolean check(HSSFRow row, HSSFCell cell, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

}
