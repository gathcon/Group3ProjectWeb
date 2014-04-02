package dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import model.TableRow;

@Stateless
@LocalBean
public class Base_DataDAO implements DAOInterface{

    @PersistenceContext(unitName = "project")
    private EntityManager em;
    
	public DatabaseResponse persist(TableRow base_Data) {
		try {
			em.persist(base_Data);
			return DatabaseResponse.OK;
		} catch (EntityExistsException e) {
			return DatabaseResponse.ENTITY_ALREADY_EXISTS;
		}
	}
	
	public void removeBase_Data(Base_Data base_Data) {
		em.remove(em.merge(base_Data));
	}
    
	public List<Base_Data> getAllBase_Data() {
		@SuppressWarnings("unchecked")
		List<Base_Data> base_Data = (List<Base_Data>) em.createNamedQuery("Base_Data.findAll").getResultList();
		return base_Data;
	}

	public List<Base_Data> getIMSIByFailureId(int failureId) {
		@SuppressWarnings("unchecked")
		List<Base_Data> Base_Data = (List<Base_Data>) em.createNamedQuery("Base_Data.findIMSIByFailureId").
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
	public List<Base_Data> getImsisForDropdown() {
		@SuppressWarnings("unchecked")
		List<Base_Data> base_Data = (List<Base_Data>) em.createNamedQuery("Base_Data.findAllImsis").getResultList();
		return base_Data;
	}
	public List<Base_Data> getAllPhoneModels() {
		@SuppressWarnings("unchecked")
		List<Base_Data> base_Data = (List<Base_Data>) em.createNamedQuery("Base_Data.findAllPhoneModels").getResultList();
		return base_Data;
	}
	
	public Number failureCountByModelBetweenDates(Date startDate, Date endDate, String model){
		Number number = null;
		number = (Number) em.createNamedQuery("Base_Data.findFailureCountByModelAndByDateRange").setParameter("model", model).setParameter("startDate", startDate).setParameter("endDate", endDate).getSingleResult();
		return number;
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

	public List totalFailuresDurationForImsiBetweenDates(Date startDate, Date endDate) {
		@SuppressWarnings("unchecked")
		List base_Data = em.createNamedQuery("Base_Data.findFailuresDurationForIMSIsBetweenDates").setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
		if (base_Data.size() == 0)
			return null;
		else 
			return base_Data;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getTop10ImsisByDate(Date startDate, Date endDate) {
		List<Object[]> queryResult = null;
		queryResult =  (List<Object[]>) em.createNamedQuery("Base_Data.findTop10ImsisWithinDateRange").setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
		
		Collections.sort(queryResult, new Comparator<Object[]>(){
			@Override
			public int compare(Object[] o1, Object[] o2) {
				return ((Long) o2[1]).compareTo( (Long) o1[1]);
			}
		});
		
		int size = queryResult.size(); 
		if(size >= 10){
			return queryResult.subList(0, 10);
		} else if (size >= 1 && size < 10) {
			return queryResult.subList(0, size);
		} else {
			return null;
		}
	}

	public List<BigInteger> getAllImsis() {
		List<BigInteger> queryResult = null;
		
		queryResult =  (List<BigInteger>) em.createNamedQuery("Base_Data.findAllImsiS").getResultList();
	
		System.out.println("IMSI: ====" + queryResult.get(0).toString());
		
		return queryResult;
	}	
}
