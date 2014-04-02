package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.TableRow;
import model.User;

@Stateless
@LocalBean
public class UserDAO implements DAOInterface{

	@PersistenceContext(unitName = "project")
	private EntityManager em;

	public User getUser(String userName) {
		return em.find(User.class, userName);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void removeUser(User user) {
		em.remove(em.merge(user));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public DatabaseResponse persist(TableRow user) {
		try {
			em.persist(user);
			return DatabaseResponse.OK;
		} catch (EntityExistsException e) {
			return DatabaseResponse.ENTITY_ALREADY_EXISTS;
		}
	}

	public List<User> getAllUsers() {
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) em.createNamedQuery("User.findAll")
				.getResultList();
		return users;
	}
}
