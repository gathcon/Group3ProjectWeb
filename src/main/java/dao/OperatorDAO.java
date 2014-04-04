package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Operator;
import model.OperatorPK;
import model.TableRow;

@Stateless
@LocalBean
public class OperatorDAO implements DAOInterface{
	
	@PersistenceContext(unitName = "project")
	private EntityManager em;

	public void persist(TableRow operator) {
		@SuppressWarnings("unchecked")
		List<Operator> operators = (List<Operator>) em.createNamedQuery("Operator.findAll").getResultList();
		OperatorPK opk = ((Operator) operator).getId();
		if(!isValueInList(operators, opk)){
			em.persist(operator);
		}
	}
	
	public boolean isValueInList(List<Operator> eventCauses, OperatorPK opk) {
		for(Operator ec : eventCauses) {
			if(ec.getId().equals(opk)) return true;
		}
		return false;
	}

	public void remove(TableRow operator) {
		em.remove(em.merge(operator));
	}	
	
	public Operator getOperator(OperatorPK id) {
		return em.find(Operator.class, id);
	}
}
