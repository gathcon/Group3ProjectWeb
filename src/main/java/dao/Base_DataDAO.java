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
}