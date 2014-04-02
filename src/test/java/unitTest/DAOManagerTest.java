package unitTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.ejb.EJB;

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
import org.junit.Test;
import org.junit.runner.RunWith;
import dao.Base_DataDAO;
import dao.DAOManager;
import dao.DatabaseResponse;
import dao.Event_CauseDAO;
import dao.FailureDAO;
import dao.OperatorDAO;
import dao.User_EquipmentDAO;

@RunWith(Arquillian.class)
public class DAOManagerTest {

	@Deployment
	public static WebArchive createTestArchive() {
		MavenDependencyResolver resolver = DependencyResolvers.use(
				MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");

		return ShrinkWrap.create(WebArchive.class, "test.war")
        		.addPackages(true, "dao", "jaxrs", "model", "restApp", "loader")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsLibraries(resolver.artifact("org.mockito:mockito-all:1.9.5").resolveAsFiles())
                .addAsLibraries(resolver.artifact("org.apache.poi:poi").resolveAsFiles());
	}

	@EJB
	DAOManager daoManager;

	// Behavior tests

	@Test
	public void testDAOManagerForBaseData() {
		Base_Data mockedBase_DataEntity = mock(Base_Data.class);
		Base_DataDAO mockedBase_DataDAO = mock(Base_DataDAO.class);
		daoManager.setBaseDataDAO(mockedBase_DataDAO);
		when(mockedBase_DataDAO.persist(mockedBase_DataEntity))
				.thenReturn(DatabaseResponse.OK);
		DatabaseResponse response = daoManager.persist(mockedBase_DataEntity);
		assertEquals(DatabaseResponse.OK, response);
	}

	@Test
	public void testDAOManagerForFailure() {
		Failure mockedFailureEntity = mock(Failure.class);
		FailureDAO mockedFailureDAO = mock(FailureDAO.class);
		daoManager.setFailureDAO(mockedFailureDAO);
		when(mockedFailureDAO.persist(mockedFailureEntity)).thenReturn(
				DatabaseResponse.OK);
		DatabaseResponse response = daoManager.persist(mockedFailureEntity);
		assertEquals(DatabaseResponse.OK, response);
	}

	@Test
	public void testDAOManagerForUserEquipment() {
		User_Equipment mockedUser_EquipmentEntity = mock(User_Equipment.class);
		User_EquipmentDAO mockedUser_EquipmentDAO = mock(User_EquipmentDAO.class);
		daoManager.setUser_EquipmentDAO(mockedUser_EquipmentDAO);
		when(
				mockedUser_EquipmentDAO
						.persist(mockedUser_EquipmentEntity))
				.thenReturn(DatabaseResponse.OK);
		DatabaseResponse response = daoManager
				.persist(mockedUser_EquipmentEntity);
		assertEquals(DatabaseResponse.OK, response);
	}

	@Test
	public void testDAOManagerForEventCause() {
		Event_Cause mockedEvent_CauseEntity = mock(Event_Cause.class);
		Event_CauseDAO mockedEvent_CauseDAO = mock(Event_CauseDAO.class);
		daoManager.setEvent_CauseDAO(mockedEvent_CauseDAO);
		when(mockedEvent_CauseDAO.persist(mockedEvent_CauseEntity))
				.thenReturn(DatabaseResponse.OK);
		DatabaseResponse response = daoManager.persist(mockedEvent_CauseEntity);
		assertEquals(DatabaseResponse.OK, response);
	}

	@Test
	public void testDAOManagerForOperator() {
		Operator mockedOperatorEntity = mock(Operator.class);
		OperatorDAO mockedOperatorDAO = mock(OperatorDAO.class);
		daoManager.setOperatorDAO(mockedOperatorDAO);
		when(mockedOperatorDAO.persist(mockedOperatorEntity)).thenReturn(
				DatabaseResponse.OK);
		DatabaseResponse response = daoManager.persist(mockedOperatorEntity);
		assertEquals(DatabaseResponse.OK, response);
	}

}
