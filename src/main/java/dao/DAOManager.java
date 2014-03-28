package dao;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.Base_Data;
import model.Event_Cause;
import model.Failure;
import model.Operator;
import model.User_Equipment;

@Stateless
@LocalBean
public class DAOManager {
	
	@EJB private Base_DataDAO baseDataDAO;
	@EJB private FailureDAO failureDAO;
	@EJB private User_EquipmentDAO user_EquipmentDAO;
	@EJB private OperatorDAO operatorDAO;
	@EJB private Event_CauseDAO event_CauseDAO;

	public DatabaseResponse persist(Base_Data entity) {
		return baseDataDAO.addBase_Data(entity);
	}
	
	public DatabaseResponse persist(Event_Cause entity) {
		return event_CauseDAO.addEvent_Cause(entity);		
	}
	
	public DatabaseResponse persist(Failure entity) {
		return failureDAO.addFailure(entity);		
	}
	
	public DatabaseResponse persist(Operator entity) {
		return operatorDAO.addOperator(entity);
	}
	
	public DatabaseResponse persist(User_Equipment entity) {
		return user_EquipmentDAO.addUser_Equipment(entity);		
	}

	public Base_DataDAO getBaseDataDAO() {
		return baseDataDAO;
	}

	public void setBaseDataDAO(Base_DataDAO baseDataDAO) {
		this.baseDataDAO = baseDataDAO;
	}

	public FailureDAO getFailureDAO() {
		return failureDAO;
	}

	public void setFailureDAO(FailureDAO failureDAO) {
		this.failureDAO = failureDAO;
	}

	public User_EquipmentDAO getUser_EquipmentDAO() {
		return user_EquipmentDAO;
	}

	public void setUser_EquipmentDAO(User_EquipmentDAO user_EquipmentDAO) {
		this.user_EquipmentDAO = user_EquipmentDAO;
	}

	public OperatorDAO getOperatorDAO() {
		return operatorDAO;
	}

	public void setOperatorDAO(OperatorDAO operatorDAO) {
		this.operatorDAO = operatorDAO;
	}

	public Event_CauseDAO getEvent_CauseDAO() {
		return event_CauseDAO;
	}

	public void setEvent_CauseDAO(Event_CauseDAO event_CauseDAO) {
		this.event_CauseDAO = event_CauseDAO;
	}

}
