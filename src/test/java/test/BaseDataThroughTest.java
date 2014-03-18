package test;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Base_Data;
import model.Event_Cause;
import model.Event_CausePK;
import model.Failure;
import model.Operator;
import model.OperatorPK;
import model.TableRow;
import model.User;
import model.User_Equipment;

import org.junit.Test;

import javax.ejb.EJB;

import jaxrs.Base_DataWS;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import dao.Base_DataDAO;


@RunWith(Arquillian.class)
public class BaseDataThroughTest {

	@EJB
	Base_DataDAO baseDataDao;
	
	@EJB
	Base_DataWS baseDataWs;
	
	@Deployment
	public static JavaArchive createDeployment() {
	    return ShrinkWrap.create(JavaArchive.class, "test.jar")
	    	.addAsResource("META-INF/persistence.xml")
	        .addClasses(Base_DataWS.class,Base_DataDAO.class, Base_Data.class, TableRow.class,Event_Cause.class,Event_CausePK.class,Failure.class,Operator.class, OperatorPK.class,User_Equipment.class,User.class)
	        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	       
	}
	
	@Ignore
	@Test
	public void testThatBaseDataReturnsNORecords() throws ParseException {
		Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2000-01-11 16:00:00");
		Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-01-20 17:00:00");
		BigInteger imsi = BigInteger.valueOf(Long.parseLong("344930000000011"));
		Number number = new Integer(5);
		assertEquals(number.intValue(), baseDataDao.imsiFailureCountBetweenDates(startDate, endDate, imsi).intValue());
	}
	
	
	@Test
	public void testThroughTheLayers() throws ParseException {
		
		Number number = new Integer(0);
		
		String imsi = "000000000000000";
		String startDate = "2000-01-11 16:00:00";
		String endDate = "2000-01-11 16:00:00";
		System.out.println("Layers: " + baseDataWs.checkForAnImsisFailuresBetweenDates(imsi, startDate, endDate).intValue());
		assertEquals(number.intValue(), baseDataWs.checkForAnImsisFailuresBetweenDates(imsi, startDate, endDate).intValue());
	}
	
	@Test
	public void testgetBase_Data(){
		// pass in the primary key: value 3
		assertNotNull(baseDataWs.getBase_Data(3));
	}

	@Test
	public void testgetBase_DataByFailureId(){
		// pass in the failure id out of failure table
		assertNotNull(baseDataWs.getBase_DataByFailureId(0));
	}
	
	@Test
	public void testgetEventID_CauseCodeByImsi(){
		// pass in BigInteger imsi
		// returns event id and cause code
		BigInteger imsi = BigInteger.valueOf(Long.parseLong("0000000000000000"));
		assertNull(baseDataWs.getEventID_CauseCodeByImsi(imsi));
	}
	

	@Test
	public void testgetIMSIByDateRange() throws ParseException {
		// pass in the dates
		// note the date format .0 seconds
		// gets back a list of imsis
		assertNull(baseDataWs.getIMSIByDateRange("1928-01-11 16:00:00.0", "1928-01-11 17:00:00.0"));
	}
	
	@Test
	public void testForAnImsisFailuresBetweenTwoDatesWithABadIMSI() throws ParseException {
		//use the code up above testthatbasedatareturnsNOrecords
		Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2000-01-11 16:00:00");
		Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-01-20 17:00:00");
		BigInteger imsi = BigInteger.valueOf(Long.parseLong("0000000000000000"));
		Number number = new Integer(0);
		assertEquals(baseDataDao.imsiFailureCountBetweenDates(startDate, endDate, imsi).intValue(), number.intValue());
	}
	
	@Test
	public void testForAnImsisFailuresBetweenTwoDatesWithAGoodIMSI() throws ParseException {
		//use the code up above testthatbasedatareturnsNOrecords
		Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2000-01-11 16:00:00");
		Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-01-20 17:00:00");
		BigInteger imsi = BigInteger.valueOf(Long.parseLong("0000000000000000"));
		// give in a good imsi, specify that the returned number of records is not null
		Number number = new Integer(0);
		assertEquals(baseDataDao.imsiFailureCountBetweenDates(startDate, endDate, imsi).intValue(), number.intValue());
	}
}
