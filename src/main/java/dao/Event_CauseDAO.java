package dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Event_Cause;
import model.Event_CausePK;
import model.TableRow;

@Stateless
@LocalBean
public class Event_CauseDAO implements DAOInterface{
	
	@PersistenceContext(unitName = "project")
	private EntityManager em;
	
	public Event_Cause getEvent_Cause(Event_CausePK id) {
		return em.find(Event_Cause.class, id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public DatabaseResponse persist(TableRow event_Cause) {
		try {
			em.persist(event_Cause);
			return DatabaseResponse.OK;
		} catch (EntityExistsException e) {
			return DatabaseResponse.ENTITY_ALREADY_EXISTS;
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void removeEvent_Cause(Event_Cause event_Cause) {
		em.remove(em.merge(event_Cause));
	}
}
