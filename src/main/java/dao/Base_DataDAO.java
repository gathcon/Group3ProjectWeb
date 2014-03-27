package dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Base_Data;
import model.Event_Cause;

@Stateless
@LocalBean
public class Base_DataDAO {

    @PersistenceContext(unitName = "project")
    private EntityManager em;
    
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public DatabaseResponse addBase_Data(Base_Data base_Data) {
		try {
			em.persist(base_Data);
			return DatabaseResponse.OK;
		} catch (EntityExistsException e) {
			return DatabaseResponse.ENTITY_ALREADY_EXISTS;
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void removeBase_Data(Base_Data base_Data) {
		em.remove(em.merge(base_Data));
	}
    
	public List<Base_Data> getAllBase_Data() {
		@SuppressWarnings("unchecked")
		List<Base_Data> base_Data = (List<Base_Data>) em.createNamedQuery("Base_Data.findAll").getResultList();
		return base_Data;
	}

	public List<Base_Data> getBase_DataByFailureId(int failureId) {
		@SuppressWarnings("unchecked")
		List<Base_Data> Base_Data = (List<Base_Data>) em.createNamedQuery("Base_Data.findByFailureId").
		setParameter("failureId", failureId).getResultList();
		if (Base_Data.size() == 0)
			return null;
		else 
			return Base_Data;	
	}
	
	public List<Base_Data> getIMSIByDateRange(Date startDate, Date endDate) {
		@SuppressWarnings("unchecked")
		List<Base_Data> Base_Data = (List<Base_Data>) em.createNamedQuery("Base_Data.findIMSIByDateRange").
		setParameter("startDate", startDate)
		.setParameter("endDate", endDate).getResultList();
		if (Base_Data.size() == 0)
			return null;
		else 
			return Base_Data;	
	}

	public List<Event_Cause> getEventID_CauseCodeByImsi(BigInteger imsi) {
		@SuppressWarnings("unchecked")
		List<Event_Cause> Event_Cause = (List<Event_Cause>) em.createNamedQuery("Base_Data.findEventID_CauseCodeByImsi").
		setParameter("imsi", imsi).getResultList();
		if (Event_Cause.size() == 0)
			return null;
		else 
			return Event_Cause;
	}
	
	public Number imsiFailureCountBetweenDates(Date startDate, Date endDate, BigInteger imsi){
		Number number = null;
		number = (Number) em.createNamedQuery("Base_Data.findFailuresByImsiAndByDateRange").setParameter("imsi", imsi).setParameter("startDate", startDate).setParameter("endDate", endDate).getSingleResult();
		return number;
	}

	public List<Base_Data> totalFailuresDurationForImsiBetweenDates(Date startDate, Date endDate) {
		@SuppressWarnings("unchecked")
		List<Base_Data> Base_Data = (List<Base_Data>) em.createNamedQuery("Base_Data.findFailuresDurationForIMSIsBetweenDates").setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
		if (Base_Data.size() == 0)
			return null;
		else 
			return Base_Data;	
	}	
}
