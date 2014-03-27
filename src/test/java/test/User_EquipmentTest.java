package test;

import static org.junit.Assert.*;

import javax.ejb.EJB;

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

import dao.User_EquipmentDAO;

@RunWith(Arquillian.class)
public class User_EquipmentTest {
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
	private User_EquipmentDAO user_Equipmentdao;
	
	private User_Equipment user_Equipment;
	private static int user_EquipmentPK = 101;

	@Before
	public void persistdb() {
		user_Equipment = new User_Equipment();
		user_Equipment.setUser_EquipmentId(user_EquipmentPK);
		user_Equipment.setAccessCapability("accessCapability");
		user_Equipment.setInputMode("inputMode");
		user_Equipment.setManufacturer("manufacturer");
		user_Equipment.setMarketingName("marketingName");
		user_Equipment.setModel("model");
		user_Equipment.setOs("os");
		user_Equipment.setUeType("ueType");
		user_Equipment.setVendorName("vendorName");
		user_Equipmentdao.addUser_Equipment(user_Equipment);
	}

	@After
	public void removedb() {
		user_Equipmentdao.removeUser_Equipment(user_Equipment);
	}

	@Test
	public void testGetUser_Equipment() {
		User_Equipment ue = user_Equipmentdao.getUser_Equipment(user_EquipmentPK);
		assertNotNull(ue);
		assertNotNull(ue.getUser_EquipmentId());
		assertNotNull(ue.getAccessCapability());
		assertNotNull(ue.getInputMode());
		assertNotNull(ue.getManufacturer());
		assertNotNull(ue.getMarketingName());
		assertNotNull(ue.getModel());
		assertNotNull(ue.getOs());
		assertNotNull(ue.getUeType());
		assertNotNull(ue.getVendorName());
	}
}