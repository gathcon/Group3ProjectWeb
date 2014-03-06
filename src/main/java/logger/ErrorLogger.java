package logger;


import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell; 
import org.apache.poi.hssf.usermodel.HSSFRow; 
import org.apache.poi.ss.usermodel.Cell;


public class ErrorLogger implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Vector<Vector<HSSFCell>> cellVectorHolder;
	
	public ErrorLogger()
	{
		cellVectorHolder = new Vector<Vector<HSSFCell>>();
	}
	
	public void addToLogVector(HSSFRow row)
	{
		try
		{
			Iterator<Cell> cellIter = row.cellIterator();
			Vector<HSSFCell> cellStoreVector=new Vector<HSSFCell>();
			
			while (cellIter.hasNext())
			{
				HSSFCell myCell = (HSSFCell) cellIter.next();
				cellStoreVector.addElement(myCell);
			}
			cellVectorHolder.addElement(cellStoreVector);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void writeToFile() 
	{
		PrintWriter file;
		String name;
		Vector<HSSFCell> cellStoreVector;
		
		try
		{
			Date date = new Date();
			name = "log/log" + date.toString() + ".txt";
			file = new PrintWriter(name);
			for (int i = 0; i < cellVectorHolder.size(); i++)
			{
				cellStoreVector = (Vector<HSSFCell>) cellVectorHolder.elementAt(i);
				file.println();
				for (int j = 0; j < cellStoreVector.size(); j++)
				{
					HSSFCell myCell = (HSSFCell)cellStoreVector.elementAt(j);
					String stringCellValue = myCell.toString();
					file.print(stringCellValue + "\t\t");
				}
				file.println();
			}
			file.close();
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
	}
}