package dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityExistsException;
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
	public DatabaseResponse addFailure(Failure failure) {
		try {
			em.persist(failure);
			return DatabaseResponse.OK;
		} catch (EntityExistsException e) {
			return DatabaseResponse.ENTITY_ALREADY_EXISTS;
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void removeFailure(Failure failure) {
		em.remove(em.merge(failure));
	}

}
