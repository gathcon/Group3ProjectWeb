package test;

import static org.junit.Assert.*;

import javax.ejb.EJB;

import model.Event_Cause;
import model.Event_CausePK;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import dao.Event_CauseDAO;

@RunWith(Arquillian.class)
public class Event_CauseTest {
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
	private Event_CauseDAO event_Causedao;
	
	private Event_Cause event_Cause;
	private static Event_CausePK event_CausePK = new Event_CausePK(100, 5);

	@Before
	public void persistdb() {
		event_Cause = new Event_Cause();
		event_Cause.setId(event_CausePK);
		event_Cause.setDescription("description");
		event_Causedao.addEvent_Cause(event_Cause);
	}

	@After
	public void removedb() {
		event_Causedao.removeEvent_Cause(event_Cause);
	}

	@Test
	public void testGetEvent_Cause() {
		Event_Cause ec = event_Causedao.getEvent_Cause(event_CausePK);
		assertNotNull(ec);
		assertNotNull(ec.getId());
		assertNotNull(ec.getDescription());

	}
}
