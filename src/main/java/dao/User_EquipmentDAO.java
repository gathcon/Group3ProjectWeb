package dao;

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
	public void addUser_Equipment(User_Equipment user_Equipment) {
		em.persist(user_Equipment);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void removeUser_Equipment(User_Equipment user_Equipment) {
		em.remove(em.merge(user_Equipment));
	}

}