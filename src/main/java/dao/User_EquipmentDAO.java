package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.TableRow;
import model.User_Equipment;

@Stateless
@LocalBean
public class User_EquipmentDAO implements DAOInterface{

	@PersistenceContext(unitName = "project")
	private EntityManager em;

	public CustomResponse persist(TableRow user_Equipment) {
		@SuppressWarnings("unchecked")
		List<User_Equipment> userEquipments = (List<User_Equipment>) em.createNamedQuery("User_Equipment.findAll").getResultList();
		if(!isValueInList(userEquipments, ((User_Equipment) user_Equipment).getUser_EquipmentId())){
			em.persist(user_Equipment);
			return CustomResponse.OK;
		}
		return CustomResponse.ENTITY_ALREADY_EXISTS;
	}
	
	public boolean isValueInList(List<User_Equipment> userEquipments, int id) {
		for(User_Equipment ue : userEquipments) {
			if(ue.getUser_EquipmentId() == id) return true;
		}
		return false;
	}

	public void remove(TableRow user_Equipment) {
		em.remove(em.merge(user_Equipment));
	}
	
	public User_Equipment getUser_Equipment(int id) {
		return em.find(User_Equipment.class, id);
	}

	public List<User_Equipment> getAllUser_Equipment() {
		@SuppressWarnings("unchecked")
		List<User_Equipment> user_Equipment = (List<User_Equipment>) em.createNamedQuery("User_Equipment.findAll").getResultList();
		return user_Equipment;
	}
}
