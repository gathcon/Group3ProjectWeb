package dao;

import java.util.List;

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
    public void addFailures(List<Failure> failures) {
        for (Failure failure : failures) {
            em.persist(failure);
        }
    }

	public List<Failure> getAllFailures() {
		@SuppressWarnings("unchecked")
		List<Failure> failures = (List<Failure>) em.createNamedQuery("Failure.findAll").getResultList();
		
		return failures;
	}
}
