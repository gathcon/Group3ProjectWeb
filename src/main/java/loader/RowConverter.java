package loader;
 
import java.math.BigInteger;
 
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
 
import model.Base_Data;
import model.EntityType;
import model.Event_Cause;
import model.Event_CausePK;
import model.Failure;
import model.Operator;
import model.OperatorPK;
import model.TableRow;
import model.User_Equipment;
 
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
 
import dao.Event_CauseDAO;
import dao.FailureDAO;
import dao.OperatorDAO;
import dao.User_EquipmentDAO;
 
 
@Stateless
@LocalBean
public class RowConverter {
 
 
    @EJB private FailureDAO failureDAO;
    @EJB private User_EquipmentDAO user_EquipmentDAO;
    @EJB private OperatorDAO operatorDAO;
    @EJB private Event_CauseDAO event_CauseDAO;
   
    public TableRow convert(HSSFRow row, EntityType e) {
 
        switch (e) {
       
        case FAILURE:
            Failure failure = new Failure();
            failure.setFailureId((int)row.getCell(0).getNumericCellValue());
            failure.setDescription(row.getCell(1).getStringCellValue());
            return failure;
           
        case EVENTCAUSE:
           
            Event_Cause eventCause = new Event_Cause();
           
            Event_CausePK eventCauseID = new Event_CausePK((int)row.getCell(0).getNumericCellValue(),
                    (int)row.getCell(1).getNumericCellValue());
           
            eventCause.setId(eventCauseID);
            eventCause.setDescription(row.getCell(2).getStringCellValue());
           
            return eventCause;
           
        case OPERATOR:
           
            Operator operator = new Operator();
           
            OperatorPK operatorID = new OperatorPK();
            operatorID.setMcc((int)row.getCell(0).getNumericCellValue());
            operatorID.setMnc((int)row.getCell(1).getNumericCellValue());
           
            operator.setId(operatorID);
            operator.setOperatorName(row.getCell(3).getStringCellValue());
            operator.setCountry(row.getCell(2).getStringCellValue());
           
            return operator;
           
           
        case USEREQUIPMENT:
           
            User_Equipment userEquipment = new User_Equipment();
           
            userEquipment.setUser_EquipmentId((int)row.getCell(0).getNumericCellValue());
            userEquipment.setMarketingName(row.getCell(1).getStringCellValue());
            userEquipment.setManufacturer(row.getCell(2).getStringCellValue());
            userEquipment.setAccessCapability(row.getCell(3).getStringCellValue());
            userEquipment.setModel(row.getCell(4).getStringCellValue());
            userEquipment.setVendorName(row.getCell(5).getStringCellValue());
            userEquipment.setUeType(row.getCell(6).getStringCellValue());
            userEquipment.setOs(row.getCell(7).getStringCellValue());
            userEquipment.setInputMode(row.getCell(8).getStringCellValue());
           
            return userEquipment;
           
        case BASEDATA:
           
            Base_Data baseData = new Base_Data();
            
            Event_CausePK ecPK = new Event_CausePK((int)row.getCell(8).getNumericCellValue(), (int)row.getCell(1).getNumericCellValue());
            Event_Cause bdEventCause = event_CauseDAO.getEvent_Cause(ecPK);
           
            Failure bdFailure = failureDAO.getFailure((int)row.getCell(2).getNumericCellValue());
           
            User_Equipment bdUserEquipment = user_EquipmentDAO.getUser_Equipment((int)row.getCell(3).getNumericCellValue());
           
            OperatorPK oPK = new OperatorPK((int)row.getCell(4).getNumericCellValue(), (int)row.getCell(5).getNumericCellValue());
            Operator bdOperator = operatorDAO.getOperator(oPK);
           
            baseData.setDateTime(HSSFDateUtil.getJavaDate(row.getCell(0).getNumericCellValue()));
            baseData.setEventCause(bdEventCause);
            baseData.setFailure(bdFailure);
            baseData.setUserEquipment(bdUserEquipment);
            baseData.setOperator(bdOperator);
            baseData.setCellId((int)row.getCell(6).getNumericCellValue());
            baseData.setDuration((int)row.getCell(7).getNumericCellValue());
            baseData.setNeVersion(row.getCell(9).getStringCellValue());
            baseData.setImsi(BigInteger.valueOf((long) row.getCell(10).getNumericCellValue()));
            baseData.setHier3Id(BigInteger.valueOf((long) row.getCell(11).getNumericCellValue()));
            baseData.setHier32Id(BigInteger.valueOf((long) row.getCell(12).getNumericCellValue()));
            baseData.setHier321Id(BigInteger.valueOf((long) row.getCell(13).getNumericCellValue()));
       
        return baseData;
       
        default:
            return null;
 
        }
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