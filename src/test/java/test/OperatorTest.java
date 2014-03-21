package test;

import static org.junit.Assert.*;

import javax.ejb.EJB;

import model.Operator;
import model.OperatorPK;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import dao.OperatorDAO;

@RunWith(Arquillian.class)
public class OperatorTest {
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
	private OperatorDAO Operatordao;
	
	private Operator Operator;
	private static OperatorPK operatorPK = new OperatorPK(100, 5);

	@Before
	public void persistdb() {
		Operator = new Operator();
		Operator.setId(operatorPK);
		Operator.setCountry("ireland");
		Operator.setOperatorName("vodafone");
		Operatordao.addOperator(Operator);
	}

	@After
	public void removedb() {
		Operatordao.removeOperator(Operator);
	}

	@Test
	public void testGetOperator() {
		Operator o = Operatordao.getOperator(operatorPK);
		assertNotNull(o);
		assertNotNull(o.getId());
		assertNotNull(o.getCountry());
		assertNotNull(o.getOperatorName());

	}
}
