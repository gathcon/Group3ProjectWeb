package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Base_Data;

@Stateless
@LocalBean
public class Base_DataDAO {

    @PersistenceContext(unitName = "project")
    private EntityManager em;
    
    public Base_Data getBase_Data(int id) {
    	return em.find(Base_Data.class, id);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addBase_Datas(List<Base_Data> base_Datas) {
        for (Base_Data base_Data : base_Datas) {
            em.persist(base_Data);
        }
    }

//	public List<Base_Data> getBase_DataList(int failure) {
//		@SuppressWarnings("unchecked")
//		List<Base_Data> Base_Data = (List<Base_Data>) em.createNamedQuery("Base_Data.findByFailureId").
//		setParameter("failure", failure).getResultList();
//		if (Base_Data.size() == 0)
//			return null;
//		else 
//			return Base_Data;	
//	}

	public List<Base_Data> getAllBase_Data() {
		@SuppressWarnings("unchecked")
		List<Base_Data> base_Data = (List<Base_Data>) em.createNamedQuery("Base_Data.findAll").getResultList();
		
		return base_Data;
	}
}