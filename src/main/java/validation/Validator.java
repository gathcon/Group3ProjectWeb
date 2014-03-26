package validation;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

public abstract class Validator {
	public abstract boolean check(HSSFCell cell, String check);
	public abstract boolean check(HSSFCell cell1, HSSFCell cell2, String check);
	public abstract boolean check(HSSFCell cell);
	public abstract boolean check(HSSFRow row, HSSFCell cell, int columnIndex);
	
	
}
