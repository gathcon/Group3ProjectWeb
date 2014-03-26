package test;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Date;

import javax.ejb.EJB;

import jaxrs.Base_DataWS;
import model.Base_Data;
import model.Event_Cause;
import model.Event_CausePK;
import model.Failure;
import model.Operator;
import model.OperatorPK;
import model.User_Equipment;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import dao.Base_DataDAO;
import dao.Event_CauseDAO;
import dao.FailureDAO;
import dao.OperatorDAO;
import dao.User_EquipmentDAO;

@RunWith(Arquillian.class)
public class NetManagerUserStoryTest {
	@Deployment
	public static JavaArchive createTestArchive() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addPackages(true, "dao", "jaxrs", "model", "restApp")
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB private Base_DataDAO baseDataDAO;
	@EJB private FailureDAO failureDAO;
	@EJB private OperatorDAO operatorDAO;
	@EJB private User_EquipmentDAO user_EquipmentDAO;
	@EJB private Event_CauseDAO event_CauseDAO;

	@EJB private Base_DataWS baseDataWS;

	private Base_Data bd,bd1,bd2,bd3,bd4,bd5,bd6,bd7,bd8,
	
				bd9,bd10,bd11, bd12,bd13,bd14,bd15,bd16,
				
				bd17,bd18,bd19,bd20,bd21,bd22,bd23,
				
				bd24,bd25,bd26,bd27,bd28,bd29,
				
				bd30,bd31,bd32,bd33,bd34,
				
				bd35,bd36,bd37,bd38,
				
				bd39,bd40,bd41,
				
				bd42,bd43,
				
				bd44,bd45,bd46,
				
				bd47,bd48,bd49,bd50,bd51,bd52,bd53,bd54,bd55,bd56;
	
	
	private Failure f;
	private Operator o;
	private User_Equipment ue;
	private Event_Cause ec;

	@Before
	public void setupBaseData() {
		f = new Failure();
		f.setFailureId(0);
		f.setDescription("Test Failure");

		o = new Operator();
		OperatorPK id = new OperatorPK(340, 930);
		o.setId(id);
		o.setCountry("ireland");
		o.setOperatorName("vodafone");

		ec = new Event_Cause();
		Event_CausePK pkid = new Event_CausePK(0, 4097);
		ec.setId(pkid);
		ec.setDescription("RRC CONN SETUP-SUCCESS");

		ue = new User_Equipment();
		ue.setUser_EquipmentId(100100);
		ue.setMarketingName("marketingName");
		ue.setManufacturer("manufacturer");
		ue.setAccessCapability("accessCapability");
		ue.setModel("model");
		ue.setVendorName("vendorName");
		ue.setUeType("ueType");
		ue.setOs("os");
		ue.setInputMode("inputMode");

		//9 records for imsi 12
		bd = new Base_Data();bd.setCellId(4);bd.setDateTime(new Date(113,0,11,17,46,1));bd.setDuration(1000);bd.setHier3Id(new BigInteger("1234"));bd.setHier32Id(new BigInteger("1234"));bd.setHier321Id(new BigInteger("1234"));bd.setImsi(new BigInteger("344930000000012"));bd.setNeVersion("neVersion");bd.setFailure(f);bd.setUserEquipment(ue);bd.setEventCause(ec);bd.setOperator(o);
		bd1 = new Base_Data();bd1.setCellId(4);bd1.setDateTime(new Date(113,0,11,17,46,1));bd1.setDuration(1000);bd1.setHier3Id(new BigInteger("1234"));bd1.setHier32Id(new BigInteger("1234"));bd1.setHier321Id(new BigInteger("1234"));bd1.setImsi(new BigInteger("344930000000012"));bd1.setNeVersion("neVersion");bd1.setFailure(f);bd1.setUserEquipment(ue);bd1.setEventCause(ec);bd1.setOperator(o);
		bd2 = new Base_Data();bd2.setCellId(4);bd2.setDateTime(new Date(113,0,11,17,46,1));bd2.setDuration(1000);bd2.setHier3Id(new BigInteger("1234"));bd2.setHier32Id(new BigInteger("1234"));bd2.setHier321Id(new BigInteger("1234"));bd2.setImsi(new BigInteger("344930000000012"));bd2.setNeVersion("neVersion");bd2.setFailure(f);bd2.setUserEquipment(ue);bd2.setEventCause(ec);bd2.setOperator(o);
		bd3 = new Base_Data();bd3.setCellId(4);bd3.setDateTime(new Date(113,0,11,17,46,1));bd3.setDuration(1000);bd3.setHier3Id(new BigInteger("1234"));bd3.setHier32Id(new BigInteger("1234"));bd3.setHier321Id(new BigInteger("1234"));bd3.setImsi(new BigInteger("344930000000012"));bd3.setNeVersion("neVersion");bd3.setFailure(f);bd3.setUserEquipment(ue);bd3.setEventCause(ec);bd3.setOperator(o);
		bd4 = new Base_Data();bd4.setCellId(4);bd4.setDateTime(new Date(113,0,11,17,46,1));bd4.setDuration(1000);bd4.setHier3Id(new BigInteger("1234"));bd4.setHier32Id(new BigInteger("1234"));bd4.setHier321Id(new BigInteger("1234"));bd4.setImsi(new BigInteger("344930000000012"));bd4.setNeVersion("neVersion");bd4.setFailure(f);bd4.setUserEquipment(ue);bd4.setEventCause(ec);bd4.setOperator(o);
		bd5 = new Base_Data();bd5.setCellId(4);bd5.setDateTime(new Date(113,0,11,17,46,1));bd5.setDuration(1000);bd5.setHier3Id(new BigInteger("1234"));bd5.setHier32Id(new BigInteger("1234"));bd5.setHier321Id(new BigInteger("1234"));bd5.setImsi(new BigInteger("344930000000012"));bd5.setNeVersion("neVersion");bd5.setFailure(f);bd5.setUserEquipment(ue);bd5.setEventCause(ec);bd5.setOperator(o);
		bd6 = new Base_Data();bd6.setCellId(4);bd6.setDateTime(new Date(113,0,11,17,46,1));bd6.setDuration(1000);bd6.setHier3Id(new BigInteger("1234"));bd6.setHier32Id(new BigInteger("1234"));bd6.setHier321Id(new BigInteger("1234"));bd6.setImsi(new BigInteger("344930000000012"));bd6.setNeVersion("neVersion");bd6.setFailure(f);bd6.setUserEquipment(ue);bd6.setEventCause(ec);bd6.setOperator(o);
		bd7 = new Base_Data();bd7.setCellId(4);bd7.setDateTime(new Date(113,0,11,17,46,1));bd7.setDuration(1000);bd7.setHier3Id(new BigInteger("1234"));bd7.setHier32Id(new BigInteger("1234"));bd7.setHier321Id(new BigInteger("1234"));bd7.setImsi(new BigInteger("344930000000012"));bd7.setNeVersion("neVersion");bd7.setFailure(f);bd7.setUserEquipment(ue);bd7.setEventCause(ec);bd7.setOperator(o);
		bd8 = new Base_Data();bd8.setCellId(4);bd8.setDateTime(new Date(113,0,11,17,46,1));bd8.setDuration(1000);bd8.setHier3Id(new BigInteger("1234"));bd8.setHier32Id(new BigInteger("1234"));bd8.setHier321Id(new BigInteger("1234"));bd8.setImsi(new BigInteger("344930000000012"));bd8.setNeVersion("neVersion");bd8.setFailure(f);bd8.setUserEquipment(ue);bd8.setEventCause(ec);bd8.setOperator(o);
		
		//8 records for imsi 13
		bd9 = new Base_Data();bd9.setCellId(4);bd9.setDateTime(new Date(113,0,11,17,46,1));bd9.setDuration(1000);bd9.setHier3Id(new BigInteger("1234"));bd9.setHier32Id(new BigInteger("1234"));bd9.setHier321Id(new BigInteger("1234"));bd9.setImsi(new BigInteger("344930000000013"));bd9.setNeVersion("neVersion");bd9.setFailure(f);bd9.setUserEquipment(ue);bd9.setEventCause(ec);bd9.setOperator(o);
		bd10 = new Base_Data();bd10.setCellId(4);bd10.setDateTime(new Date(113,0,11,17,46,1));bd10.setDuration(1000);bd10.setHier3Id(new BigInteger("1234"));bd10.setHier32Id(new BigInteger("1234"));bd10.setHier321Id(new BigInteger("1234"));bd10.setImsi(new BigInteger("344930000000013"));bd10.setNeVersion("neVersion");bd10.setFailure(f);bd10.setUserEquipment(ue);bd10.setEventCause(ec);bd10.setOperator(o);
		bd11 = new Base_Data();bd11.setCellId(4);bd11.setDateTime(new Date(113,0,11,17,46,1));bd11.setDuration(1000);bd11.setHier3Id(new BigInteger("1234"));bd11.setHier32Id(new BigInteger("1234"));bd11.setHier321Id(new BigInteger("1234"));bd11.setImsi(new BigInteger("344930000000013"));bd11.setNeVersion("neVersion");bd11.setFailure(f);bd11.setUserEquipment(ue);bd11.setEventCause(ec);bd11.setOperator(o);
		bd12 = new Base_Data();bd12.setCellId(4);bd12.setDateTime(new Date(113,0,11,17,46,1));bd12.setDuration(1000);bd12.setHier3Id(new BigInteger("1234"));bd12.setHier32Id(new BigInteger("1234"));bd12.setHier321Id(new BigInteger("1234"));bd12.setImsi(new BigInteger("344930000000013"));bd12.setNeVersion("neVersion");bd12.setFailure(f);bd12.setUserEquipment(ue);bd12.setEventCause(ec);bd12.setOperator(o);
		bd13 = new Base_Data();bd13.setCellId(4);bd13.setDateTime(new Date(113,0,11,17,46,1));bd13.setDuration(1000);bd13.setHier3Id(new BigInteger("1234"));bd13.setHier32Id(new BigInteger("1234"));bd13.setHier321Id(new BigInteger("1234"));bd13.setImsi(new BigInteger("344930000000013"));bd13.setNeVersion("neVersion");bd13.setFailure(f);bd13.setUserEquipment(ue);bd13.setEventCause(ec);bd13.setOperator(o);
		bd14 = new Base_Data();bd14.setCellId(4);bd14.setDateTime(new Date(113,0,11,17,46,1));bd14.setDuration(1000);bd14.setHier3Id(new BigInteger("1234"));bd14.setHier32Id(new BigInteger("1234"));bd14.setHier321Id(new BigInteger("1234"));bd14.setImsi(new BigInteger("344930000000013"));bd14.setNeVersion("neVersion");bd14.setFailure(f);bd14.setUserEquipment(ue);bd14.setEventCause(ec);bd14.setOperator(o);
		bd15 = new Base_Data();bd15.setCellId(4);bd15.setDateTime(new Date(113,0,11,17,46,1));bd15.setDuration(1000);bd15.setHier3Id(new BigInteger("1234"));bd15.setHier32Id(new BigInteger("1234"));bd15.setHier321Id(new BigInteger("1234"));bd15.setImsi(new BigInteger("344930000000013"));bd15.setNeVersion("neVersion");bd15.setFailure(f);bd15.setUserEquipment(ue);bd15.setEventCause(ec);bd15.setOperator(o);
		bd16 = new Base_Data();bd16.setCellId(4);bd16.setDateTime(new Date(113,0,11,17,46,1));bd16.setDuration(1000);bd16.setHier3Id(new BigInteger("1234"));bd16.setHier32Id(new BigInteger("1234"));bd16.setHier321Id(new BigInteger("1234"));bd16.setImsi(new BigInteger("344930000000013"));bd16.setNeVersion("neVersion");bd16.setFailure(f);bd16.setUserEquipment(ue);bd16.setEventCause(ec);bd16.setOperator(o);
		
		//7 records for imsi 14
		bd17 = new Base_Data();bd17.setCellId(4);bd17.setDateTime(new Date(113,0,11,17,46,1));bd17.setDuration(1000);bd17.setHier3Id(new BigInteger("1234"));bd17.setHier32Id(new BigInteger("1234"));bd17.setHier321Id(new BigInteger("1234"));bd17.setImsi(new BigInteger("344930000000014"));bd17.setNeVersion("neVersion");bd17.setFailure(f);bd17.setUserEquipment(ue);bd17.setEventCause(ec);bd17.setOperator(o);
		bd18 = new Base_Data();bd18.setCellId(4);bd18.setDateTime(new Date(113,0,11,17,46,1));bd18.setDuration(1000);bd18.setHier3Id(new BigInteger("1234"));bd18.setHier32Id(new BigInteger("1234"));bd18.setHier321Id(new BigInteger("1234"));bd18.setImsi(new BigInteger("344930000000014"));bd18.setNeVersion("neVersion");bd18.setFailure(f);bd18.setUserEquipment(ue);bd18.setEventCause(ec);bd18.setOperator(o);
		bd19 = new Base_Data();bd19.setCellId(4);bd19.setDateTime(new Date(113,0,11,17,46,1));bd19.setDuration(1000);bd19.setHier3Id(new BigInteger("1234"));bd19.setHier32Id(new BigInteger("1234"));bd19.setHier321Id(new BigInteger("1234"));bd19.setImsi(new BigInteger("344930000000014"));bd19.setNeVersion("neVersion");bd19.setFailure(f);bd19.setUserEquipment(ue);bd19.setEventCause(ec);bd19.setOperator(o);
		bd20 = new Base_Data();bd20.setCellId(4);bd20.setDateTime(new Date(113,0,11,17,46,1));bd20.setDuration(1000);bd20.setHier3Id(new BigInteger("1234"));bd20.setHier32Id(new BigInteger("1234"));bd20.setHier321Id(new BigInteger("1234"));bd20.setImsi(new BigInteger("344930000000014"));bd20.setNeVersion("neVersion");bd20.setFailure(f);bd20.setUserEquipment(ue);bd20.setEventCause(ec);bd20.setOperator(o);
		bd21 = new Base_Data();bd21.setCellId(4);bd21.setDateTime(new Date(113,0,11,17,46,1));bd21.setDuration(1000);bd21.setHier3Id(new BigInteger("1234"));bd21.setHier32Id(new BigInteger("1234"));bd21.setHier321Id(new BigInteger("1234"));bd21.setImsi(new BigInteger("344930000000014"));bd21.setNeVersion("neVersion");bd21.setFailure(f);bd21.setUserEquipment(ue);bd21.setEventCause(ec);bd21.setOperator(o);
		bd22 = new Base_Data();bd22.setCellId(4);bd22.setDateTime(new Date(113,0,11,17,46,1));bd22.setDuration(1000);bd22.setHier3Id(new BigInteger("1234"));bd22.setHier32Id(new BigInteger("1234"));bd22.setHier321Id(new BigInteger("1234"));bd22.setImsi(new BigInteger("344930000000014"));bd22.setNeVersion("neVersion");bd22.setFailure(f);bd22.setUserEquipment(ue);bd22.setEventCause(ec);bd22.setOperator(o);
		bd23 = new Base_Data();bd23.setCellId(4);bd23.setDateTime(new Date(113,0,11,17,46,1));bd23.setDuration(1000);bd23.setHier3Id(new BigInteger("1234"));bd23.setHier32Id(new BigInteger("1234"));bd23.setHier321Id(new BigInteger("1234"));bd23.setImsi(new BigInteger("344930000000014"));bd23.setNeVersion("neVersion");bd23.setFailure(f);bd23.setUserEquipment(ue);bd23.setEventCause(ec);bd23.setOperator(o);
		
		//6 records for imsi 11
		bd24 = new Base_Data();bd24.setCellId(4);bd24.setDateTime(new Date(113,0,11,17,46,1));bd24.setDuration(1000);bd24.setHier3Id(new BigInteger("1234"));bd24.setHier32Id(new BigInteger("1234"));bd24.setHier321Id(new BigInteger("1234"));bd24.setImsi(new BigInteger("344930000000011"));bd24.setNeVersion("neVersion");bd24.setFailure(f);bd24.setUserEquipment(ue);bd24.setEventCause(ec);bd24.setOperator(o);
		bd25 = new Base_Data();bd25.setCellId(4);bd25.setDateTime(new Date(113,0,11,17,46,1));bd25.setDuration(1000);bd25.setHier3Id(new BigInteger("1234"));bd25.setHier32Id(new BigInteger("1234"));bd25.setHier321Id(new BigInteger("1234"));bd25.setImsi(new BigInteger("344930000000011"));bd25.setNeVersion("neVersion");bd25.setFailure(f);bd25.setUserEquipment(ue);bd25.setEventCause(ec);bd25.setOperator(o);
		bd26 = new Base_Data();bd26.setCellId(4);bd26.setDateTime(new Date(113,0,11,17,46,1));bd26.setDuration(1000);bd26.setHier3Id(new BigInteger("1234"));bd26.setHier32Id(new BigInteger("1234"));bd26.setHier321Id(new BigInteger("1234"));bd26.setImsi(new BigInteger("344930000000011"));bd26.setNeVersion("neVersion");bd26.setFailure(f);bd26.setUserEquipment(ue);bd26.setEventCause(ec);bd26.setOperator(o);
		bd27 = new Base_Data();bd27.setCellId(4);bd27.setDateTime(new Date(113,0,11,17,46,1));bd27.setDuration(1000);bd27.setHier3Id(new BigInteger("1234"));bd27.setHier32Id(new BigInteger("1234"));bd27.setHier321Id(new BigInteger("1234"));bd27.setImsi(new BigInteger("344930000000011"));bd27.setNeVersion("neVersion");bd27.setFailure(f);bd27.setUserEquipment(ue);bd27.setEventCause(ec);bd27.setOperator(o);
		bd28 = new Base_Data();bd28.setCellId(4);bd28.setDateTime(new Date(113,0,11,17,46,1));bd28.setDuration(1000);bd28.setHier3Id(new BigInteger("1234"));bd28.setHier32Id(new BigInteger("1234"));bd28.setHier321Id(new BigInteger("1234"));bd28.setImsi(new BigInteger("344930000000011"));bd28.setNeVersion("neVersion");bd28.setFailure(f);bd28.setUserEquipment(ue);bd28.setEventCause(ec);bd28.setOperator(o);
		bd29 = new Base_Data();bd29.setCellId(4);bd29.setDateTime(new Date(113,0,11,17,46,1));bd29.setDuration(1000);bd29.setHier3Id(new BigInteger("1234"));bd29.setHier32Id(new BigInteger("1234"));bd29.setHier321Id(new BigInteger("1234"));bd29.setImsi(new BigInteger("344930000000011"));bd29.setNeVersion("neVersion");bd29.setFailure(f);bd29.setUserEquipment(ue);bd29.setEventCause(ec);bd29.setOperator(o);
		
		//5 records for imsi 16
		bd30 = new Base_Data();bd30.setCellId(4);bd30.setDateTime(new Date(113,0,11,17,46,1));bd30.setDuration(1000);bd30.setHier3Id(new BigInteger("1234"));bd30.setHier32Id(new BigInteger("1234"));bd30.setHier321Id(new BigInteger("1234"));bd30.setImsi(new BigInteger("344930000000016"));bd30.setNeVersion("neVersion");bd30.setFailure(f);bd30.setUserEquipment(ue);bd30.setEventCause(ec);bd30.setOperator(o);
		bd31 = new Base_Data();bd31.setCellId(4);bd31.setDateTime(new Date(113,0,11,17,46,1));bd31.setDuration(1000);bd31.setHier3Id(new BigInteger("1234"));bd31.setHier32Id(new BigInteger("1234"));bd31.setHier321Id(new BigInteger("1234"));bd31.setImsi(new BigInteger("344930000000016"));bd31.setNeVersion("neVersion");bd31.setFailure(f);bd31.setUserEquipment(ue);bd31.setEventCause(ec);bd31.setOperator(o);
		bd32 = new Base_Data();bd32.setCellId(4);bd32.setDateTime(new Date(113,0,11,17,46,1));bd32.setDuration(1000);bd32.setHier3Id(new BigInteger("1234"));bd32.setHier32Id(new BigInteger("1234"));bd32.setHier321Id(new BigInteger("1234"));bd32.setImsi(new BigInteger("344930000000016"));bd32.setNeVersion("neVersion");bd32.setFailure(f);bd32.setUserEquipment(ue);bd32.setEventCause(ec);bd32.setOperator(o);
		bd33 = new Base_Data();bd33.setCellId(4);bd33.setDateTime(new Date(113,0,11,17,46,1));bd33.setDuration(1000);bd33.setHier3Id(new BigInteger("1234"));bd33.setHier32Id(new BigInteger("1234"));bd33.setHier321Id(new BigInteger("1234"));bd33.setImsi(new BigInteger("344930000000016"));bd33.setNeVersion("neVersion");bd33.setFailure(f);bd33.setUserEquipment(ue);bd33.setEventCause(ec);bd33.setOperator(o);
		bd34 = new Base_Data();bd34.setCellId(4);bd34.setDateTime(new Date(113,0,11,17,46,1));bd34.setDuration(1000);bd34.setHier3Id(new BigInteger("1234"));bd34.setHier32Id(new BigInteger("1234"));bd34.setHier321Id(new BigInteger("1234"));bd34.setImsi(new BigInteger("344930000000016"));bd34.setNeVersion("neVersion");bd34.setFailure(f);bd34.setUserEquipment(ue);bd34.setEventCause(ec);bd34.setOperator(o);

		//4 records for imsi 17
		bd35 = new Base_Data();bd35.setCellId(4);bd35.setDateTime(new Date(113,0,11,17,46,1));bd35.setDuration(1000);bd35.setHier3Id(new BigInteger("1234"));bd35.setHier32Id(new BigInteger("1234"));bd35.setHier321Id(new BigInteger("1234"));bd35.setImsi(new BigInteger("344930000000017"));bd35.setNeVersion("neVersion");bd35.setFailure(f);bd35.setUserEquipment(ue);bd35.setEventCause(ec);bd35.setOperator(o);
		bd36 = new Base_Data();bd36.setCellId(4);bd36.setDateTime(new Date(113,0,11,17,46,1));bd36.setDuration(1000);bd36.setHier3Id(new BigInteger("1234"));bd36.setHier32Id(new BigInteger("1234"));bd36.setHier321Id(new BigInteger("1234"));bd36.setImsi(new BigInteger("344930000000017"));bd36.setNeVersion("neVersion");bd36.setFailure(f);bd36.setUserEquipment(ue);bd36.setEventCause(ec);bd36.setOperator(o);
		bd37 = new Base_Data();bd37.setCellId(4);bd37.setDateTime(new Date(113,0,11,17,46,1));bd37.setDuration(1000);bd37.setHier3Id(new BigInteger("1234"));bd37.setHier32Id(new BigInteger("1234"));bd37.setHier321Id(new BigInteger("1234"));bd37.setImsi(new BigInteger("344930000000017"));bd37.setNeVersion("neVersion");bd37.setFailure(f);bd37.setUserEquipment(ue);bd37.setEventCause(ec);bd37.setOperator(o);
		bd38 = new Base_Data();bd38.setCellId(4);bd38.setDateTime(new Date(113,0,11,17,46,1));bd38.setDuration(1000);bd38.setHier3Id(new BigInteger("1234"));bd38.setHier32Id(new BigInteger("1234"));bd38.setHier321Id(new BigInteger("1234"));bd38.setImsi(new BigInteger("344930000000017"));bd38.setNeVersion("neVersion");bd38.setFailure(f);bd38.setUserEquipment(ue);bd38.setEventCause(ec);bd38.setOperator(o);
		
		//3 records for imsi 18
		bd39 = new Base_Data();bd39.setCellId(4);bd39.setDateTime(new Date(113,0,11,17,46,1));bd39.setDuration(1000);bd39.setHier3Id(new BigInteger("1234"));bd39.setHier32Id(new BigInteger("1234"));bd39.setHier321Id(new BigInteger("1234"));bd39.setImsi(new BigInteger("344930000000018"));bd39.setNeVersion("neVersion");bd39.setFailure(f);bd39.setUserEquipment(ue);bd39.setEventCause(ec);bd39.setOperator(o);
		bd40 = new Base_Data();bd40.setCellId(4);bd40.setDateTime(new Date(113,0,11,17,46,1));bd40.setDuration(1000);bd40.setHier3Id(new BigInteger("1234"));bd40.setHier32Id(new BigInteger("1234"));bd40.setHier321Id(new BigInteger("1234"));bd40.setImsi(new BigInteger("344930000000018"));bd40.setNeVersion("neVersion");bd40.setFailure(f);bd40.setUserEquipment(ue);bd40.setEventCause(ec);bd40.setOperator(o);
		bd41 = new Base_Data();bd41.setCellId(4);bd41.setDateTime(new Date(113,0,11,17,46,1));bd41.setDuration(1000);bd41.setHier3Id(new BigInteger("1234"));bd41.setHier32Id(new BigInteger("1234"));bd41.setHier321Id(new BigInteger("1234"));bd41.setImsi(new BigInteger("344930000000018"));bd41.setNeVersion("neVersion");bd41.setFailure(f);bd41.setUserEquipment(ue);bd41.setEventCause(ec);bd41.setOperator(o);
		
		//2 records for imsi 19
		bd42 = new Base_Data();bd42.setCellId(4);bd42.setDateTime(new Date(113,0,11,17,46,1));bd42.setDuration(1000);bd42.setHier3Id(new BigInteger("1234"));bd42.setHier32Id(new BigInteger("1234"));bd42.setHier321Id(new BigInteger("1234"));bd42.setImsi(new BigInteger("344930000000019"));bd42.setNeVersion("neVersion");bd42.setFailure(f);bd42.setUserEquipment(ue);bd42.setEventCause(ec);bd42.setOperator(o);
		bd43 = new Base_Data();bd43.setCellId(4);bd43.setDateTime(new Date(113,0,11,17,46,1));bd43.setDuration(1000);bd43.setHier3Id(new BigInteger("1234"));bd43.setHier32Id(new BigInteger("1234"));bd43.setHier321Id(new BigInteger("1234"));bd43.setImsi(new BigInteger("344930000000019"));bd43.setNeVersion("neVersion");bd43.setFailure(f);bd43.setUserEquipment(ue);bd43.setEventCause(ec);bd43.setOperator(o);
		
		//1 record for imsi 20
		bd44 = new Base_Data();bd44.setCellId(4);bd44.setDateTime(new Date(113,0,11,17,46,1));bd44.setDuration(1000);bd44.setHier3Id(new BigInteger("1234"));bd44.setHier32Id(new BigInteger("1234"));bd44.setHier321Id(new BigInteger("1234"));bd44.setImsi(new BigInteger("344930000000020"));bd44.setNeVersion("neVersion");bd44.setFailure(f);bd44.setUserEquipment(ue);bd44.setEventCause(ec);bd44.setOperator(o);
		//1 record for imsi 21
		bd45 = new Base_Data();bd45.setCellId(4);bd45.setDateTime(new Date(113,0,11,17,46,1));bd45.setDuration(1000);bd45.setHier3Id(new BigInteger("1234"));bd45.setHier32Id(new BigInteger("1234"));bd45.setHier321Id(new BigInteger("1234"));bd45.setImsi(new BigInteger("344930000000021"));bd45.setNeVersion("neVersion");bd45.setFailure(f);bd45.setUserEquipment(ue);bd45.setEventCause(ec);bd45.setOperator(o);
		//1 record for imsi 22
		bd46 = new Base_Data();bd46.setCellId(4);bd46.setDateTime(new Date(113,0,11,17,46,1));bd46.setDuration(1000);bd46.setHier3Id(new BigInteger("1234"));bd46.setHier32Id(new BigInteger("1234"));bd46.setHier321Id(new BigInteger("1234"));bd46.setImsi(new BigInteger("344930000000022"));bd46.setNeVersion("neVersion");bd46.setFailure(f);bd46.setUserEquipment(ue);bd46.setEventCause(ec);bd46.setOperator(o);
		
		//10 records for imsi 15
		bd47 = new Base_Data();bd47.setCellId(4);bd47.setDateTime(new Date(113,0,11,17,46,1));bd47.setDuration(1000);bd47.setHier3Id(new BigInteger("1234"));bd47.setHier32Id(new BigInteger("1234"));bd47.setHier321Id(new BigInteger("1234"));bd47.setImsi(new BigInteger("344930000000015"));bd47.setNeVersion("neVersion");bd47.setFailure(f);bd47.setUserEquipment(ue);bd47.setEventCause(ec);bd47.setOperator(o);
		bd48 = new Base_Data();bd48.setCellId(4);bd48.setDateTime(new Date(113,0,11,17,46,1));bd48.setDuration(1000);bd48.setHier3Id(new BigInteger("1234"));bd48.setHier32Id(new BigInteger("1234"));bd48.setHier321Id(new BigInteger("1234"));bd48.setImsi(new BigInteger("344930000000015"));bd48.setNeVersion("neVersion");bd48.setFailure(f);bd48.setUserEquipment(ue);bd48.setEventCause(ec);bd48.setOperator(o);
		bd49 = new Base_Data();bd49.setCellId(4);bd49.setDateTime(new Date(113,0,11,17,46,1));bd49.setDuration(1000);bd49.setHier3Id(new BigInteger("1234"));bd49.setHier32Id(new BigInteger("1234"));bd49.setHier321Id(new BigInteger("1234"));bd49.setImsi(new BigInteger("344930000000015"));bd49.setNeVersion("neVersion");bd49.setFailure(f);bd49.setUserEquipment(ue);bd49.setEventCause(ec);bd49.setOperator(o);
		bd50 = new Base_Data();bd50.setCellId(4);bd50.setDateTime(new Date(113,0,11,17,46,1));bd50.setDuration(1000);bd50.setHier3Id(new BigInteger("1234"));bd50.setHier32Id(new BigInteger("1234"));bd50.setHier321Id(new BigInteger("1234"));bd50.setImsi(new BigInteger("344930000000015"));bd50.setNeVersion("neVersion");bd50.setFailure(f);bd50.setUserEquipment(ue);bd50.setEventCause(ec);bd50.setOperator(o);
		bd51 = new Base_Data();bd51.setCellId(4);bd51.setDateTime(new Date(113,0,11,17,46,1));bd51.setDuration(1000);bd51.setHier3Id(new BigInteger("1234"));bd51.setHier32Id(new BigInteger("1234"));bd51.setHier321Id(new BigInteger("1234"));bd51.setImsi(new BigInteger("344930000000015"));bd51.setNeVersion("neVersion");bd51.setFailure(f);bd51.setUserEquipment(ue);bd51.setEventCause(ec);bd51.setOperator(o);
		bd52 = new Base_Data();bd52.setCellId(4);bd52.setDateTime(new Date(113,0,11,17,46,1));bd52.setDuration(1000);bd52.setHier3Id(new BigInteger("1234"));bd52.setHier32Id(new BigInteger("1234"));bd52.setHier321Id(new BigInteger("1234"));bd52.setImsi(new BigInteger("344930000000015"));bd52.setNeVersion("neVersion");bd52.setFailure(f);bd52.setUserEquipment(ue);bd52.setEventCause(ec);bd52.setOperator(o);
		bd53 = new Base_Data();bd53.setCellId(4);bd53.setDateTime(new Date(113,0,11,17,46,1));bd53.setDuration(1000);bd53.setHier3Id(new BigInteger("1234"));bd53.setHier32Id(new BigInteger("1234"));bd53.setHier321Id(new BigInteger("1234"));bd53.setImsi(new BigInteger("344930000000015"));bd53.setNeVersion("neVersion");bd53.setFailure(f);bd53.setUserEquipment(ue);bd53.setEventCause(ec);bd53.setOperator(o);
		bd54 = new Base_Data();bd54.setCellId(4);bd54.setDateTime(new Date(113,0,11,17,46,1));bd54.setDuration(1000);bd54.setHier3Id(new BigInteger("1234"));bd54.setHier32Id(new BigInteger("1234"));bd54.setHier321Id(new BigInteger("1234"));bd54.setImsi(new BigInteger("344930000000015"));bd54.setNeVersion("neVersion");bd54.setFailure(f);bd54.setUserEquipment(ue);bd54.setEventCause(ec);bd54.setOperator(o);
		bd55 = new Base_Data();bd55.setCellId(4);bd55.setDateTime(new Date(113,0,11,17,46,1));bd55.setDuration(1000);bd55.setHier3Id(new BigInteger("1234"));bd55.setHier32Id(new BigInteger("1234"));bd55.setHier321Id(new BigInteger("1234"));bd55.setImsi(new BigInteger("344930000000015"));bd55.setNeVersion("neVersion");bd55.setFailure(f);bd55.setUserEquipment(ue);bd55.setEventCause(ec);bd55.setOperator(o);
		bd56 = new Base_Data();bd56.setCellId(4);bd56.setDateTime(new Date(113,0,11,17,46,1));bd56.setDuration(1000);bd56.setHier3Id(new BigInteger("1234"));bd56.setHier32Id(new BigInteger("1234"));bd56.setHier321Id(new BigInteger("1234"));bd56.setImsi(new BigInteger("344930000000015"));bd56.setNeVersion("neVersion");bd56.setFailure(f);bd56.setUserEquipment(ue);bd56.setEventCause(ec);bd56.setOperator(o);
		
		failureDAO.addFailure(f);
		operatorDAO.addOperator(o);
		user_EquipmentDAO.addUser_Equipment(ue);
		event_CauseDAO.addEvent_Cause(ec);
		
		baseDataDAO.addBase_Data(bd);
		baseDataDAO.addBase_Data(bd1);
		baseDataDAO.addBase_Data(bd2);
		baseDataDAO.addBase_Data(bd3);
		baseDataDAO.addBase_Data(bd4);
		baseDataDAO.addBase_Data(bd5);
		baseDataDAO.addBase_Data(bd6);
		baseDataDAO.addBase_Data(bd7);
		baseDataDAO.addBase_Data(bd8);
		baseDataDAO.addBase_Data(bd9);
		baseDataDAO.addBase_Data(bd10);
		
		baseDataDAO.addBase_Data(bd11);
		baseDataDAO.addBase_Data(bd12);
		baseDataDAO.addBase_Data(bd13);
		baseDataDAO.addBase_Data(bd14);
		baseDataDAO.addBase_Data(bd15);
		baseDataDAO.addBase_Data(bd16);
		baseDataDAO.addBase_Data(bd17);
		baseDataDAO.addBase_Data(bd18);
		baseDataDAO.addBase_Data(bd19);
		baseDataDAO.addBase_Data(bd20);
		
		
		baseDataDAO.addBase_Data(bd21);
		baseDataDAO.addBase_Data(bd22);
		baseDataDAO.addBase_Data(bd23);
		baseDataDAO.addBase_Data(bd24);
		baseDataDAO.addBase_Data(bd25);
		baseDataDAO.addBase_Data(bd26);
		baseDataDAO.addBase_Data(bd27);
		baseDataDAO.addBase_Data(bd28);
		baseDataDAO.addBase_Data(bd29);
		baseDataDAO.addBase_Data(bd30);		
		
		baseDataDAO.addBase_Data(bd31);
		baseDataDAO.addBase_Data(bd32);
		baseDataDAO.addBase_Data(bd33);
		baseDataDAO.addBase_Data(bd34);
		baseDataDAO.addBase_Data(bd35);
		baseDataDAO.addBase_Data(bd36);
		baseDataDAO.addBase_Data(bd37);
		baseDataDAO.addBase_Data(bd38);
		baseDataDAO.addBase_Data(bd39);
		baseDataDAO.addBase_Data(bd40);	
		
		baseDataDAO.addBase_Data(bd41);
		baseDataDAO.addBase_Data(bd42);
		baseDataDAO.addBase_Data(bd43);
		baseDataDAO.addBase_Data(bd44);
		baseDataDAO.addBase_Data(bd45);
		baseDataDAO.addBase_Data(bd46);
		baseDataDAO.addBase_Data(bd47);
		baseDataDAO.addBase_Data(bd48);
		baseDataDAO.addBase_Data(bd49);
		baseDataDAO.addBase_Data(bd50);	
		
		baseDataDAO.addBase_Data(bd51);
		baseDataDAO.addBase_Data(bd52);
		baseDataDAO.addBase_Data(bd53);
		baseDataDAO.addBase_Data(bd54);
		baseDataDAO.addBase_Data(bd55);
		baseDataDAO.addBase_Data(bd56);
		
	}

	@After
	public void emptydb() {
		baseDataDAO.removeBase_Data(bd);
		baseDataDAO.removeBase_Data(bd1);
		baseDataDAO.removeBase_Data(bd2);
		baseDataDAO.removeBase_Data(bd3);
		baseDataDAO.removeBase_Data(bd4);
		baseDataDAO.removeBase_Data(bd5);
		baseDataDAO.removeBase_Data(bd6);
		baseDataDAO.removeBase_Data(bd7);
		baseDataDAO.removeBase_Data(bd8);
		baseDataDAO.removeBase_Data(bd9);
		baseDataDAO.removeBase_Data(bd10);
		
		baseDataDAO.removeBase_Data(bd11);
		baseDataDAO.removeBase_Data(bd12);
		baseDataDAO.removeBase_Data(bd13);
		baseDataDAO.removeBase_Data(bd14);
		baseDataDAO.removeBase_Data(bd15);
		baseDataDAO.removeBase_Data(bd16);
		baseDataDAO.removeBase_Data(bd17);
		baseDataDAO.removeBase_Data(bd18);
		baseDataDAO.removeBase_Data(bd19);
		baseDataDAO.removeBase_Data(bd20);
		
		
		baseDataDAO.removeBase_Data(bd21);
		baseDataDAO.removeBase_Data(bd22);
		baseDataDAO.removeBase_Data(bd23);
		baseDataDAO.removeBase_Data(bd24);
		baseDataDAO.removeBase_Data(bd25);
		baseDataDAO.removeBase_Data(bd26);
		baseDataDAO.removeBase_Data(bd27);
		baseDataDAO.removeBase_Data(bd28);
		baseDataDAO.removeBase_Data(bd29);
		baseDataDAO.removeBase_Data(bd30);		
		
		baseDataDAO.removeBase_Data(bd31);
		baseDataDAO.removeBase_Data(bd32);
		baseDataDAO.removeBase_Data(bd33);
		baseDataDAO.removeBase_Data(bd34);
		baseDataDAO.removeBase_Data(bd35);
		baseDataDAO.removeBase_Data(bd36);
		baseDataDAO.removeBase_Data(bd37);
		baseDataDAO.removeBase_Data(bd38);
		baseDataDAO.removeBase_Data(bd39);
		baseDataDAO.removeBase_Data(bd40);	
		
		baseDataDAO.removeBase_Data(bd41);
		baseDataDAO.removeBase_Data(bd42);
		baseDataDAO.removeBase_Data(bd43);
		baseDataDAO.removeBase_Data(bd44);
		baseDataDAO.removeBase_Data(bd45);
		baseDataDAO.removeBase_Data(bd46);
		baseDataDAO.removeBase_Data(bd47);
		baseDataDAO.removeBase_Data(bd48);
		baseDataDAO.removeBase_Data(bd49);
		baseDataDAO.removeBase_Data(bd50);	
		
		baseDataDAO.removeBase_Data(bd51);
		baseDataDAO.removeBase_Data(bd52);
		baseDataDAO.removeBase_Data(bd53);
		baseDataDAO.removeBase_Data(bd54);
		baseDataDAO.removeBase_Data(bd55);
		baseDataDAO.removeBase_Data(bd56);
		
		failureDAO.removeFailure(f);
		operatorDAO.removeOperator(o);
		user_EquipmentDAO.removeUser_Equipment(ue);
		event_CauseDAO.removeEvent_Cause(ec);
	}
	
	@Test
	public void testTheQuery() {
		// call the BaseDataDAO method
		//pass in the dates
		// assertEquals 
	}

}
