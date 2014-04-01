package test;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.Base_Data;
import model.Event_Cause;
import model.Event_CausePK;
import model.Failure;
import model.Operator;
import model.OperatorPK;
import model.User_Equipment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ejb.EJB;

import jaxrs.Base_DataWS;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Ignore;
import org.junit.runner.RunWith;

import dao.Base_DataDAO;
import dao.Event_CauseDAO;
import dao.FailureDAO;
import dao.OperatorDAO;
import dao.User_EquipmentDAO;

@RunWith(Arquillian.class)
public class Base_DataTest {

	@Deployment
	public static JavaArchive createTestArchive() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addPackages(true, "dao", "jaxrs", "model", "restApp")
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	private Base_DataDAO baseDataDAO;
	@EJB
	private FailureDAO failureDAO;
	@EJB
	private OperatorDAO operatorDAO;
	@EJB
	private User_EquipmentDAO user_EquipmentDAO;
	@EJB
	private Event_CauseDAO event_CauseDAO;

	@EJB
	private Base_DataWS baseDataWS;

	private Base_Data bd,bd1,bd2;
	private Failure f;
	private Operator o;
	private User_Equipment ue;
	private Event_Cause ec;

	
	public void addOneBaseDataRow() {
		backingTables();

		bd = new Base_Data();
		bd.setCellId(1);
		bd.setDateTime(new Date(1000));
		bd.setDuration(1000);
		bd.setHier3Id(new BigInteger("1234"));
		bd.setHier32Id(new BigInteger("1234"));
		bd.setHier321Id(new BigInteger("1234"));
		bd.setImsi(new BigInteger("1234"));
		bd.setNeVersion("neVersion");
		bd.setFailure(f);
		bd.setUserEquipment(ue);
		bd.setEventCause(ec);
		bd.setOperator(o);

		addBackUpTables();
		
		baseDataDAO.addBase_Data(bd);
	}
	
	public void addBackUpTables(){
		failureDAO.addFailure(f);
		operatorDAO.addOperator(o);
		user_EquipmentDAO.addUser_Equipment(ue);
		event_CauseDAO.addEvent_Cause(ec);		
	}
	
	public void emptyBackUpTables(){
		failureDAO.removeFailure(f);
		operatorDAO.removeOperator(o);
		user_EquipmentDAO.removeUser_Equipment(ue);
		event_CauseDAO.removeEvent_Cause(ec);		
	}
	
	public void backingTables(){
		f = new Failure();
		f.setFailureId(0);
		f.setDescription("Test Failure");

		o = new Operator();
		OperatorPK id = new OperatorPK(100, 100);
		o.setId(id);
		o.setCountry("ireland");
		o.setOperatorName("vodafone");

		ec = new Event_Cause();
		Event_CausePK pkid = new Event_CausePK(1000, 1000);
		ec.setId(pkid);
		ec.setDescription("An event of sorts");

		ue = new User_Equipment();
		ue.setUser_EquipmentId(100);
		ue.setMarketingName("marketingName");
		ue.setManufacturer("manufacturer");
		ue.setAccessCapability("accessCapability");
		ue.setModel("model");
		ue.setVendorName("vendorName");
		ue.setUeType("ueType");
		ue.setOs("os");
		ue.setInputMode("inputMode");		
	}

	
	public void emptydb() {
		baseDataDAO.removeBase_Data(bd);
		emptyBackUpTables();
	}
	
	private void emptyMultipleBaseDatas() {
		// TODO Auto-generated method stub
		baseDataDAO.removeBase_Data(bd);
		baseDataDAO.removeBase_Data(bd1);
		baseDataDAO.removeBase_Data(bd2);
		emptyBackUpTables();	
		
	}


	private void setUpMultipleBaseDatas() {
		backingTables();
		bd = new Base_Data();bd.setCellId(4);bd.setDateTime(new Date(113,0,11,17,46,1));bd.setDuration(1000);bd.setHier3Id(new BigInteger("1234"));bd.setHier32Id(new BigInteger("1234"));bd.setHier321Id(new BigInteger("1234"));bd.setImsi(new BigInteger("344930000000012"));bd.setNeVersion("neVersion");bd.setFailure(f);bd.setUserEquipment(ue);bd.setEventCause(ec);bd.setOperator(o);
		bd1 = new Base_Data();bd1.setCellId(4);bd1.setDateTime(new Date(113,0,11,17,46,1));bd1.setDuration(1000);bd1.setHier3Id(new BigInteger("1234"));bd1.setHier32Id(new BigInteger("1234"));bd1.setHier321Id(new BigInteger("1234"));bd1.setImsi(new BigInteger("344930000000012"));bd1.setNeVersion("neVersion");bd1.setFailure(f);bd1.setUserEquipment(ue);bd1.setEventCause(ec);bd1.setOperator(o);
		bd2 = new Base_Data();bd2.setCellId(4);bd2.setDateTime(new Date(113,0,11,17,46,1));bd2.setDuration(1000);bd2.setHier3Id(new BigInteger("1234"));bd2.setHier32Id(new BigInteger("1234"));bd2.setHier321Id(new BigInteger("1234"));bd2.setImsi(new BigInteger("344930000000012"));bd2.setNeVersion("neVersion");bd2.setFailure(f);bd2.setUserEquipment(ue);bd2.setEventCause(ec);bd2.setOperator(o);
		addBackUpTables();
		
		baseDataDAO.addBase_Data(bd);	
		baseDataDAO.addBase_Data(bd1);
		baseDataDAO.addBase_Data(bd2);	
	}

	@Test
	public void WS_testgetAllBase_Data() {
		addOneBaseDataRow();
		List<Base_Data> bds = baseDataWS.getAllBase_Data();
		bd = bds.get(0);
		assertNotNull(bd);
		assertNotNull(bd.getDataId());
		assertNotNull(bd.getCellId());
		assertNotNull(bd.getDateTime());
		assertNotNull(bd.getDuration());
		assertNotNull(bd.getHier3Id());
		assertNotNull(bd.getHier32Id());
		assertNotNull(bd.getHier321Id());
		assertNotNull(bd.getImsi());
		assertNotNull(bd.getNeVersion());
		assertNotNull(bd.getFailure());
		assertNotNull(bd.getUserEquipment());
		assertNotNull(bd.getEventCause());
		assertNotNull(bd.getOperator());
		emptydb();
	}

	@Test
	public void DAO_testForFailuresCountByModelBetweenTwoDates()
			throws ParseException {
		addOneBaseDataRow();
		Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.parse("2000-01-11 16:00:00");
		Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.parse("2015-01-20 17:00:00");
		String model = "Model That doesn't exist";
		Number number = new Integer(0);
		assertEquals(number.intValue(), baseDataDAO
				.failureCountByModelBetweenDates(startDate, endDate, model)
				.intValue());
		emptydb();
	}


	@Ignore
	@Test
	public void DAO_testThatBaseDataReturnsNORecords() throws ParseException {
		Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.parse("2000-01-11 16:00:00");
		Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.parse("2015-01-20 17:00:00");
		BigInteger imsi = BigInteger.valueOf(Long.parseLong("000000000000000"));
		Number number = new Integer(0);
		assertEquals(number.intValue(), baseDataDAO
				.imsiFailureCountBetweenDates(startDate, endDate, imsi)
				.intValue());
	}

	@Test
	public void WS_testThroughTheLayers() throws ParseException {

		Number number = new Integer(0);

		String imsi = "000000000000000";
		String startDate = "2000-01-11T16:00";
		String endDate = "2000-01-11T16:00";
		assertEquals(number.intValue(), baseDataWS
				.checkForAnImsisFailuresBetweenDates(imsi, startDate, endDate)
				.intValue());
	}

	@Test
	public void WS_testgetBase_DataByFailureId() {
		addOneBaseDataRow();
		// pass in the failure id out of failure table
		assertNotNull(baseDataWS.getIMSIByFailureId(0));
		emptydb();
	}

	@Test
	public void WS_testgetEventID_CauseCodeByImsi() {

		addOneBaseDataRow();
		BigInteger imsi = BigInteger.valueOf(Long.parseLong("1234"));
		assertNotNull(baseDataWS.getEventID_CauseCodeByImsi(imsi));
		emptydb();
	}
	
	@Test
	public void WS_testgetIMSIByDateRange() throws ParseException {
		setUpMultipleBaseDatas();
		assertNotNull(baseDataWS.getIMSIByDateRange("2013-01-10T16:00",
				"2013-01-12T16:00"));
		emptyMultipleBaseDatas();
	}
	
	@Test
	public void WS_checkAllPhoneModels() throws ParseException {
		setUpMultipleBaseDatas();
		assertEquals(1,baseDataWS.getAllPhoneModels().size());
		emptyMultipleBaseDatas();		
		
	}
	
	@Test
	public void WS_checkAllImSis(){
		setUpMultipleBaseDatas();
		assertEquals(1,baseDataWS.getAllImsis().size());
		emptyMultipleBaseDatas();			
	}
	
	@Test
	public void WS_checkFailureCountByModelAndDateRange() throws ParseException {
		setUpMultipleBaseDatas();
		Number number = new Integer(3);
		assertEquals(number.intValue(),	baseDataWS.getFailureCountByModelAndByDateRange("model", "2013-01-10T16:00", "2013-01-12T16:00").intValue());
		//baseDataWS.getFailureCountByModelAndByDateRange("model", "2013-01-10T16:00", "2013-01-12T16:00");
		emptyMultipleBaseDatas();			
	}
	
	@Test
	public void DAO_testForAnImsisFailuresBetweenTwoDatesWithAGoodIMSI()
			throws ParseException {
		// use the code up above testthatbasedatareturnsNOrecords
		setUpMultipleBaseDatas();
		Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.parse("2000-01-11 16:00:00");
		Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.parse("2015-01-20 17:00:00");
		BigInteger imsi = BigInteger.valueOf(Long.parseLong("344930000000012"));
		// give in a good imsi, specify that the returned number of records is
		// not null
		Number number = new Integer(3);
		assertEquals(number.intValue(), baseDataDAO
				.imsiFailureCountBetweenDates(startDate, endDate, imsi)
				.intValue());
		emptyMultipleBaseDatas();
	}
 }
 
