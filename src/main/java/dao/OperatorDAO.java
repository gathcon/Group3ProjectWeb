package dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Operator;
import model.OperatorPK;

@Stateless
@LocalBean
public class OperatorDAO {
	
	@PersistenceContext(unitName = "project")
	private EntityManager em;
	
	public Operator getOperator(OperatorPK id) {
		return em.find(Operator.class, id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addOperator(Operator operator) {
		em.persist(operator);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void removeOperator(Operator operator) {
		em.remove(em.merge(operator));
	}
}
