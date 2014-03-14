package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Operator;
import model.OperatorPK;

@Stateless
@LocalBean
public class OperatorDAO {

    @PersistenceContext(unitName = "project")
    private EntityManager em;
    
    public Operator getOperator(OperatorPK oPK) {
    	return em.find(Operator.class, oPK);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addOperators(List<Operator> operators) {
        for (Operator operator : operators) {
            em.persist(operator);
        }
    }

	public List<Operator> getAllOperators() {
		@SuppressWarnings("unchecked")
		List<Operator> operators = (List<Operator>) em.createNamedQuery("Operator.findAll").getResultList();
		
		return operators;
	}

	public void addOperator(Object entity) {
		em.persist(entity);
		
	}
}