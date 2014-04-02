package dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import model.TableRow;

@Stateless
@LocalBean
public interface DAOInterface {
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public DatabaseResponse persist(TableRow row);

}
