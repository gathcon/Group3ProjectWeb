package test;

import javax.ejb.EJB;

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
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Test;
import org.junit.runner.RunWith;

import dao.Event_CauseDAO;
import dao.FailureDAO;
import dao.OperatorDAO;
import dao.User_EquipmentDAO;

@RunWith(Arquillian.class)
public class DuplicateTest {
	
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
	private FailureDAO failureDAO;
	@EJB
	private OperatorDAO operatorDAO;
	@EJB
	private User_EquipmentDAO user_EquipmentDAO;
	@EJB
	private Event_CauseDAO event_CauseDAO;

	@Test
	public void test() {
		Failure f1 = new Failure();
		f1.setFailureId(0);
		f1.setDescription("Test Failure");
		Failure f2 = new Failure();
		f2.setFailureId(0);
		f2.setDescription("Test Failure");
		
		failureDAO.persist(f1);
		failureDAO.persist(f2);
		
		Operator o1 = new Operator();
		OperatorPK id1 = new OperatorPK(100, 100);
		o1.setId(id1);
		o1.setCountry("ireland");
		o1.setOperatorName("vodafone");
		Operator o2 = new Operator();
		OperatorPK id2 = new OperatorPK(100, 100);
		o2.setId(id2);
		o2.setCountry("ireland");
		o2.setOperatorName("vodafone");
		
		operatorDAO.persist(o1);
		operatorDAO.persist(o2);

		Event_Cause ec1 = new Event_Cause();
		Event_CausePK pkid1 = new Event_CausePK(1000, 1000);
		ec1.setId(pkid1);
		ec1.setDescription("An event of sorts");
		Event_Cause ec2 = new Event_Cause();
		Event_CausePK pkid2 = new Event_CausePK(1000, 1000);
		ec2.setId(pkid2);
		ec2.setDescription("An event of sorts");
		
		event_CauseDAO.persist(ec1);
		event_CauseDAO.persist(ec2);

		User_Equipment ue1 = new User_Equipment();
		ue1.setUser_EquipmentId(100);
		ue1.setMarketingName("marketingName");
		ue1.setManufacturer("manufacturer");
		ue1.setAccessCapability("accessCapability");
		ue1.setModel("model");
		ue1.setVendorName("vendorName");
		ue1.setUeType("ueType");
		ue1.setOs("os");
		ue1.setInputMode("inputMode");
		User_Equipment ue2 = new User_Equipment();
		ue2.setUser_EquipmentId(100);
		ue2.setMarketingName("marketingName");
		ue2.setManufacturer("manufacturer");
		ue2.setAccessCapability("accessCapability");
		ue2.setModel("model");
		ue2.setVendorName("vendorName");
		ue2.setUeType("ueType");
		ue2.setOs("os");
		ue2.setInputMode("inputMode");
		
		user_EquipmentDAO.persist(ue1);
		user_EquipmentDAO.persist(ue2);
	}

}
