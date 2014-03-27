package redundant;

import org.apache.poi.hssf.usermodel.HSSFSheet;

public abstract class TableReader {
	public abstract void read();
	public abstract void read(HSSFSheet sheet);
}
