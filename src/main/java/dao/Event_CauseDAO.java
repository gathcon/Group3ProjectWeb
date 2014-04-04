package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
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

	public CustomResponse persist(TableRow event_Cause) {
		@SuppressWarnings("unchecked")
		List<Event_Cause> eventCauses = (List<Event_Cause>) em.createNamedQuery("Event_Cause.findAll").getResultList();
		Event_CausePK epk = ((Event_Cause) event_Cause).getId();
		if(!isValueInList(eventCauses, epk)){
			em.persist(event_Cause);
			return CustomResponse.OK;
		}
		return CustomResponse.ENTITY_ALREADY_EXISTS;
	}
	
	public boolean isValueInList(List<Event_Cause> eventCauses, Event_CausePK epk) {
		for(Event_Cause ec : eventCauses) {
			if(ec.getId().equals(epk)) return true;
		}
		return false;
	}

	public void remove(TableRow event_Cause) {
		em.remove(em.merge(event_Cause));
	}
	
	public Event_Cause getEvent_Cause(Event_CausePK id) {
		return em.find(Event_Cause.class, id);
	}
}
