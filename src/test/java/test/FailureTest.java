package test;

import static org.junit.Assert.*;

import javax.ejb.EJB;

import model.Failure;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import dao.FailureDAO;

@RunWith(Arquillian.class)
public class FailureTest {
	
	@Deployment
    public static WebArchive createTestArchive() {
		MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
        
		return ShrinkWrap.create(WebArchive.class, "test.war")
        		.addPackages(true, "dao", "jaxrs", "model", "restApp")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

	@EJB
	private FailureDAO failuredao;
	
	private Failure failure;
	private static int failurePK = 101;

	@Before
	public void persistdb() {
		failure = new Failure();
		failure.setFailureId(failurePK);
		failure.setDescription("Test Failure");
		failuredao.addFailure(failure);
	}

	@After
	public void removedb() {
		failuredao.removeFailure(failure);
	}

	@Test
	public void testGetFailure() {
		Failure f = failuredao.getFailure(failurePK);
		assertNotNull(f);
		assertNotNull(f.getFailureId());
		assertNotNull(f.getDescription());
	}
}
