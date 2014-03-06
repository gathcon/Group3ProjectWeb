package persistence;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entityCreator.EntityType;

public class PersistenceUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	protected static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("project");

	public static void persist(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		em.close();
	}

	public static void remove(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Object mergedEntity = em.merge(entity);
		em.remove(mergedEntity);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void removeAll(EntityType table) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		switch (table) {
		case BASEDATA:
			em.createQuery("delete from Base_Data").executeUpdate();
			break;
		case EVENTCAUSE:
			em.createQuery("delete from Event_Cause").executeUpdate();
			break;
		case FAILURE:
			em.createQuery("delete from Failure").executeUpdate();
			break;
		case USEREQUIPMENT:
			em.createQuery("delete from User_Equipment").executeUpdate();
			break;
		case OPERATOR:
			em.createQuery("delete from Operator").executeUpdate();
			break;

		default:
			break;
		}
		em.getTransaction().commit();
		em.close();
	}

	public static Object merge(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		entity = em.merge(entity);
		em.getTransaction().commit();
		em.close();
		return entity;
	}

	public static EntityManager createEM() {
		return emf.createEntityManager();
	}

}