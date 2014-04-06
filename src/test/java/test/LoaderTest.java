package test;

import static org.junit.Assert.*;

import java.util.List;

import javax.ejb.EJB;

import loader.Loader;
import model.Base_Data;
import model.Event_Cause;
import model.Failure;
import model.Operator;
import model.User_Equipment;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
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
public class LoaderTest {
	@Deployment
	public static WebArchive createTestArchive() {
		MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");

		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackages(true, "dao", "jaxrs", "model", "restApp", "loader")
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsLibraries(resolver.artifact("org.mockito:mockito-all:1.9.5").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.apache.poi:poi").resolveAsFiles());
	}
	
	@EJB
	Loader loader;
	@EJB
	Base_DataDAO base_DataDAO;
	@EJB
	private FailureDAO failureDAO;
	@EJB
	private User_EquipmentDAO user_EquipmentDAO;
	@EJB
	private OperatorDAO operatorDAO;
	@EJB
	private Event_CauseDAO event_CauseDAO;
	private List<Base_Data> base_DataList;
	private List<Event_Cause> event_CauseList;
	private List<Failure> failureList;
	private List<Operator> operatorList;
	private List<User_Equipment> user_EquipmentList;
	
	@Before
	public void before() {
		base_DataList = base_DataDAO.getAllBase_Data();
		event_CauseList = event_CauseDAO.getAllEvent_Cause();
		failureList = failureDAO.getAllFailure();
		operatorList = operatorDAO.getAllOperator();
		user_EquipmentList = user_EquipmentDAO.getAllUser_Equipment();
		
		emptyDatabase();
	}
	
	@After
	public void after() {
		emptyDatabase();
	}

	@Test
	public void test() {
		String excelWorkBookLocation = "/home/datasets/TestDataSample.xls";
		
		loader.loadFile(excelWorkBookLocation);
		
		base_DataList = base_DataDAO.getAllBase_Data();
		event_CauseList = event_CauseDAO.getAllEvent_Cause();
		failureList = failureDAO.getAllFailure();
		operatorList = operatorDAO.getAllOperator();
		user_EquipmentList = user_EquipmentDAO.getAllUser_Equipment();
		
		assertEquals("Base Data ", 1, base_DataList.size());
		assertEquals("Event Cause ", 80, event_CauseList.size());
		assertEquals("Failure ", 5, failureList.size());
		assertEquals("Operator ", 41, operatorList.size());
		assertEquals("User Equipment ", 98, user_EquipmentList.size());
	}
	
	public void emptyDatabase() {
		for(Base_Data row : base_DataList) {
			base_DataDAO.remove(row);
		}
		for(Event_Cause row : event_CauseList) {
			event_CauseDAO.remove(row);
		}
		for(Failure row : failureList) {
			failureDAO.remove(row);
		}
		for(Operator row : operatorList) {
			operatorDAO.remove(row);
		}
		for(User_Equipment row : user_EquipmentList) {
			user_EquipmentDAO.remove(row);
		}
	}

}
