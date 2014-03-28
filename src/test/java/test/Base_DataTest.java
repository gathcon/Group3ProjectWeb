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
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
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
    public static WebArchive createTestArchive() {
		MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
        
		return ShrinkWrap.create(WebArchive.class, "test.war")
        		.addPackages(true, "dao", "jaxrs", "model", "restApp")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

	@EJB private Base_DataDAO baseDataDAO;
	@EJB private FailureDAO failureDAO;
	@EJB private OperatorDAO operatorDAO;
	@EJB private User_EquipmentDAO user_EquipmentDAO;
	@EJB private Event_CauseDAO event_CauseDAO;

	@EJB private Base_DataWS baseDataWS;

	private Base_Data bd;
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
		
		failureDAO.addFailure(f);
		operatorDAO.addOperator(o);
		user_EquipmentDAO.addUser_Equipment(ue);
		event_CauseDAO.addEvent_Cause(ec);
		baseDataDAO.addBase_Data(bd);
	}

	@After
	public void emptydb() {
		baseDataDAO.removeBase_Data(bd);
		failureDAO.removeFailure(f);
		operatorDAO.removeOperator(o);
		user_EquipmentDAO.removeUser_Equipment(ue);
		event_CauseDAO.removeEvent_Cause(ec);
	}

	@Test
	public void testgetAllBase_Data() {
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
	}

//	@Ignore
//	@Test
//	public void testThatBaseDataReturnsNORecords() throws ParseException {
//		Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//				.parse("2000-01-11 16:00:00");
//		Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//				.parse("2015-01-20 17:00:00");
//		BigInteger imsi = BigInteger.valueOf(Long.parseLong("344930000000011"));
//		Number number = new Integer(5);
//		assertEquals(number.intValue(), baseDataDao
//				.imsiFailureCountBetweenDates(startDate, endDate, imsi)
//				.intValue());
//	}
//
//	@Test
//	public void testThroughTheLayers() throws ParseException {
//
//		Number number = new Integer(0);
//
//		String imsi = "000000000000000";
//		String startDate = "2000-01-11 16:00:00";
//		String endDate = "2000-01-11 16:00:00";
//		assertEquals(number.intValue(), baseDataWs
//				.checkForAnImsisFailuresBetweenDates(imsi, startDate, endDate)
//				.intValue());
//	}
//
//	@Test
//	public void testgetBase_DataByFailureId() {
//		// pass in the failure id out of failure table
//		assertNotNull(baseDataWs.getBase_DataByFailureId(0));
//	}
//
//	@Test
//	public void testgetEventID_CauseCodeByImsi() {
//		// pass in BigInteger imsi
//		// returns event id and cause code
//		BigInteger imsi = BigInteger
//				.valueOf(Long.parseLong("0000000000000000"));
//		assertNull(baseDataWs.getEventID_CauseCodeByImsi(imsi));
//	}
//
//	@Test
//	public void testgetIMSIByDateRange() throws ParseException {
//		// pass in the dates
//		// note the date format .0 seconds
//		// gets back a list of imsis
//		assertNull(baseDataWs.getIMSIByDateRange("1928-01-11 16:00:00.0",
//				"1928-01-11 17:00:00.0"));
//	}
//
//	@Test
//	public void testForAnImsisFailuresBetweenTwoDatesWithABadIMSI()
//			throws ParseException {
//		// use the code up above testthatbasedatareturnsNOrecords
//		Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//				.parse("2000-01-11 16:00:00");
//		Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//				.parse("2015-01-20 17:00:00");
//		BigInteger imsi = BigInteger
//				.valueOf(Long.parseLong("0000000000000000"));
//		Number number = new Integer(0);
//		assertEquals(number.intValue(), baseDataDao
//				.imsiFailureCountBetweenDates(startDate, endDate, imsi)
//				.intValue());
//	}
//
//	@Test
//	public void testForAnImsisFailuresBetweenTwoDatesWithAGoodIMSI()
//			throws ParseException {
//		// use the code up above testthatbasedatareturnsNOrecords
//		Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//				.parse("2000-01-11 16:00:00");
//		Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//				.parse("2015-01-20 17:00:00");
//		BigInteger imsi = BigInteger
//				.valueOf(Long.parseLong("0000000000000000"));
//		// give in a good imsi, specify that the returned number of records is
//		// not null
//		Number number = new Integer(0);
//		assertEquals(number.intValue(), baseDataDao
//				.imsiFailureCountBetweenDates(startDate, endDate, imsi)
//				.intValue());
//	}
}
