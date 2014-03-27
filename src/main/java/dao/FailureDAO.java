package dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Failure;

@Stateless
@LocalBean
public class FailureDAO {

	@PersistenceContext(unitName = "project")
	private EntityManager em;
	
	public Failure getFailure(int id) {
		return em.find(Failure.class, id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addFailure(Failure failure) {
		em.persist(failure);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void removeFailure(Failure failure) {
		em.remove(em.merge(failure));
	}

}
