package reader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.Failure;
import dao.Base_DataDAO;
import dao.Event_CauseDAO;
import dao.FailureDAO;
import dao.OperatorDAO;
import dao.User_EquipmentDAO;
import entityCreator.EntityType;

@Stateless
@LocalBean
public class EntityPersistenceDistribution {
	
	
	
	@EJB
	private Base_DataDAO bd;
	@EJB
  	private FailureDAO f;
	@EJB
	private OperatorDAO op;
	@EJB
	private User_EquipmentDAO ue;
	@EJB
	private 
	Event_CauseDAO ec;
	
	
	
	public void find(Object entity, EntityType e){
		System.out.println("in find");
		switch (e)
		{ 
		  case BASEDATA: 
		        bd.addBase_Data(entity);
		        break;
		  case FAILURE:
			  System.out.println("inside switch statement failure");
		        f.addFailure((Failure) entity);
		        break;   
		  case EVENTCAUSE:
			  	
		        ec.addEvent_Cause(entity);
		        break;
		  case OPERATOR:
			  	
		        op.addOperator(entity);
		        break;   
		  case USEREQUIPMENT:
			  	
		        ue.addUser_Equipment(entity);  
		        break;
		  default:
		  System.out.println("no entities found");
	        
			break;
		        
		}
		
		
		
	}
}
