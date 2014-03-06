package entityCreator;

import java.math.BigInteger;
import java.util.Date;

import convertedRow.ConvertedRow;
import convertedRow.ConvertedRowBaseData;
import convertedRow.ConvertedRowEventCause;
import convertedRow.ConvertedRowFailure;
import convertedRow.ConvertedRowOperator;
import convertedRow.ConvertedRowUser;
import convertedRow.ConvertedRowUserEquipment;
import model.Base_Data;
import model.Event_Cause;
import model.Event_CausePK;
import model.Failure;
import model.TableRow;
import model.Operator;
import model.OperatorPK;
import model.User;
import model.User_Equipment;

public class EntityCreator {

	public static TableRow getEntity(ConvertedRow cr) {
		
		TableRow row = null;

		if (cr instanceof ConvertedRowBaseData) {
			ConvertedRowBaseData convertedRowBD = (ConvertedRowBaseData) cr;
			row = getBase_Data(convertedRowBD.getCellId(), convertedRowBD.getDateTime(), convertedRowBD.getDuration(),
					convertedRowBD.getHier3Id(), convertedRowBD.getHier32Id(), convertedRowBD.getHier321Id(),
					convertedRowBD.getImsi(), convertedRowBD.getNeVersion(), convertedRowBD.getFailure(),
					convertedRowBD.getUserEquipment(), convertedRowBD.getEventCause(), convertedRowBD.getOperator());

		} else if (cr instanceof ConvertedRowEventCause) {
			ConvertedRowEventCause convertedRowEC = (ConvertedRowEventCause) cr;
			row = getEvent_Cause(convertedRowEC.getCausecode(), convertedRowEC.getEventid(), convertedRowEC.getDecription());

		} else if (cr instanceof ConvertedRowFailure) {
			ConvertedRowFailure convertedRowF = (ConvertedRowFailure) cr;
			row = getFailure(convertedRowF.getFailureid(), convertedRowF.getDescription());

		} else if (cr instanceof ConvertedRowUserEquipment) {
			ConvertedRowUserEquipment convertedRowUE = (ConvertedRowUserEquipment) cr;
			row = getUser_Equipment(convertedRowUE.getTac(), convertedRowUE.getMarketingname(),
					convertedRowUE.getManufacturer(), convertedRowUE.getAccesscapability(),
					convertedRowUE.getModel(), convertedRowUE.getVendorname(), convertedRowUE.getUetype(),
					convertedRowUE.getOs(), convertedRowUE.getInput_mode());

		} else if (cr instanceof ConvertedRowOperator) {
			ConvertedRowOperator convertedRowO = (ConvertedRowOperator) cr;
			row = getOperator(convertedRowO.getMcc(), convertedRowO.getMnc(), convertedRowO.getCountry(),
					convertedRowO.getOperator());
		
		} else if (cr instanceof ConvertedRowUser) {
			ConvertedRowUser convertedRowU = (ConvertedRowUser) cr;
			row = getUser(convertedRowU.getUserName(), convertedRowU.getPassword(), convertedRowU.getUserType());
		}
		
		return row;
	}

	public static Failure getFailure(int id, String description) {

		Failure row = new Failure();
		row.setFailureId(id);
		row.setDescription(description);

		return row;

	}

	public static Operator getOperator(int mcc, int mnc, String country,
			String operatorName) {

		OperatorPK id = new OperatorPK(mcc, mnc);

		Operator row = new Operator();
		row.setId(id);
		row.setCountry(country);
		row.setOperatorName(operatorName);

		return row;

	}

	public static Event_Cause getEvent_Cause(int causeCode, int id,
			String description) {

		Event_Cause row = new Event_Cause();
		Event_CausePK pkid = new Event_CausePK(causeCode, id);
		row.setId(pkid);
		row.setDescription(description);

		return row;

	}
	
	public static User getUser(String userName, String password, String userType) {

		User row = new User();
		row.setUserName(userName);
		row.setPassword(password);
		row.setUserType(userType);

		return row;
	}

	public static User_Equipment getUser_Equipment(int id,
			String marketingName, String manufacturer, String accessCapability,
			String model, String vendorName, String ueType, String os,
			String inputMode) {

		User_Equipment row = new User_Equipment();
		row.setUser_EquipmentId(id);
		row.setMarketingName(marketingName);
		row.setManufacturer(manufacturer);
		row.setAccessCapability(accessCapability);
		row.setModel(model);
		row.setVendorName(vendorName);
		row.setUeType(ueType);
		row.setOs(os);
		row.setInputMode(inputMode);

		return row;

	}

	public static Base_Data getBase_Data(int cellId, Date dateTime,
			int duration, BigInteger hier3Id, BigInteger hier32Id,
			BigInteger hier321Id, BigInteger imsi, String neVersion,
			Failure failure, User_Equipment userEquipment,
			Event_Cause eventCause, Operator operator) {

		Base_Data row = new Base_Data();
		row.setCellId(cellId);
		row.setDateTime(dateTime);
		row.setDuration(duration);
		row.setHier3Id(hier3Id);
		row.setHier32Id(hier32Id);
		row.setHier321Id(hier321Id);
		row.setImsi(imsi);
		row.setNeVersion(neVersion);
		row.setFailure(failure);
		row.setUserEquipment(userEquipment);
		row.setEventCause(eventCause);
		row.setOperator(operator);

		return row;
	}

}
