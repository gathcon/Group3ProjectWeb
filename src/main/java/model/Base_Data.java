package model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigInteger;
import java.util.Date;

@Entity
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Base_Data.findEventID_CauseCodeByImsi", query = "SELECT b.eventCause FROM Base_Data b where b.imsi=:imsi Group by b.eventCause"),
		@NamedQuery(name = "Base_Data.findAll", query = "SELECT b FROM Base_Data b"),
		@NamedQuery(name = "Base_Data.findById", query = "SELECT b FROM Base_Data b where b.dataId=:dataId"),
		@NamedQuery(name = "Base_Data.findIMSIByFailureId", query = "SELECT b FROM Base_Data b where b.failure.failureId=:failureId GROUP BY b.imsi"),
		@NamedQuery(name = "Base_Data.findIMSIByDateRange", query = "SELECT b FROM Base_Data b WHERE b.dateTime BETWEEN :startDate AND :endDate GROUP BY b.imsi"),
		@NamedQuery(name = "Base_Data.findAllPhoneModels", query = "SELECT b FROM Base_Data b GROUP BY b.userEquipment.model"),
		@NamedQuery(name = "Base_Data.findFailureCountByModelAndByDateRange", query = "SELECT COUNT(b) FROM Base_Data b WHERE (b.userEquipment.model=:model) AND (b.dateTime BETWEEN :startDate AND :endDate)"),
		@NamedQuery(name = "Base_Data.findFailuresDurationForIMSIsBetweenDates", query = "SELECT b.imsi, COUNT(b), SUM(b.duration) FROM Base_Data b WHERE (b.dateTime BETWEEN :startDate AND :endDate) GROUP BY b.imsi"),
		@NamedQuery(name = "Base_Data.findEventID_CauseCodeByModel", query = "SELECT b.eventCause, COUNT(b) FROM Base_Data b where b.userEquipment.model=:model Group by b.eventCause"),
		@NamedQuery(name = "Base_Data.findFailuresByImsiAndByDateRange", query = "SELECT COUNT(b) FROM Base_Data b WHERE (b.imsi=:imsi) AND (b.dateTime BETWEEN :startDate AND :endDate)"),
		@NamedQuery(name = "Base_Data.findUniqueCauseCodesByIMSI", query = "SELECT b.eventCause.id.causeCode, COUNT(b.eventCause.id.causeCode) FROM Base_Data b where b.imsi=:imsi GROUP BY b.eventCause.id.causeCode"),
		@NamedQuery(name = "Base_Data.findTop10ImsisWithinDateRange", query = "SELECT b.imsi, COUNT( b.imsi) FROM Base_Data b WHERE b.dateTime BETWEEN :startDate AND :endDate GROUP BY b.imsi"),
		@NamedQuery(name = "Base_Data.findTop10CombinationsByDateRange", query = "SELECT b.operator.id.mcc, b.operator.id.mnc, b.cellId, COUNT(b) FROM Base_Data b WHERE b.dateTime BETWEEN :startDate AND :endDate GROUP BY b.operator.id.mcc, b.operator.id.mnc, b.cellId"),
		@NamedQuery(name = "Base_Data.findAllImsiS", query = "SELECT DISTINCT b.imsi FROM Base_Data b ORDER BY b.imsi"),
		@NamedQuery(name = "Base_Data.findAllImsis", query = "SELECT b FROM Base_Data b GROUP BY b.imsi")

})
public class Base_Data extends TableRow implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "data_id", nullable = false)
	private int dataId;

	@Column(name = "cell_id")
	private int cellId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_time")
	private Date dateTime;

	private int duration;

	@Column(name = "hier3_id")
	private BigInteger hier3Id;

	@Column(name = "hier32_id")
	private BigInteger hier32Id;

	@Column(name = "hier321_id")
	private BigInteger hier321Id;

	private BigInteger imsi;

	@Column(name = "ne_version")
	private String neVersion;

	@ManyToOne
	@JoinColumn(name = "failure_id")
	private Failure failure;

	@ManyToOne
	@JoinColumn(name = "ue_id")
	private User_Equipment userEquipment;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "cause_code", referencedColumnName = "cause_code"),
			@JoinColumn(name = "event_id", referencedColumnName = "event_id") })
	private Event_Cause eventCause;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "mcc", referencedColumnName = "mcc"),
			@JoinColumn(name = "mnc", referencedColumnName = "mnc") })
	private Operator operator;

	public Base_Data() {
	}

	public int getDataId() {
		return this.dataId;
	}

	public int getCellId() {
		return this.cellId;
	}

	public void setCellId(int cellId) {
		this.cellId = cellId;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public BigInteger getHier3Id() {
		return this.hier3Id;
	}

	public void setHier3Id(BigInteger hier3Id) {
		this.hier3Id = hier3Id;
	}

	public BigInteger getHier32Id() {
		return this.hier32Id;
	}

	public void setHier32Id(BigInteger hier32Id) {
		this.hier32Id = hier32Id;
	}

	public BigInteger getHier321Id() {
		return this.hier321Id;
	}

	public void setHier321Id(BigInteger hier321Id) {
		this.hier321Id = hier321Id;
	}

	public BigInteger getImsi() {
		return this.imsi;
	}

	public void setImsi(BigInteger imsi) {
		this.imsi = imsi;
	}

	public String getNeVersion() {
		return this.neVersion;
	}

	public void setNeVersion(String neVersion) {
		this.neVersion = neVersion;
	}

	public Failure getFailure() {
		return this.failure;
	}

	public void setFailure(Failure failure) {
		this.failure = failure;
	}

	public User_Equipment getUserEquipment() {
		return this.userEquipment;
	}

	public void setUserEquipment(User_Equipment userEquipment) {
		this.userEquipment = userEquipment;
	}

	public Event_Cause getEventCause() {
		return this.eventCause;
	}

	public void setEventCause(Event_Cause eventCause) {
		this.eventCause = eventCause;
	}

	public Operator getOperator() {
		return this.operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}
}
