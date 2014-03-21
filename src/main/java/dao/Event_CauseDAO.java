package dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Event_Cause;
import model.Event_CausePK;

@Stateless
@LocalBean
public class Event_CauseDAO {
	
	@PersistenceContext(unitName = "project")
	private EntityManager em;
	
	public Event_Cause getEvent_Cause(Event_CausePK id) {
		return em.find(Event_Cause.class, id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addEvent_Cause(Event_Cause event_Cause) {
		em.persist(event_Cause);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void removeEvent_Cause(Event_Cause event_Cause) {
		em.remove(em.merge(event_Cause));
	}
}
