package dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Operator;
import model.OperatorPK;
import model.TableRow;

@Stateless
@LocalBean
public class OperatorDAO implements DAOInterface{
	
	@PersistenceContext(unitName = "project")
	private EntityManager em;
	
	public Operator getOperator(OperatorPK id) {
		return em.find(Operator.class, id);
	}

	public DatabaseResponse persist(TableRow operator) {
		try {
			em.persist(operator);
			return DatabaseResponse.OK;
		} catch (EntityExistsException e) {
			return DatabaseResponse.ENTITY_ALREADY_EXISTS;
		}
	}

	public void removeOperator(Operator operator) {
		em.remove(em.merge(operator));
	}
}
