package dao;

import model.TableRow;

public interface DAOInterface {
	
	public CustomResponse persist(TableRow row);
	
	public void remove(TableRow row);

}
