package dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.Base_Data;
import model.Event_Cause;
import model.Failure;
import model.Operator;
import model.TableRow;
import model.User_Equipment;

@Stateless
@LocalBean
public class DAOManager {

	@EJB
	private Base_DataDAO baseDataDAO;
	@EJB
	private FailureDAO failureDAO;
	@EJB
	private User_EquipmentDAO user_EquipmentDAO;
	@EJB
	private OperatorDAO operatorDAO;
	@EJB
	private Event_CauseDAO event_CauseDAO;

	public CustomResponse persist(TableRow entity) {
		if (entity instanceof Base_Data) {
			return baseDataDAO.persist((Base_Data) entity);
		}
		if (entity instanceof Event_Cause) {
			return event_CauseDAO.persist((Event_Cause) entity);
		}
		if (entity instanceof Failure) {
			return failureDAO.persist(entity);	
		}
		if (entity instanceof Operator) {
			return operatorDAO.persist((Operator) entity);
		}
		if (entity instanceof User_Equipment) {
			return user_EquipmentDAO.persist((User_Equipment) entity);
		}
		return CustomResponse.UNKNOWN_TYPE;
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
