package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.TableRow;
import model.User;

@Stateless
@LocalBean
public class UserDAO implements DAOInterface{

	@PersistenceContext(unitName = "project")
	private EntityManager em;

	public void persist(TableRow user) {
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) em.createNamedQuery("User.findAll").getResultList();
		if(!isValueInList(users, ((User) user).getUserName())){
			em.persist(user);
		}
	}
	
	public boolean isValueInList(List<User> users, String username) {
		for(User u : users) {
			if(u.getUserName().equals(username)) return true;
		}
		return false;
	}

	public void remove(TableRow user) {
		em.remove(em.merge(user));
	}
	
	public User getUser(String userName) {
		return em.find(User.class, userName);
	}

	public List<User> getAllUsers() {
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) em.createNamedQuery("User.findAll")
				.getResultList();
		return users;
	}
}
