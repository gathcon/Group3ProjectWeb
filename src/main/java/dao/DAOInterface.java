package dao;

import model.TableRow;

public interface DAOInterface {
	
	public void persist(TableRow row);
	
	public void remove(TableRow row);

}
