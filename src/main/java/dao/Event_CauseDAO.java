package dao;

import java.util.List;

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
    
    public Event_Cause getEvent_Cause(Event_CausePK ecPK) {
    	return em.find(Event_Cause.class, ecPK);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addEvent_Causes(List<Event_Cause> event_Causes) {
        for (Event_Cause event_Cause : event_Causes) {
            em.persist(event_Cause);
        }
    }

	public List<Event_Cause> getAllEvent_Causes() {
		@SuppressWarnings("unchecked")
		List<Event_Cause> event_Causes = (List<Event_Cause>) em.createNamedQuery("Event_Cause.findAll").getResultList();
		
		return event_Causes;
	}

	public void addEvent_Cause(Object entity) {
		em.persist(entity);
		
	}
}