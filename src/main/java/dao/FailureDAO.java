package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Failure;
import model.TableRow;

@Stateless
@LocalBean
public class FailureDAO implements DAOInterface{

	@PersistenceContext(unitName = "project")
	private EntityManager em;

	public void persist(TableRow failure) {
		@SuppressWarnings("unchecked")
		List<Failure> failures = (List<Failure>) em.createNamedQuery("Failure.findAll").getResultList();
		if(!isValueInList(failures, ((Failure) failure).getFailureId())){
			em.persist(failure);
		}
	}
	
	public boolean isValueInList(List<Failure> failures, int id) {
		for(Failure f : failures) {
			if(f.getFailureId() == id) return true;
		}
		return false;
	}

	public void remove(TableRow failure) {
		em.remove(em.merge(failure));
	}
	
	public Failure getFailure(int id) {
		return em.find(Failure.class, id);
	}

}
