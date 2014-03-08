package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.User_Equipment;

@Stateless
@LocalBean
public class User_EquipmentDAO {

    @PersistenceContext(unitName = "project")
    private EntityManager em;
    
    public User_Equipment getUser_Equipment(int id) {
    	return em.find(User_Equipment.class, id);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addUser_Equipments(List<User_Equipment> user_Equipments) {
        for (User_Equipment user_Equipment : user_Equipments) {
            em.persist(user_Equipment);
        }
    }

	public List<User_Equipment> getAllUser_Equipments() {
		@SuppressWarnings("unchecked")
		List<User_Equipment> user_Equipments = (List<User_Equipment>) em.createNamedQuery("User_Equipment.findAll").getResultList();
		
		return user_Equipments;
	}
}