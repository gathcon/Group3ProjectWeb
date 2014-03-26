package convertedRow;

import java.math.BigInteger;
import java.util.Date;

import dao.Event_CauseDAO;
import dao.FailureDAO;
import dao.OperatorDAO;
import dao.User_EquipmentDAO;
import model.Event_Cause;
import model.Event_CausePK;
import model.Failure;
import model.Operator;
import model.OperatorPK;
import model.User_Equipment;

public class ConvertedRowBaseData extends ConvertedRow {
	
	private int cellId;
	private int duration;
	private BigInteger hier3Id;
	private BigInteger hier32Id;
	private BigInteger hier321Id;
	private BigInteger imsi;
	private Date dateTime;
	private String neVersion;
	private Failure failure;
	private int failureId;
	
	private Event_Cause eventCause;
	private int eventId;
	private int causeId;
	
	private User_Equipment userEquipment;
	private int userEquipmentId;
	
	private Operator operator;
	private int mcc;
	private int mnc;
	
	public ConvertedRowBaseData(int cellId, int duration, BigInteger hier3Id,
			BigInteger hier32Id, BigInteger hier321Id, BigInteger imsi,
			Date dateTime, String neVersion, int failureclassid, int eventid, 
			int causeid, int uetype, int mcc, int mnc) {
		
		this.cellId = cellId;
		this.duration = duration;
		this.hier3Id = hier3Id;
		this.hier32Id = hier32Id;
		this.hier321Id = hier321Id;
		this.imsi = imsi;
		this.dateTime = dateTime;
		this.neVersion = neVersion;
		
		this.failureId = failureclassid;
		FailureDAO fd = new FailureDAO();
		failure = fd.getFailure(failureId);
		
		this.eventId = eventid;
		this.causeId = causeid;
		Event_CausePK ecPK = new Event_CausePK(causeId, eventId);
		Event_CauseDAO ecd = new Event_CauseDAO();
		eventCause = ecd.getEvent_Cause(ecPK);
		
		this.userEquipmentId = uetype;
		User_EquipmentDAO ued = new User_EquipmentDAO();
		userEquipment = ued.getUser_Equipment(userEquipmentId);
		
		this.mcc = mcc;
		this.mnc = mnc;
		OperatorPK oPK = new OperatorPK(mcc, mnc);
		OperatorDAO od = new OperatorDAO();
		operator = od.getOperator(oPK);
	}

	public int getCellId() {
		return cellId;
	}

	public void setCellId(int cellId) {
		this.cellId = cellId;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public BigInteger getHier3Id() {
		return hier3Id;
	}

	public void setHier3Id(BigInteger hier3Id) {
		this.hier3Id = hier3Id;
	}

	public BigInteger getHier32Id() {
		return hier32Id;
	}

	public void setHier32Id(BigInteger hier32Id) {
		this.hier32Id = hier32Id;
	}

	public BigInteger getHier321Id() {
		return hier321Id;
	}

	public void setHier321Id(BigInteger hier321Id) {
		this.hier321Id = hier321Id;
	}

	public BigInteger getImsi() {
		return imsi;
	}

	public void setImsi(BigInteger imsi) {
		this.imsi = imsi;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getNeVersion() {
		return neVersion;
	}

	public void setNeVersion(String neVersion) {
		this.neVersion = neVersion;
	}

	public Failure getFailure() {
		return failure;
	}

	public void setFailure(Failure failure) {
		this.failure = failure;
	}

	public int getFailureId() {
		return failureId;
	}

	public void setFailureId(int failureId) {
		this.failureId = failureId;
	}

	public Event_Cause getEventCause() {
		return eventCause;
	}

	public void setEventCause(Event_Cause eventCause) {
		this.eventCause = eventCause;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getCauseId() {
		return causeId;
	}

	public void setCauseId(int causeId) {
		this.causeId = causeId;
	}

	public User_Equipment getUserEquipment() {
		return userEquipment;
	}

	public void setUserEquipment(User_Equipment userEquipment) {
		this.userEquipment = userEquipment;
	}

	public int getUserEquipmentId() {
		return userEquipmentId;
	}

	public void setUserEquipmentId(int userEquipmentId) {
		this.userEquipmentId = userEquipmentId;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public int getMcc() {
		return mcc;
	}

	public void setMcc(int mcc) {
		this.mcc = mcc;
	}

	public int getMnc() {
		return mnc;
	}

	public void setMnc(int mnc) {
		this.mnc = mnc;
	}
}
